package com.hd123.wsw.core.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hd123.wsw.core.entity.PersonUtil;
import com.hd123.wsw.core.utils.PageUtil;

public class PersonDao {
  @Resource(name = "entityManagerFactory")
  protected EntityManagerFactory EntityManagerFactory;

  /**
   * 查询person列表
   * 分页
   * @param page
   * @param condition
   * @return
   */
  public List<PersonUtil> getAllPerson(PageUtil page, String condition) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    List<PersonUtil> list = null;
    try {
      int pageNo = page.getCurPageNo();
      int end = (pageNo - 1) * 8;
      String hql = "FROM PersonUtil a order by a.id";
      if (condition != null && !condition.trim().equals("")) {
        hql = "FROM PersonUtil a where " + condition + " order by id";
      }
      Query query = em.createQuery(hql);
      query.setFirstResult(end);
      query.setMaxResults(8);
      list = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    return list;

  }

  /**
   * 新增person记录
   * 
   * @param person
   */
  public void savePerson(PersonUtil person) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(person);
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
   * 更新person记录
   * 
   * @param person
   */
  public void updatePerson(PersonUtil person) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    try {
      em.getTransaction().begin();
      PersonUtil personTest = em.find(PersonUtil.class, person.getId());
      personTest.setEmail(person.getEmail());
      personTest.setAccount(person.getAccount());
      personTest.setBirthday(person.getBirthday());
      personTest.setSalary(person.getSalary());
      personTest.setSex(person.getSex());
      personTest.setUsername(person.getUsername());
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
   * 批量删除
   * 
   * @param ids
   */
  public void deletePerson(String ids) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    try {
      em.getTransaction().begin();
      if (ids.indexOf(",") > 0) {
        String[] idArray = ids.split(",");
        for (int i = 0; i < idArray.length; i++) {
          int id = Integer.parseInt(idArray[i]);
          PersonUtil personUtil = em.find(PersonUtil.class, id);
          em.remove(personUtil);
        }
      } else {
        int id = Integer.parseInt(ids);
        PersonUtil personUtil = em.find(PersonUtil.class, id);
        em.remove(personUtil);
      }
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
   * 查询总记录数
   * 
   * @param condition
   * @return
   */
  public int getTotalCounts(String condition) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    String hql = "select count(*) from PersonUtil";
    int i = 0;
    try {
      if (condition != null && !condition.equals("")) {
        hql += "  where  ";
        hql += condition;
      }
      Query query = em.createQuery(hql);
      i = Integer.parseInt(query.getResultList().get(0).toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    return i;
  }

  /**
   * 根据id查询person记录
   * 
   * @param id
   * @return
   */
  public PersonUtil findPersonById(int id) {
    EntityManager em = EntityManagerFactory.createEntityManager();
    PersonUtil person = null;
    try {
      String hql = "from PersonUtil where id=" + id;
      person = (PersonUtil) em.createQuery(hql).getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    return person;
  }
}
