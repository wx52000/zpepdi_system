package com.zpepdi.eureka_client.dao.appraise;

import org.springframework.stereotype.Repository;
import com.zpepdi.eureka_client.entity.ProjectLog;

@Repository
public interface ProjectLogDao {

    void add(ProjectLog projectLog);


}
