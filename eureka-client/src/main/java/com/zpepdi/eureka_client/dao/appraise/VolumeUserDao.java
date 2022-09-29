package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Volume;

@Repository
public interface VolumeUserDao {

    void addDesigner(Volume volume);

    void addChecker(Volume volume);

    void addExcelDesigner(ExcelProject excelProject);

    void addExcelChecker(ExcelProject excelProject);
    void del(Volume volume);
}
