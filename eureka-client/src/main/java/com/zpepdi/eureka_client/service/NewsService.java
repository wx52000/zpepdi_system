package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.result.Result;

public interface NewsService {

  Result newsCount(Integer id);

  Result query(Integer id);
}
