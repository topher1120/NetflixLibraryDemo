package com.cmware.ws;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

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
    public JSONObject getData(@QueryParam("type") String searchType) {
        log.info("getData, searchType: {}", searchType);
        JSONObject result = new JSONObject();
        try {
            Future<Map<String, String>> resultFuture = new HystrixDemoCommand(searchType).queue();
            Map<String, String> resultItems = resultFuture.get();

            for (Map.Entry<String, String> entry : resultItems.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }

            return result;
        } catch (JSONException e) {
            log.error("failed to make JSON response and return: {}", e.getMessage());
        } catch (Exception e) {
            log.error("failed to make response: {}", e.getMessage());
        }

        throw new WebApplicationException();
    }

    private String getFormattedTime() {
        return DateTime.now().toString(formatter);
    }

    class HystrixDemoCommand extends HystrixCommand<Map<String, String>> {

        private String commandType;

        public HystrixDemoCommand(String commandType) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("demoCommand"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionIsolationThreadTimeoutInMilliseconds(5000)));
            this.commandType = commandType;
        }

        @Override
        protected Map<String, String> run() throws Exception {
            if ("normal".equals(commandType)) {
                return buildResult("success", "Successful request at " + getFormattedTime());
            }

            if ("delay".equals(commandType)) {
                Thread.sleep(20000);
                return buildResult("success", "Delayed successful request at " + getFormattedTime());
            }

            throw new RuntimeException("failed");
        }

        @Override
        protected Map<String, String> getFallback() {
            return buildResult("error", "Fallback for request at " + getFormattedTime());
        }

        private Map<String, String> buildResult(String status, String value) {
            Map<String, String> result = new HashMap<String, String>();
            result.put("type", status);
            result.put("content", value);
            return result;
        }
    }
}
