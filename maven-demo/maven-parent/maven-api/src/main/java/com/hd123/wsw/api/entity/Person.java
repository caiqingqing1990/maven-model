package com.hd123.wsw.api.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "query-definition")
public class Person {
  @FormParam("id")
  private Integer id;
  @FormParam("username")
  private String username;
  @FormParam("sex")
  private String sex;
  @FormParam("email")
  private String email;
  @FormParam("birthday")
  private Date birthday;
  @FormParam("salary")
  private float salary;
  @FormParam("account")
  private BigDecimal account;
 
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
 
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
 
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public float getSalary() {
    return salary;
  }
  public void setSalary(float salary) {
    this.salary = salary;
  }
  public BigDecimal getAccount() {
    return account;
  }
  public void setAccount(BigDecimal account) {
    this.account = account;
  }
  @Override
  public String toString() {
    return "Person [id=" + id + ", username=" + username + ", sex=" + sex + ", email=" + email
        + ", birthday=" + birthday + ", salary=" + salary + ", account=" + account + "]";
  }

  
  
}
