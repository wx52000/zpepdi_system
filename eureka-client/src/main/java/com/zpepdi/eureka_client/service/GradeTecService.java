package com.zpepdi.eureka_client.service;

import org.apache.ibatis.annotations.Param;
import com.zpepdi.eureka_client.entity.GradeTec;

import java.util.List;
import java.util.Set;

public interface GradeTecService {

    void add(GradeTec gradeTec);

    void del(Integer id);

    void addExcel(@Param("list") List<Integer> list , @Param("set") Set<Integer> set);

    void resetState();
}
