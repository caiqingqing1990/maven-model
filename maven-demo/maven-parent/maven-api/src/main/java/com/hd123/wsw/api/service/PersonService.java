package com.hd123.wsw.api.service;

import com.hd123.wsw.api.entity.Person;


public interface PersonService {
  public String getAllPerson(String start, String username);
  public void savePerson(Person person);
  public void updatePerson(Person person);
  public void deletePerson(String ids);
  public int getTotalCounts(String condition);
  public Person findPersonById(int id);
}
