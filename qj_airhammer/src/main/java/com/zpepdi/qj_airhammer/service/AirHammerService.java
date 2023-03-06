package com.zpepdi.qj_airhammer.service;

import com.zpepdi.qj_airhammer.entity.AirHammer;
import jxl.write.WriteException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AirHammerService {
   HttpServletResponse compute(HttpServletResponse response, AirHammer airHammer, MultipartFile file) throws IOException, WriteException;

}
