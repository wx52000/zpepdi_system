package com.zpepdi.eureka_client.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.zpepdi.eureka_client.annotation.UserId;
import com.zpepdi.eureka_client.excel.WorkdayByProjectExcelListener;
import com.zpepdi.eureka_client.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.ProjectExcelTec;
import com.zpepdi.eureka_client.excel.ExcelOutData;
import com.zpepdi.eureka_client.excel.ExcelOutputHead;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.tools.Download;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("projectExcel")
public class ProjectExcelController {
  private ProjectService projectService;
  private ProjectTecService projectTecService;
  private ProjectWorkDayService projectWorkDayService;
  private VolumeService volumeService;

  @Autowired
  public void setProjectService(ProjectService projectService){
    this.projectService = projectService;
  }
  @Autowired
  public void setProjectTecService(ProjectTecService projectTecService){
    this.projectTecService =projectTecService;
  }
  @Autowired
  public void setProjectWorkDayService(ProjectWorkDayService projectWorkDayService){
    this.projectWorkDayService = projectWorkDayService;
  }
  @Autowired
  public void setVolumeService(VolumeService volumeService){
    this.volumeService = volumeService;
  }
  /*
  all为项目管理页面下载；其余为项目页面下载
  */
  @RequestMapping("statisticAll")
  public Result statisticAll(@RequestParam("date") String date, HttpServletResponse response){
//    String min = request.getParameter("minDay");
//    String max = request.getParameter("maxDay");
    System.out.println("+++++++++++++++++++ssss");
    List<String> listHeader = Arrays.asList("专业","卷册总数","专业工时","备用工时","已用工时");
    List<ProjectExcelTec> list = projectWorkDayService.statisticAll(date);
    String fileName = "D:/excel/statisticAll.xlsx";
    ExcelWriter excelWriter = null;
    try {
      excelWriter = EasyExcel.write(fileName).build();
      WriteSheet writeSheet = EasyExcel.writerSheet().sheetName("sheet").needHead(Boolean.FALSE).build();
      for(int i = 0; i < list.size(); i ++ ){
        System.out.println(list.get(i).getList());
        if (list.get(i).getList().size() > 0) {
          WriteTable writeTable = EasyExcel.writerTable(i).needHead(Boolean.TRUE)
            .head(ExcelOutputHead.ComplexList(list.get(i).getName(),listHeader))
            .build();
          excelWriter.write(ExcelOutData.dataTec(list.get(i).getList()),writeSheet,writeTable);
        }
      }
    }finally {
      if (excelWriter != null)
        excelWriter.finish();
    }
    Download.downloadFile(response, "statisticAll.xlsx", date + "专业完成卷册工时汇总.xlsx");
    return Result.ok();
  }


