package com.appdynamicspilot.restv2;

import com.appdynamicspilot.model.Fault;
import com.appdynamicspilot.service.FaultService;
import com.appdynamicspilot.util.FaultUtils;
import com.appdynamicspilot.util.SpringContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by swetha.ravichandran on 7/2/15.
 */
@Path("/json/fault")
public class Faults {

    private static final Logger log = Logger.getLogger(Faults.class.getName());
    FaultUtils faultUtils = new FaultUtils();

    /**
     * Gets FaultService bean
     *
     * @return FaultService
     */
    public FaultService getFIBugService() {
        return (FaultService) SpringContext.getBean("faultService");
    }

    /**
     * Save the faults and can be injected later based on time frame.
     *
     * @param lsFault
     * @return
     * @throws Exception
     */
    @Path("/savefaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String saveFaults(List<Fault> lsFault) throws Exception {
        String returnMessage = "";
        String userName = "";
        try {
            if (lsFault != null && lsFault.size() > 0) {
                for (Fault fault : lsFault) {
                    userName = fault.getUsername();
                    getFIBugService().saveFaults(fault);
                }
                faultUtils.saveCaching(userName, lsFault);
                returnMessage = "Fault(s) injected successfully";
            } else {
                returnMessage = "No Fault received";
            }

        } catch (Exception ex) {
            log.error(ex);
        }
        return returnMessage;
    }

    /**
     * Read the faults based on user
     *
     * @param req
     * @return fault information
     * @throws Exception
     */
    @Path("/readfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> readFaultsByUser(@Context HttpServletRequest req) throws Exception {
        String userName = req.getHeader("USERNAME");
        List<Fault> lsFault = new ArrayList<Fault>();
        try {
            if (!StringUtils.isBlank(userName)) {
                lsFault = getFIBugService().getAllFaultsByUser(userName);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }

    /**
     * Read all the faults
     *
     * @return
     * @throws Exception
     */
    @Path("/readallfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> readAllFaults() throws Exception {
        List<Fault> lsFault = new ArrayList<Fault>();
        try {
            lsFault = getFIBugService().getAllFaults();
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFault;
    }

    /**
     * Stop the injected faults based on user
     *
     * @param req
     * @return deleted rows
     * @throws Exception
     */
    @Path("/stopfaults")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int stopFaultsByUser(@Context HttpServletRequest req) throws Exception {
        String userName = req.getHeader("USERNAME");
        String faultName = req.getHeader("FAULTNAME");
        int deletedRows = 0;
        try {
            deletedRows = getFIBugService().deleteFaults(userName, faultName);
            faultUtils.deleteCaching(userName, faultName);
        } catch (Exception ex) {
            log.error(ex);
        }
        return deletedRows;
    }

    /**
     * Inject faults based on time frame in load-gen
     *
     * @param req
     * @return
     * @throws Exception
     */
    @Path("/getfaults")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fault> getFaultsBeforeInjecting(@Context HttpServletRequest req) throws Exception {

        String username = req.getHeader("USERNAME");
        List<Fault> lsFaultReturn = new ArrayList<>();
        List<Fault> lsFault = new ArrayList<>();
        try {
            FaultUtils faultUtils = new FaultUtils();
            log.info(username);
            if (!StringUtils.isBlank(username)) {
                if (faultUtils.readCaching(username) != null) {
                    lsFault = faultUtils.readCaching(username);
                } else {
                    lsFault = getFIBugService().getAllFaultsByUser(username);
                }
            }
            for (Fault fault : lsFault) {
                if(faultUtils.checkTime(fault.getTimeframe()))
                    lsFaultReturn.add(fault);
            }
        } catch (Exception ex) {
            log.error(ex);
        }
        return lsFaultReturn;
    }

    /**
     * Inject the faults now/ Saved faults
     *
     * @return
     * @throws Exception
     */
    @Path("/injectfaults")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String injectFaults(final List<Fault> lsFault) throws Exception {
        try {

            if(lsFault == null || lsFault.size() <= 0)
                return "Fault List is Empty. No Faults being injected.";

            Thread.sleep(30000);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String result = veryExpensiveOperation();
                        //asyncResponse.resume(result);
                    }catch (Exception ex){
                        log.error(ex.getMessage());
                    }
                }

                private String veryExpensiveOperation() {
                    if (lsFault != null && lsFault.size() > 0) {
                        return faultUtils.injectFault(lsFault);
                    }
                    return "Fault List is Empty. No Faults being injected.";
                }
            }).start();

        } catch (Exception ex) {
            log.error(ex);
        }
        return "Faults Injected Successfully. Please give it sometime to reflect in controller.";
    }

}
