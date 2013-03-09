package com.cmware.ws;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import com.netflix.servo.monitor.Monitors;
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
import java.util.concurrent.atomic.AtomicLong;

/**
 * Resource dedicated to provide REST based access to a set of properties managed and populated by Archaius.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/archaius")
public class ArchaiusResource {

    private static final Logger log = LoggerFactory.getLogger(ArchaiusResource.class);
    private DynamicStringProperty sampleProperty;

    @Monitor(name="requestCount", type= DataSourceType.COUNTER)
    private AtomicLong requestCounter = new AtomicLong(0);

    public ArchaiusResource() {
        sampleProperty = DynamicPropertyFactory.getInstance().getStringProperty("sampleProperty", "Hello world!");
        Monitors.registerObject(this);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getProperties() {

        requestCounter.incrementAndGet();
        JSONObject jsonValue = new JSONObject();
        try {
            jsonValue.put("sampleProperty", sampleProperty.get());
            return jsonValue;
        } catch (JSONException e) {
            log.error("Failed to put json value and send back: {}", e.getMessage());
        }

        throw new WebServiceException();
    }
}
