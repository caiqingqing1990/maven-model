package com.hd123.wsw.rs.webserviceimpl;

import com.hd123.wsw.api.service.UserService;
import com.hd123.wsw.rs.webservice.UserWebService;

public class UserWebServiceImpl implements UserWebService {
  private UserService userService;
  
  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public String query(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return userService.findUserById(id);
  }

  @Override
  public void delete(Integer id1, Integer id2) throws Exception {
    // TODO Auto-generated method stub
    userService.deleteUser(id1, id2);
  }

  @Override
  public String queryunion(Integer id, String sex) throws Exception {
    // TODO Auto-generated method stub
    return userService.findUserAndOrder(id, sex);
  }

  @Override
  public void callProcedure() throws Exception {
    // TODO Auto-generated method stub
    userService.callProcedure();
  }

  @Override
  public String getusers() throws Exception {
    // TODO Auto-generated method stub
    return userService.getUsers();
  }

}
