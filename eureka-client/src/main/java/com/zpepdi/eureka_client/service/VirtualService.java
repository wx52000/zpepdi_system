package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.PrincipalWorkday;
import com.zpepdi.eureka_client.entity.Virtual;
import com.zpepdi.eureka_client.entity.VirtualDesigner;
import com.zpepdi.eureka_client.result.Result;

import java.util.List;
import java.util.Map;

public interface VirtualService {

  Result setProject(Virtual virtual);

  Result query();

  Result queryByUser(Integer id);

  Result queryById(Map<String,Object> map);

  Result queryUsedWorkday(Map<String,Object> map,Integer userId);

  Result queryWorkday(Map<String,Object> map,Integer id);

  Result setWorkday(Map<String,Object> map,Integer id);
}
