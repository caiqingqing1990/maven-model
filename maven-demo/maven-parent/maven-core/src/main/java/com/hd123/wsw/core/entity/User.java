package com.hd123.wsw.core.entity;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Fuser")
public class User {

  private Integer id;

  private String username;

  private String sex;

  private String email;

  private Date birthday;

  private float salary;

  private BigDecimal account;
  
  private List<UserOrder> orderList;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", length = 11, nullable = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "username", length = 50, nullable = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "sex", length = 10, nullable = false)
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Column(name = "email", length = 50, nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "birthday", nullable = true)
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  @Column(name = "salary")
  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }

  @Column(name = "account", precision = 19, scale = 2)
  public BigDecimal getAccount() {
    return account;
  }

  public void setAccount(BigDecimal account) {
    this.account = account;
  }
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  public List<UserOrder> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<UserOrder> orderList) {
    this.orderList = orderList;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", email=" + email
        + ", birthday=" + birthday + ", salary=" + salary + ", account=" + account + ", orderList="
        + orderList + "]";
  }

  

}

