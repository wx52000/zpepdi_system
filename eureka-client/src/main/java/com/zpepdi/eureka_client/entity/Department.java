package com.zpepdi.eureka_client.entity;

public class Department {

    private Integer id;

    private String name;

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

  public Department(String name) {
    this.name = name;
  }
}
