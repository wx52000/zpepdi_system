package com.zpepdi.qj_heating.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zpepdi.qj_heating.result.Result;
import org.springframework.stereotype.Service;


public interface jsdataService {

    Result queryTableNames();
    Result queryzhcn();

    Result queryTabledata(String tablename);

    Result queryTablebz(String tablename);
}
