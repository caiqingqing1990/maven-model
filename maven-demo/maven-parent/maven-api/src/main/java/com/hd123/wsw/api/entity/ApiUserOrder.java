package com.hd123.wsw.api.entity;

public class ApiUserOrder {
  private Integer id;
  private Integer quantity;
  private String pro_name;
  private Integer userId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getPro_name() {
    return pro_name;
  }

  public void setPro_name(String pro_name) {
    this.pro_name = pro_name;
  }

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
