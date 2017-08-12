package com.hd123.wsw.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hd123.wsw.core.entity.User;
import com.hd123.wsw.core.entity.UserUtil;

public class UserDao {
  @Resource(name = "entityManagerFactory")
  protected EntityManagerFactory EntityManagerFactory;
  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;
  /**
   * 一对多映射练习
   * 
   * @param id
   * @return
   */
  public User findUserById(Integer id) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    User user = null;
    try {
      String hql = "from User where id=" + id;
      user = (User) em.createQuery(hql).getSingleResult();
      // 强制加载一对多关联数据，否则会报错
      Hibernate.initialize(user.getOrderList());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    return user;
  }

  /**
   * 编程式事务练习
   * 
   * @param id
   */
  public void deleteUser(Integer id) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    try {
      // em.getTransaction().begin();
      User user = em.find(User.class, id);
      em.remove(user);
      // em.getTransaction().commit();
    } catch (Exception e) {
      try {
        // em.getTransaction().rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } finally {
      em.close();
    }
  }

  /**
   * hql多表查询练习
   * 
   * @param id
   * @param sex
   * @return
   */
  public List<UserUtil> findUserAndOrder(Integer id, String sex) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    List<UserUtil> userList = null;
    try {
      String hql = "select a.id,a.username,a.sex,a.email,a.birthday,a.salary,a.account,b.quantity,b.pro_name"
          + " from User a, UserOrder b where a.id = b.userId and a.sex = '"
          + sex
          + "' and  a.id="
          + id;
      Query query = em.createQuery(hql);
      userList = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    return userList;
  };

  /**
   * 调用存储过程练习
   */
  public void callProcedure() {
    EntityManager em = EntityManagerFactory.createEntityManager();
    try {
      em.getTransaction().begin();
      //调用无参过程
     /* Query query = em.createNativeQuery("{call insert_test()}");
      query.executeUpdate();*/
      //调用有参过程
      Query query1 = em.createNativeQuery("{call querytest(?)}");
      query1.setParameter(1, new Integer(1));
      query1.executeUpdate();
      em.getTransaction().commit();
    } catch (Exception e) {
      try {
        em.getTransaction().rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } finally {
      em.close();
    }
  }
  /**
   * template练习
   * @return
   */
  public List<User> getUsers() {
    List<User> list = jdbcTemplate.query("select * from fuser", new RowMapper() {
      public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setAccount(rs.getBigDecimal("account"));
        user.setBirthday(rs.getDate("birthday"));
        user.setEmail(rs.getString("email"));
        user.setSalary(rs.getFloat("salary"));
        user.setSex(rs.getString("sex"));
        return user;
      }
    });
    return list;
  }
}
