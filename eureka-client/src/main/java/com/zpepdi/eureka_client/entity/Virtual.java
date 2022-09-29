package com.zpepdi.eureka_client.entity;

import java.util.List;
import java.util.Map;

public class Virtual {
  private Integer id;

  private String name;

  private String number;

  private double workday;

  private List<Map<String,Object>> general;


  private List<Map<String,Object>> principal;


  private String createDate;

  private String createUser;

  private String month;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public double getWorkday() {
    return workday;
  }

  public void setWorkday(double workday) {
    this.workday = workday;
  }

  public List<Map<String, Object>> getGeneral() {
    return general;
  }

  public void setGeneral(List<Map<String, Object>> general) {
    this.general = general;
  }

  public List<Map<String, Object>> getPrincipal() {
    return principal;
  }

  public void setPrincipal(List<Map<String, Object>> principal) {
    this.principal = principal;
  }


  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }
}
