package com.zpepdi.eureka_client.entity;

public class UserScore {

    private Integer id;

//    private Integer gsId;

    private Integer gradeId;

    private Integer scoreId;

    private Double designer;

    private Double personal;

    private Double coordinate;

    private Long date;

    private Integer role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getGradeId() {
      return gradeId;
    }

    public void setGradeId(Integer gradeId) {
      this.gradeId = gradeId;
    }

    public Integer getScoreId() {
      return scoreId;
    }

  public void setScoreId(Integer scoreId) {
    this.scoreId = scoreId;
  }

  public Double getDesigner() {
        return designer;
    }

    public void setDesigner(Double designer) {
        this.designer = designer;
    }

    public Double getPersonal() {
        return personal;
    }

    public void setPersonal(Double personal) {
        this.personal = personal;
    }

    public Double getCoordinate() {

        return coordinate;
    }

    public void setCoordinate(Double coordinate) {
        this.coordinate = coordinate;
    }

    public Long getDate() {
      return date;
    }

    public void setDate(Long date) {
      this.date = date;
    }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }
}
