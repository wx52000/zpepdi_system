package com.zpepdi.eureka_client.service;

import java.util.List;
import java.util.Map;

public interface ReasonService {

  List<Map> queryByType(Integer type);
}
