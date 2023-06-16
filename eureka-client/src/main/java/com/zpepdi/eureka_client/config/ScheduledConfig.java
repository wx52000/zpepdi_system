package com.zpepdi.eureka_client.config;

import com.zpepdi.eureka_client.service.*;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

//定时任务，用于清空评价表
@Configuration
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledConfig.class);
    
    private VolumeService volumeService;
    @Autowired
    private DataTransmissionService service;
    @Autowired
    private ProjectService projectService;


    //此处为每月自动更新时间
    public static final Integer setDay = 25;
    private static final String setTime =  "0 0 0 25 */3 ?";

    @Autowired
    public  void setVolumeService(VolumeService volumeService){
        this.volumeService = volumeService;
    }

    @Autowired
    public ScientificSystemService scientificSystemService;


//    @Scheduled(cron = "0 0 3 23 * ?")
//    public void createScientificProduce(){
//        scientificSystemService.createScientificProduce(DateUtils.getDateMonth());
//    }
//
//    @Scheduled(cron = "0 0 5 1 * ?")
//    public void resetPlanDate(){
//        volumeService.resetPlanDate();
//    }
//////
//////
//    @Scheduled(cron = "0 0 0/2 * * ?")
////    @PostConstruct
//    public void dataTransmission(){
//        service.dataTransmissionService();
//    }
//    @Scheduled(cron = "0 59 23 * * ?")
//    @Async("taskExecutor")
////    @PostConstruct
//    public void queryIncomeInformation(){
//        service.queryIncomeInformation();
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        if (projectService.confirmDay().equals(day)) {
//            volumeService.updatePlanedPublicDate();
//            volumeService.timingConfirmWorkday(DateUtils.dateToString(calendar.getTime(),"yyyy-MM"));
//        }
//    }





  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
      Method[] methods = BatchProperties.Job.class.getMethods();
      int defaultPoolSize = 3;
      int corePoolSize = 0;
      if (methods != null && methods.length > 0) {
        for (Method method : methods) {
          Scheduled annotation = method.getAnnotation(Scheduled.class);
          if (annotation != null) {
            corePoolSize++;
          }
        }
        if (defaultPoolSize > corePoolSize)
          corePoolSize = defaultPoolSize;
      }
    scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
    }


    //  @Scheduled(cron = "0 0 0 * * ?")
//  public void  spider(){
////      String[] cmdArr = new String[]{"G:","/c","cd G:\\python_project\\zpepdi\\zpepdi\\zpepdi","/C",
////        "pyhton start_scrapy.py"};
//    try {
//      System.out.println("爬虫抓取定时任务" + LocalDateTime.now());
//      Process process = Runtime.getRuntime().exec("C:\\Users\\admin\\Desktop\\评价系统\\spider.bat");
////      Process process = Runtime.getRuntime().exec("C:\\Users\\Administrator\\Desktop\\评价系统\\spider.bat");
////      Process process = Runtime.getRuntime().exec(cmdArr);
//      InputStream fis=process.getInputStream();
//      InputStreamReader isr=new InputStreamReader(fis);
//      //用缓冲器读行
//      BufferedReader br=new BufferedReader(isr);
//      String line=null;
//      //直到读完为止
//      while((line=br.readLine())!=null)
//      {
//        System.out.println(line);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }

}
