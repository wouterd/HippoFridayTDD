package com.example

import groovy.json.JsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

  /**
   * Method handling HTTP GET requests. The returned object will be sent
   * to the client as "text/plain" media type.
   *
   * @return String that will be returned as a text/plain response.
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public static String getIt() {
    return "Got it!";
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public static String someJson() {
    new JsonBuilder([
        foo   : 'bar',
        number: 1337
    ]).toPrettyString()
  }

}
