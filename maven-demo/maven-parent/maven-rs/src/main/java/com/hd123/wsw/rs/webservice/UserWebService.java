package com.hd123.wsw.rs.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;

import com.hd123.wsw.api.entity.ApiUser;
import com.hd123.wsw.api.entity.Person;

@Path(value = "/user")
@Produces(value = {
  MediaType.APPLICATION_JSON })
@Consumes(value = {
  MediaType.APPLICATION_JSON })
public interface UserWebService {
  @GET
  @Path(value = "/queryUser/{id}")
  public String query(@PathParam("id")
  Integer id) throws Exception;

  @GET
  @Path(value = "/delete/{id1}+{id2}")
  public void delete(@PathParam("id1")
  Integer id1, @PathParam("id2")
  Integer id2) throws Exception;

  @GET
  @Path(value = "/queryUnion/{id}+{sex}")
  public String queryunion(@PathParam("id")
  Integer id, @PathParam("sex")
  String sex) throws Exception;

  @GET
  @Path(value = "/callProcedure")
  public void callProcedure() throws Exception;

  @GET
  @Path(value = "/getUsers")
  public String getusers() throws Exception;
}
