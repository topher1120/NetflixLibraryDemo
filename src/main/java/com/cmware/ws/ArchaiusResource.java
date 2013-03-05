package com.cmware.ws;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;

/**
 * Resource dedicated to provide REST based access to a set of properties managed and populated by Archaius.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/archaius")
public class ArchaiusResource {

    private static final Logger log = LoggerFactory.getLogger(ArchaiusResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getProperty(){
        JSONObject jsonValue = new JSONObject();
        try {
            jsonValue.put("sampleProperty", "Hello");
            return jsonValue;
        } catch (JSONException e) {
            log.error("Failed to put json value and send back: {}", e.getMessage());
        }

        throw new WebServiceException();
    }
}
