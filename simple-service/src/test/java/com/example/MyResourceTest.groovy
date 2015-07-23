package com.example

import groovy.json.JsonSlurper
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test

import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType

import static org.junit.Assert.assertEquals

public class MyResourceTest extends JerseyTest {

  @Override
  protected Application configure() {
    new ResourceConfig(MyResource)
  }

  @Test
  public void "myresource should return 'Got it!'"() {
    def responseMsg = target("myresource").request().get(String)
    assertEquals "Got it!", responseMsg
  }

  @Test
  public void "Json Endpoint should return a string and number"() throws Exception {
    def result = target("myresource").request(MediaType.APPLICATION_JSON_TYPE).get(String)
    def object = new JsonSlurper().parseText(result)

    assertEquals 'bar', object.foo
    assertEquals 1337, object.number
  }
}
