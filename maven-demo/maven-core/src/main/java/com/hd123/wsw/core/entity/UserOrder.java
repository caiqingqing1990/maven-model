package com.hd123.wsw.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "userorder")
public class UserOrder {
  private Integer id ;
  private Integer quantity;
  private String pro_name;
  private Integer userId;
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  @Column(name = "quantity", nullable = false)
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  @Column(name = "pro_name", nullable = false)
  public String getPro_name() {
    return pro_name;
  }
  public void setPro_name(String pro_name) {
    this.pro_name = pro_name;
  }
  @Column(name = "userId", nullable = false)
  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  @Override
  public String toString() {
    return "UserOrder [id=" + id + ", quantity=" + quantity + ", pro_name=" + pro_name
        + ", userId=" + userId + "]";
  }

  
  
}
