package com.hd123.wsw.rs.webserviceimpl;

import com.hd123.wsw.api.entity.Person;
import com.hd123.wsw.api.service.PersonService;
import com.hd123.wsw.rs.webservice.PersonWebService;

public class PersonWebServiceImpl implements PersonWebService{
  
  private PersonService personService;
  
  public PersonService getPersonService() {
    return personService;
  }

  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  @Override
  public String queryall(String start, String username) throws Exception {
    // TODO Auto-generated method stub
    return personService.getAllPerson(start, username);
  }

  @Override
  public Person query(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return personService.findPersonById(id);
  }

  @Override
  public void add(Person person) throws Exception {
    // TODO Auto-generated method stub
    personService.savePerson(person);
  }

  @Override
  public void delete(String ids) throws Exception {
    // TODO Auto-generated method stub
    personService.deletePerson(ids);
  }

  @Override
  public void update(Person person) throws Exception {
    // TODO Auto-generated method stub
    personService.updatePerson(person);
  }

}
