package com.cmware.ws;

import com.netflix.config.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * Test class for The ArchaiusResource object.
 */
public class ArchaiusResourceTest {

    private ArchaiusResource resource;
    private AbstractPollingScheduler scheduler;

    @Before
    public void setUp() throws Exception {
        BaseConfiguration config = new BaseConfiguration();
        config.setProperty("sampleProperty", "Hello John Doe");
        ConcurrentMapConfiguration dynamicConfig = new ConcurrentMapConfiguration(config);
        ConfigurationManager.install(config);
        resource = new ArchaiusResource();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProperty() throws Exception {
        JSONObject expected = new JSONObject();
        expected.put("sampleProperty", "Hello John Doe");

        assertEquals("property", expected.toString(), resource.getProperties().toString());
    }
}
