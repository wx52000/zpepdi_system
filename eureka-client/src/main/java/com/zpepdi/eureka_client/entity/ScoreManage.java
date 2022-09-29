package com.zpepdi.eureka_client.entity;

public class ScoreManage {

  private Integer id;

  private Integer scoreId;

  private String scoredId;

  private String state;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getScoreId() {
    return scoreId;
  }

  public void setScoreId(Integer scoreId) {
    this.scoreId = scoreId;
  }

  public String getScoredId() {
    return scoredId;
  }

  public void setScoredId(String scoredId) {
    this.scoredId = scoredId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
