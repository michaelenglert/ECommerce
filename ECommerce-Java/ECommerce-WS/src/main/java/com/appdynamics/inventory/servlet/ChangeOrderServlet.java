package com.appdynamics.inventory.servlet;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.appdynamics.inventory.AvailableInventory;
import com.appdynamics.inventory.QueryExecutor;
import com.appdynamicspilot.util.SpringContext;

/**
 * Created by rbolton on 8/4/16.
 * Built for thread contention use case
 */
public class ChangeOrderServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        String trigger = request.getParameter("trigger");
        InputStream downstream = new URL("http://creditcheck:3000/?x-appd-demo=" + trigger).openStream();

        String type = "experian";
        if (trigger.equals("responsetime:2")) {
            type = "equifax";
        }
        */

        this.execute();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Thread Contention</h1>");
        out.println("</body></html>");
    }

    void execute() {
        String trigger1 = "responsetime:0";
        String trigger2 = "responsetime:0";

        Date date = new Date();
        SimpleDateFormat minutes = new SimpleDateFormat("m");
        String m = minutes.format(date);
        if (Integer.parseInt(m) < 20 && Math.random() < .25) {
            trigger1 = "responsetime:2";
            trigger2 = "responsetime:1";
        }
        AvailableInventory ai = new AvailableInventory();

        ThirdPartyInventoryCheck tp1 = new ThirdPartyInventoryCheck("MainSupplierInventoryCheck", "api.mainsupplier.com", trigger1, ai);
        tp1.start();
        QueryExecutor oracleItems = (QueryExecutor) SpringContext.getBean("queryExecutor");
        OrderManager om = new OrderManager();
        om.setDbConn(oracleItems);
        om.updateOrder();

        ThirdPartyInventoryCheck tp2 = new ThirdPartyInventoryCheck("SecondarySupplierInventoryCheck", "api.secondarysupplier.com", trigger2, ai);
        tp2.start();
        oracleItems.executeOrderUpdateQuery();
        om.updateOrder();

        processResponse(tp1, tp2);
    }

    void processResponse(ThirdPartyInventoryCheck tp1, ThirdPartyInventoryCheck tp2) {
        this.filterData(tp1, tp2);
    }

    void filterData(ThirdPartyInventoryCheck tp1, ThirdPartyInventoryCheck tp2) {
        try  {
            tp1.join();
            tp2.join();
        } catch (InterruptedException e)  {}
    }
}

class ThirdPartyInventoryCheck extends Thread {

    private AvailableInventory ai;
    private String hostname;
    private String trigger;

    ThirdPartyInventoryCheck(String name, String hostname, String trigger, AvailableInventory ai) {
        super(name);
        this.ai = ai;
        this.hostname = hostname;
        this.trigger = trigger;
    }

    public void run() {
        synchronized (ai) {
            this.checkAvailability();
        }
    }

    public String checkAvailability() {
        String r = "";
        this.lockAvailableInventory();
        return "foo";
    }

    public void lockAvailableInventory() {
        this.determineInventory();
    }

    public void determineInventory() {
        this.process();
    }

    public void process() {
        try {
            this.doRequest();
        } catch (IOException e) {}
    }

    public String doRequest() throws IOException {
        //InputStream downstream = new URL("http://" + this.hostname + ":3000/checkInventory/id/46212?x-appd-demo=" + this.trigger).openStream();
        URL url = new URL("http://" + this.hostname + ":3000/checkInventory/id/46212");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("x-appd-demo", this.trigger);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String r = parseResponse(response.toString());

        this.ai.updateSupplier("foo");
        this.ai.updateInventory(1);
        return "foo";
    }

    public String parseResponse(String response) {
        int n=0;
        for (char c : response.toCharArray()) {
            n++;
        }
        return response;
    }
}

class OrderManager {

    public QueryExecutor dbConn;

    public void setDbConn(QueryExecutor dbConn) {
        this.dbConn = dbConn;
    }

    void updateOrder() {

        this.update(100000);
    }

    Integer update(Integer limit) {


        Integer count = 0;
        for(int i=1; i< limit; i++){
            count++;
        }

        dbConn.executeOrderUpdateQuery();

        return count;
    }
}