  @RequestMapping("statistic")
  public Result statistic(HttpServletResponse response, @RequestParam("id")Integer id){
    List<String> listHeader = Arrays.asList("专业","卷册总数","专业工时","备用工时","已用工时");
    Map map = projectService.queryById(id);

    List<Map<String,String>> list = projectWorkDayService.statistic(id);
      String fileName = "D:/excel/statistic.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("工时使用情况汇总").doWrite(ExcelOutData.dataTec(list));
      Download.downloadFile( response , "statistic.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }

  @RequestMapping("everyoneAll")
  public Result everyoneAll(HttpServletResponse response, @RequestParam("date")String date){
    List<String> listHeader = Arrays.asList("姓名","工号",
      "共管理卷册数量","共设计完成卷册数量","共校核完成卷册数量");
    List<Map<String,String>> list = projectWorkDayService.everyoneAll(date);
    String fileName = "D:/excel/everyone.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人卷册参与情况汇总").doWrite(ExcelOutData.everyone(list,1));
    Download.downloadFile( response , "everyone.xlsx", date +"个人卷册完成状况统计.xlsx");
    return Result.ok();
  }

  @RequestMapping("everyone")
  public Result everyone(HttpServletResponse response, @RequestParam("id")Integer id){
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month = calendar.get(Calendar.MONTH)+1;
    List<String> listHeader = Arrays.asList("姓名","工号",
            "共管理卷册数量","共设计完成卷册数量","共校核完成卷册数量");
    Map map = projectService.queryById(id);
    map.put("thisMonth",year + "-" + month + "-1");
    map.put("nextMonth",year + "-" + month+1 + "-1");
    if (month != 1)
      map.put("lastMonth",year + "-" + --month + "-1");
    else
      map.put("lastMonth",year-1 + "-12-1");
    List<Map<String,String>> list = projectWorkDayService.everyone(map);
    String fileName = "D:/excel/everyone.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人本项目卷册参与情况汇总").doWrite(ExcelOutData.everyone(list,0));
    Download.downloadFile( response , "everyone.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }

  @RequestMapping("workdayByProject")
  public Result workdayByProject(HttpServletResponse response, @RequestParam("file")MultipartFile file, @RequestParam("id") Integer id){
    ExcelReader excelReader = EasyExcel.read((File) file).build();
    WorkdayByProjectExcelListener workdayByProjectExcelListener = new WorkdayByProjectExcelListener();
    WorkdayByProjectExcelListener workdayByProjectExcelListener1 = new WorkdayByProjectExcelListener();
    ReadSheet readSheet =
            EasyExcel.readSheet(1).head(Map.class).registerReadListener(workdayByProjectExcelListener).build();
    ReadSheet readSheet1 =
            EasyExcel.readSheet(2).head(Map.class).registerReadListener(workdayByProjectExcelListener1).build();
    excelReader.read(readSheet,readSheet1);
    excelReader.finish();
    List<String> list = workdayByProjectExcelListener.getList();
    List<String> list1 = workdayByProjectExcelListener1.getList();
    return Result.ok();
  }



  @RequestMapping("volumeAll")
  public Result volumeAll(HttpServletResponse response, @RequestParam("date")String date){
    List<String> listHeader = Arrays.asList("项目号","项目名","卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","实际主设人","工时","工时状态","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    List<Map<String,String>> list = volumeService.queryByDate(date);
    String fileName = "D:/excel/volumeAll.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.dataVolume(list,1));
    Download.downloadFile( response , "volumeAll.xlsx", date+"完成卷册汇总.xlsx");
    return Result.ok();
  }

  @RequestMapping("volume")
  public Result volume(HttpServletResponse response, @RequestParam("id")Integer id){
    List<String> listHeader = Arrays.asList("卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","实际主设人","工时","工时状态","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    Map map = projectService.queryById(id);
    Project project = new Project();
    project.setId(id);
    List<Map<String,String>> list = volumeService.queryByProjectId(project);
    String fileName = "D:/excel/volume.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("卷册列表详情汇总").doWrite(ExcelOutData.dataVolume(list,0));
    Download.downloadFile( response , "volume.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }
  /*
  <=============================================>
   */

  @RequestMapping("personal")
  public Result personal(HttpServletResponse response, @UserId Integer id){
    List<String> listHeader = Arrays.asList("发放人","工时数量","工时类型","卷册号/项目号");
    List<Map<String, Object>> list = projectWorkDayService.personal(id);
    String fileName = "D:/excel/personal.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.personal(list));
    Download.downloadFile( response , "personal.xlsx", id +"施工图完成数量.xlsx");
    return Result.ok();
  }
  @RequestMapping("personalVolume")
  public Result personalVolume(HttpServletResponse response,@UserId Integer id,
                               @RequestParam("minDay")String min,@RequestParam("maxDay")String max) {
    Map<String,String> map = new HashMap();
    map.put("min",min);
    map.put("max",max);
    map.put("id",id.toString());
    List<String> listHeader = Arrays.asList("项目号","项目名","卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    List<Map<String,String>> list = volumeService.personalVolume(map);
    String fileName = "D:/excel/personalVolume.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.dataVolume(list,1));
    Download.downloadFile( response , "personalVolume.xlsx", min + "-" + max+"完成施工图汇总.xlsx");
    return Result.ok();
  }
}
