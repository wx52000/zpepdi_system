package com.zpepdi.feign_service.entity;



public class ExcelProject {

    private Integer pid ;

    private Integer vid;


    private String number;

    private String volumeName;

    private String projectName;

    private Integer tid;

    private String tec;


    private String plannedPublicationDate;

    private String actualPublicationDate;

    private String planned_shot_date;


    private String shotDate;

    private String proofreading_date;

    private String complete_time;

    private Integer designerId;

    private String designer;

    private String designerLastMonth;

    private String designerOne;

    private String designerOneRemark;


    private String designerTwo;

    private String DesignerTwoRemark;

    private String designerThree;

    private String designerThreeRemark;

    private String designerFour;

    private String designerFourRemark;

    private String designerFive;

    private String designerFiveRemark;

    private Integer checkerId;

    private String checker;

    private String checkerCompletionDate;

    private String checkerLastMonth;

    private String checkerLastWeek;

    private String checkerNowWeek;

    private String checkerRemark;


    private Integer principalId;
    private String principal;

//    @ExcelProperty(value = "完成日期" ,index = 28)
//    private String principalCompletionDate;

    private String principalLastWeek;

    private String principalNowWeek;

    private String principalRemark;
//
//    private Integer headmanId;
//    @ExcelProperty(value = "组长校" ,index = 32)
//    private String headman;
//
//    @ExcelProperty(value = "完成日期" ,index = 33)
//    private String headmanCompletionDate;
//
//    @ExcelProperty(value = "上周完成比例(%)" ,index = 34)
//    private String headmanLastWeek;
//
//    @ExcelProperty(value = "本周完成比例(%)" ,index = 35)
//    private String headmanNowWeek;
//
//    @ExcelProperty(value = "延期原因" ,index = 36)
//    private String headmanRemark;
//
//    @ExcelProperty(value = "备注" ,index = 37)
//    private String remark;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName.trim();
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }


    public String getPlannedPublicationDate() {
        return plannedPublicationDate;
    }

    public void setPlannedPublicationDate(String plannedPublicationDate) {
        this.plannedPublicationDate = plannedPublicationDate;
    }

    public String getActualPublicationDate() {
        return actualPublicationDate;
    }

    public void setActualPublicationDate(String actualPublicationDate) {
        this.actualPublicationDate = actualPublicationDate;
    }

    public String getPlanned_shot_date() {
      return planned_shot_date;
    }

    public void setPlanned_shot_date(String planned_shot_date) {
      this.planned_shot_date = planned_shot_date;
    }

    public String getProofreading_date() {
      return proofreading_date;
    }

    public void setProofreading_date(String proofreading_date) {
      this.proofreading_date = proofreading_date;
    }

    public String getComplete_time() {
      return complete_time;
    }

    public void setComplete_time(String complete_time) {
      this.complete_time = complete_time;
    }

   public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getShotDate() {
        return shotDate;
    }

    public void setShotDate(String shotDate) {
        this.shotDate = shotDate;
    }

    public String getDesignerLastMonth() {
        return designerLastMonth;
    }

    public void setDesignerLastMonth(String designerLastMonth) {
        this.designerLastMonth = designerLastMonth;
    }

    public String getDesignerOne() {
        return designerOne;
    }

    public void setDesignerOne(String designerOne) {
        this.designerOne = designerOne;
    }

    public String getDesignerOneRemark() {
        return designerOneRemark;
    }

    public void setDesignerOneRemark(String designerOneRemark) {
        this.designerOneRemark = designerOneRemark;
    }

    public String getDesignerTwo() {
        return designerTwo;
    }

    public void setDesignerTwo(String designerTwo) {
        this.designerTwo = designerTwo;
    }

    public String getDesignerTwoRemark() {
        return DesignerTwoRemark;
    }

    public void setDesignerTwoRemark(String designerTwoRemark) {
        DesignerTwoRemark = designerTwoRemark;
    }

    public String getDesignerThree() {
        return designerThree;
    }

    public void setDesignerThree(String designerThree) {
        this.designerThree = designerThree;
    }

    public String getDesignerThreeRemark() {
        return designerThreeRemark;
    }

    public void setDesignerThreeRemark(String designerThreeRemark) {
        this.designerThreeRemark = designerThreeRemark;
    }

    public String getDesignerFour() {
        return designerFour;
    }

    public void setDesignerFour(String designerFour) {
        this.designerFour = designerFour;
    }

    public String getDesignerFourRemark() {
        return designerFourRemark;
    }

    public void setDesignerFourRemark(String designerFourRemark) {
        this.designerFourRemark = designerFourRemark;
    }

    public String getDesignerFive() {
        return designerFive;
    }

    public void setDesignerFive(String designerFive) {
        this.designerFive = designerFive;
    }

    public String getDesignerFiveRemark() {
        return designerFiveRemark;
    }

    public void setDesignerFiveRemark(String designerFiveRemark) {
        this.designerFiveRemark = designerFiveRemark;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckerCompletionDate() {
        return checkerCompletionDate;
    }

    public void setCheckerCompletionDate(String checkerCompletionDate) {
        this.checkerCompletionDate = checkerCompletionDate;
    }

    public String getCheckerLastMonth() {
        return checkerLastMonth;
    }

    public void setCheckerLastMonth(String checkerLastMonth) {
        this.checkerLastMonth = checkerLastMonth;
    }

    public String getCheckerLastWeek() {
        return checkerLastWeek;
    }

    public void setCheckerLastWeek(String checkerLastWeek) {
        this.checkerLastWeek = checkerLastWeek;
    }

    public String getCheckerNowWeek() {
        return checkerNowWeek;
    }

    public void setCheckerNowWeek(String checkerNowWeek) {
        this.checkerNowWeek = checkerNowWeek;
    }

    public String getCheckerRemark() {
        return checkerRemark;
    }

    public void setCheckerRemark(String checkerRemark) {
        this.checkerRemark = checkerRemark;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

  public Integer getPrincipalId() {
    return principalId;
  }

  public void setPrincipalId(Integer principalId) {
    this.principalId = principalId;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public String getPrincipalLastWeek() {
    return principalLastWeek;
  }

  public void setPrincipalLastWeek(String principalLastWeek) {
    this.principalLastWeek = principalLastWeek;
  }

  public String getPrincipalNowWeek() {
    return principalNowWeek;
  }

  public void setPrincipalNowWeek(String principalNowWeek) {
    this.principalNowWeek = principalNowWeek;
  }

  public String getPrincipalRemark() {
    return principalRemark;
  }

  public void setPrincipalRemark(String principalRemark) {
    this.principalRemark = principalRemark;
  }
}
