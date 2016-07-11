package com.appdynamicspilot.util;

import com.appdynamicspilot.faultinjection.FaultInjection;
import com.appdynamicspilot.faultinjection.FaultInjectionFactory;
import com.appdynamicspilot.model.Fault;
import com.appdynamicspilot.service.FaultService;
import com.appdynamicspilot.service.FaultServiceInterface;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by swetha.ravichandran on 7/2/15.
 */
public class FaultUtils {

    private static final Logger log = Logger.getLogger(FaultUtils.class.getName());

    /**
     * Injects Faults
     *
     * @param lsFault - List of faults available
     */
    public String injectFault(List<Fault> lsFault) {
        for (Fault fault : lsFault) {
            return instantiateFault(fault);
        }
        return "Fault list is empty";
    }

    /**
     * Helper for time frame parser and comparison
     *
     * @param timeFrame
     * @return
     * @throws Exception
     */
    public boolean checkTime(String timeFrame) {

        //Parsing the date according to Hours, Minutes set on the UI and setting the Locale to US.
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm", Locale.US);

        String startTimeString = timeFrame.substring(0, 5);
        String endTimeString = timeFrame.substring(8);
        String currentTime = formatDateToString(new Date(), "HH:mm", "UTC");

        try {
            //Variables used for comparison with current date in the parsed format.
            Date parsedStartTime = parser.parse(startTimeString);
            log.info("parsedStartTime" + parsedStartTime.toString());
            Date parsedEndTime = parser.parse(endTimeString);
            log.info("parsedEndTime" + parsedEndTime.toString());
            Date parsedCurrentTime = parser.parse(currentTime);
            log.info("parsedCurrentTime" + parsedCurrentTime.toString());
            log.info(parsedCurrentTime.after(parsedStartTime) && parsedCurrentTime.before(parsedEndTime));

            //returns only if the time is within the time range selected on the UI.
            if (parsedCurrentTime.after(parsedStartTime) && parsedCurrentTime.before(parsedEndTime)) {
                return true;
            }
        } catch (ParseException e) {
            log.error(e);
        }
        return false;
    }

    /**
     * Utility function to convert java Date to TimeZone format
     *
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    private String formatDateToString(Date date, String format,
                                      String timeZone) {
        // null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // default system timezone if passed null or empty
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        // set timezone to SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

    /**
     * Instantiate the fault
     *
     * @param fault
     */
    private String instantiateFault(Fault fault) {
        //Creating Fault injection object parsing the bugName removing spaces.
        FaultInjectionFactory fiFactory = new FaultInjectionFactory();
        FaultInjection fi = fiFactory.getFaultInjection(fault.getBugname().replace(" ", ""));
        if (fi != null) {
            return fi.injectFault();
        }
        return "Fault Name is appropriate";
    }

    /**
     * Save Caching
     *
     * @param userName
     * @param lsFault
     */
    public void saveCaching(String userName, List<Fault> lsFault) {
        //Check if cache already exists
        if (CacheManager.getInstance().get(userName + "faultCache") != null) {
            List<Fault> lsFaultFromCache = (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
            if (lsFaultFromCache.size() > 0) {
                //If yes, get the existing list and add it to the newly created list
                for (Fault fault : lsFault) {
                    lsFaultFromCache.add(fault);
                }
                CacheManager.getInstance().clear(userName + "faultCache");
                CacheManager.getInstance().put(userName + "faultCache", lsFaultFromCache);
            }
        } else {
            CacheManager.getInstance().clear(userName + "faultCache");
            CacheManager.getInstance().put(userName + "faultCache", lsFault);
        }
    }

    /**
     * Read Caching
     *
     * @param userName
     * @return
     */
    public List<Fault> readCaching(String userName) {
        if (CacheManager.getInstance().get(userName + "faultCache") != null)
            return (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
        else
            return null;
    }

    /**
     * Delete Caching
     *
     * @param userName
     * @param faultName
     */
    public void deleteCaching(String userName, String faultName) {

        if (CacheManager.getInstance().get(userName + "faultCache") != null) {
            List<Fault> lsFaultFromCache = (List<Fault>) CacheManager.getInstance().get(userName + "faultCache");
            if (lsFaultFromCache.size() > 0) {
                for (int i = 0; i < lsFaultFromCache.size(); i++) {
                    if (lsFaultFromCache.get(i).getUsername().equals(userName.trim()) && lsFaultFromCache.get(i).getBugname().equals(faultName.trim())) {
                        lsFaultFromCache.remove(i);
                    }
                }
                CacheManager.getInstance().clear(userName + "faultCache");
                CacheManager.getInstance().put(userName + "faultCache", lsFaultFromCache);
            }
        }
    }

}
