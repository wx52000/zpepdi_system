package com.zpepdi.feign_service.service;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "EUREKA-CLIENT")
public interface ProjectExcelService {

    @RequestMapping("projectExcel/statisticAll")
    Response statisticAll(@RequestParam("date") String min);

    @RequestMapping("projectExcel/statistic")
    Response statistic(@RequestParam("id")Integer id);

    @RequestMapping("projectExcel/everyoneAll")
    Response everyoneAll(@RequestParam("date")String date);

    @RequestMapping("projectExcel/everyone")
    Response everyone(@RequestParam("id")Integer id);

    @RequestMapping("projectExcel/volumeAll")
    Response volumeAll(@RequestParam("date")String date);

    @RequestMapping("projectExcel/volume")
    Response volume(@RequestParam("id")Integer id);

    @RequestMapping("projectExcel/personal")
    Response personal(@RequestHeader("userId") Integer id);

    @RequestMapping("projectExcel/personalVolume")
    Response personalVolume(@RequestHeader("userId") Integer id,
                            @RequestParam("minDay")String min,@RequestParam("maxDay")String max);
}
