package com.zpepdi.feign_service.entity;

public class PrincipalWorkday {

  private Integer id;

  private String name;

  private Double manage_workday;

  private Double workday;

  private Integer group;

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

  public Double getManage_workday() {
    return manage_workday;
  }

  public void setManage_workday(Double manage_workday) {
    this.manage_workday = manage_workday;
  }

  public Double getWorkday() {
    return workday;
  }

  public void setWorkday(Double workday) {
    this.workday = workday;
  }

  public Integer getGroup() {
    return group;
  }

  public void setGroup(Integer group) {
    this.group = group;
  }
}
