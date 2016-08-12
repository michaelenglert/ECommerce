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

        ThirdPartyInventoryCheck tp1 = new ThirdPartyInventoryCheck("ThirdPartyInventoryCheck", "api.mainsupplier.com", "responsetime:2");
        tp1.start();
        ThirdPartyInventoryCheck tp2 = new ThirdPartyInventoryCheck("ThirdPartyInventoryCheck", "api.secondarysupplier.com", "responsetime:0");
        tp2.start();

        try  {
            tp1.join();
            tp2.join();
        } catch (InterruptedException e)  {}

        OrderManager om = new OrderManager();
        om.updateOrder();

        QueryExecutor oracleItems = (QueryExecutor) SpringContext.getBean("queryExecutor");
        oracleItems.executeOracleQuery();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Thread Contention</h1>");
        out.println("</body></html>");
    }
}

class ThirdPartyInventoryCheck extends Thread {

    private AvailableInventory ai;
    private String hostname;
    private String trigger;

    ThirdPartyInventoryCheck(String name, String hostname, String trigger) {
        super(name);
        this.ai = AvailableInventory.getInstance();
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
        try {
            r = this.doRequest();
        } catch (IOException e) {}
        return r;
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
        String foo = this.determineInventory(r);

        this.ai.updateSupplier("foo");
        this.ai.updateInventory(1);
        return foo;
    }

    public String parseResponse(String response) {
        int n=0;
        for (char c : response.toCharArray()) {
            n++;
        }
        return response;
    }

    public String determineInventory(String response) {
        int n=0;
        for (char c : response.toCharArray()) {
            n++;
        }
        return response;
    }
}

class OrderManager {

    void updateOrder() {
        this.update(1000);
    }

    Integer update(Integer limit) {


        Integer count = 0;
        for(int i=1; i< limit; i++){
            count++;
        }
        return count;
    }
}
