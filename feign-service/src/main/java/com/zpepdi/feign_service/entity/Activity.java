package com.zpepdi.feign_service.entity;

import java.util.List;
import java.util.Map;

public class Activity {
  private Integer id;

  private String number;

  private String name;

  private double workday;

  private List<String> date;

  private String start_date;

  private String end_date;

  private Integer state;

  private List<Map<String,Object>> general;

  private List<Map<String,Object>> principal;

  private String createDate;

  private String createUser;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getWorkday() {
    return workday;
  }

  public void setWorkday(double workday) {
    this.workday = workday;
  }

  public List<String> getDate() {
    return date;
  }

  public void setDate(List<String> date) {
    this.date = date;
  }

  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
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
}
