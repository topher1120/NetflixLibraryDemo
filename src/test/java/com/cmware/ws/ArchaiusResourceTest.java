package com.cmware.ws;

import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for The ArchaiusResource object.
 */
public class ArchaiusResourceTest {

    private ArchaiusResource resource;

    @Before
    public void setUp() throws Exception {
        resource = new ArchaiusResource();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProperty() throws Exception {
        JSONObject expected = new JSONObject();
        expected.put("sampleProperty", "Hello");

        assertEquals("property", expected.toString(), resource.getProperty().toString());
    }
}
