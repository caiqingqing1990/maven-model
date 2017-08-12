package com.hd123.wsw.core.serviceimpl;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hd123.wsw.api.service.UserService;
import com.hd123.wsw.core.dao.UserDao;
import com.hd123.wsw.core.entity.User;
import com.hd123.wsw.core.entity.UserUtil;
import com.hd123.wsw.core.utils.JSONUtil;

public class UserServiceImpl implements UserService {
  @Resource(name = "userDao")
  private UserDao userDao;

  @Override
  public String findUserById(Integer id) {
    // TODO Auto-generated method stub
    User user = userDao.findUserById(id);
   //将查询出来的对象装换为json格式的字符串
    String json = JSONUtil.objectToJson(user); 
    return json;
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
  public void deleteUser(Integer id1, Integer id2) {
    // TODO Auto-generated method stub
    userDao.deleteUser(id1);
    //故意设置的错误，检验编程式事务是否起作用
    int a = 3/0;
    userDao.deleteUser(id2);
  }
  @Override
  public String findUserAndOrder(Integer id, String sex) {
    // list集合转化为json字符串
    List<UserUtil> userList = userDao.findUserAndOrder(id, sex);
    String json = JSONUtil.listToJson(userList);
    return json;
  }

  @Override
  public void callProcedure() {
    // TODO Auto-generated method stub
    userDao.callProcedure();
  }

  @Override
  public String getUsers() {
    // TODO Auto-generated method stub
    List<User> userList = userDao.getUsers();
    String json = JSONUtil.listToJson(userList);
    return json;
  }

}
