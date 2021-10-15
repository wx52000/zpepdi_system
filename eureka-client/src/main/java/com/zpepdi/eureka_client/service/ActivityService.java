package com.zpepdi.eureka_client.service;

import com.zpepdi.eureka_client.entity.Activity;
import com.zpepdi.eureka_client.entity.PrincipalWorkday;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.VirtualDesigner;
import com.zpepdi.eureka_client.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

public interface ActivityService {

  Result  setProject(Activity activity);

  Result query();

  Result queryByUser(Integer id);

  Result queryById(Integer id);

  Result queryWorkday(Map<String,Object> map,Integer userId);

  Result setWorkday(Map<String,Object> map,Integer userId);
}
