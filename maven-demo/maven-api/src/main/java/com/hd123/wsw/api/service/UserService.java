package com.hd123.wsw.api.service;

import java.util.List;



public interface UserService {
  public String findUserById(Integer id);
  public void deleteUser(Integer id1,Integer id2);
  public String findUserAndOrder(Integer id,String sex);
  public void callProcedure();
  public String getUsers();
}
