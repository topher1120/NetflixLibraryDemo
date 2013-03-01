package com.cmware.ws;

import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/echo")
@ManagedResource(objectName = "bean:name=echoResource", log = true, description = "The Echo Resource")
public class EchoResource {

    private static Logger LOG = LoggerFactory.getLogger("c.c.w.EchoResource");

    private AtomicInteger currentRequests = new AtomicInteger(0);

    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Object echo(@QueryParam("value") String value) {
        currentRequests.incrementAndGet();
        LOG.debug("echoing request, value: {}", value);

        // for testing, delay response for 1 second.
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //
        // }
        currentRequests.decrementAndGet();
        return value;
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject echoJson(@QueryParam("value") String value) {
        currentRequests.incrementAndGet();
        LOG.debug("json echo request, value: {}", value);
        try {
            JSONObject jsonValue = new JSONObject();

            jsonValue.put("value", value);
            return jsonValue;
        } catch (JSONException e) {
            LOG.error("Error creating JSON: {}", e.getMessage());
            throw new WebApplicationException(Response.serverError().entity("Error creating JSON response").build());
        } finally {
            currentRequests.decrementAndGet();
        }
    }

    @ManagedAttribute(description = "The current number of requests for this service resource.")
    public Integer getCurrentRequests() {
        return currentRequests.get();
    }

}
