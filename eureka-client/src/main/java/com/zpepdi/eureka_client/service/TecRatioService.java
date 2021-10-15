package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.TecRatio;
import com.zpepdi.eureka_client.result.Result;

public interface TecRatioService {

  Result queryByTec(Integer id);

  Result setRatioByTec(TecRatio tecRatio);
}
