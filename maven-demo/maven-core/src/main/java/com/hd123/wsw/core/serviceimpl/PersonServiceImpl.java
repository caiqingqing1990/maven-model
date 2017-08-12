package com.hd123.wsw.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import com.hd123.wsw.api.entity.Person;
import com.hd123.wsw.api.service.PersonService;
import com.hd123.wsw.core.dao.PersonDao;
import com.hd123.wsw.core.entity.PersonUtil;
import com.hd123.wsw.core.utils.JSONUtil;
import com.hd123.wsw.core.utils.PageUtil;

public class PersonServiceImpl implements PersonService {
  @Resource(name = "personDao")
  private PersonDao personDao;

  public String getAllPerson(String start, String username) {
    String condition = "";
    int currentPage = (Integer.parseInt(start)) / 8 + 1;
    //构建查询条件
    if (username != null && !username.trim().equals("")) {
      condition = "  username like '%" + username + "%'";
    }
    int rows = personDao.getTotalCounts(condition);
    PageUtil page = new PageUtil(rows, currentPage);
    List<PersonUtil> personList = personDao.getAllPerson(page, condition);
    //将查询结果转换为api模块的对象集合类型
    String json = null;
    if(personList.size()!=0){
      List<Person> list = new ArrayList<Person>();
      for (int i = 0; i < personList.size(); i++) {
        PersonUtil personUtil = personList.get(i);
        Person person = new Person();
        BeanUtils.copyProperties(personUtil, person);
        list.add(person);
      }
      json = JSONUtil.getPersonString(list, rows);
    }
    return json;
  }

  public void savePerson(Person person) {
    PersonUtil personUtil = new PersonUtil();
    BeanUtils.copyProperties(person, personUtil);
    personDao.savePerson(personUtil);
  }

  public void updatePerson(Person person) {
    PersonUtil personUtil = new PersonUtil();
    BeanUtils.copyProperties(person, personUtil);
    personDao.updatePerson(personUtil);
  }

  public void deletePerson(String ids) {
    personDao.deletePerson(ids);
  }

  public int getTotalCounts(String condition) {
    return personDao.getTotalCounts(condition);
  }

  public Person findPersonById(int id) {
    PersonUtil personUtil = personDao.findPersonById(id);
    Person person = new Person();
    BeanUtils.copyProperties(personUtil, person);
    return person;
  }

}
