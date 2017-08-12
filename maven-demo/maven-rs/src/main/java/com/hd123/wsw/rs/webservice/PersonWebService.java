package com.hd123.wsw.rs.webservice;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hd123.wsw.api.entity.Person;

@Path(value = "/person")
@Produces(value = {
  MediaType.APPLICATION_JSON })
@Consumes(value = {
  MediaType.APPLICATION_JSON })
public interface PersonWebService {

  @POST
  @Path(value = "/queryall")
  @Consumes("application/x-www-form-urlencoded")
  public String queryall(@FormParam("start")
  String start, @FormParam("username")
  String username) throws Exception;

  @POST
  @Path(value = "/query")
  @Consumes("application/x-www-form-urlencoded")
  public Person query(@FormParam("id")
  Integer id) throws Exception;

  @POST
  @Path(value = "/add")
  @Consumes("application/x-www-form-urlencoded")
  public void add(@BeanParam
  Person person) throws Exception;

  @POST
  @Path(value = "/delete/")
  @Consumes("application/x-www-form-urlencoded")
  public void delete(@FormParam("ids")
  String ids) throws Exception;

  @POST
  @Path(value = "/update")
  @Consumes("application/x-www-form-urlencoded")
  public void update(@BeanParam
  Person person) throws Exception;

}
