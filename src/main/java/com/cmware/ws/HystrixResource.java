package com.cmware.ws;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * REST resource endpoint demonstrating Hystrix functionality.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/hystrix")
public class HystrixResource {

    private static Logger log = LoggerFactory.getLogger(HystrixResource.class);
    private DateTimeFormatter formatter = ISODateTimeFormat.basicTimeNoMillis();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getData(@QueryParam("type") String searchType){
        log.info("getData, searchType: {}", searchType);
        JSONObject result = new JSONObject();
        try {
            result.put("status", "success");
            result.put("value", "Successful request at "+getFormattedTime());
            return result;
        } catch (JSONException e) {
            log.error("failed to make JSON response and return: {}", e.getMessage());
        }

        throw new WebApplicationException();
    }

    private String getFormattedTime(){
        return DateTime.now().toString(formatter);
    }
}
