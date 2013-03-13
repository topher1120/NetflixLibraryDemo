package com.cmware.ws;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST resource endpoint demonstrating Hystrix functionality.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Path("/hystrix")
public class HystrixResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getData(@QueryParam("type") String searchType){
        return new JSONObject();
    }

}
