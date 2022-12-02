package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.eureka_client.dao.appraise.PositionDao;
import com.zpepdi.eureka_client.dao.appraise.SummaryProjectDao;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.SummaryProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class SummaryProjectServiceImpl implements SummaryProjectService {
    @Autowired
    private SummaryProjectDao summaryProjectDao;
    @Autowired
    private PositionDao positionDao;

    @Override
    @Transactional
    public Result setSummaryProject(Integer userId, Map<String, Object> map) {
        // 0 为新增 1为修改 2为数据新增
        int mode = Integer.parseInt(map.get("mode").toString());
        if (mode < 2) {
            summaryProjectDao.addBasic(userId, map);
            Integer projectId = Integer.valueOf(map.get("id").toString());
            if (map.get("contact") != null && !map.get("contact").toString().equals("")) {
                if (mode == 1) {
                    summaryProjectDao.delStaff(projectId, 0);
                }
                JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(map.get("contact")));
                summaryProjectDao.addStaff(projectId, jsonArray, 0);
            }
            if (map.get("tracker") != null && !map.get("tracker").toString().equals("")) {
                if (mode == 1) {
                    summaryProjectDao.delStaff(projectId, 1);
                }
                JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(map.get("tracker")));
                summaryProjectDao.addStaff(projectId, jsonArray, 1);
            }
        }
        if (mode != 1) {
            summaryProjectDao.addInformation(userId, map);
        }
        return Result.ok();
    }

    @Override
    public Result query(Integer id) {
        return Result.ok(summaryProjectDao.query(id));
    }

    @Override
    public Result queryByHandler(Integer id) {
        if (positionDao.permissionByMenus(id, 27)) {
            return Result.ok(summaryProjectDao.query(id));
        } else {
            return Result.ok(summaryProjectDao.queryByHandler(id));
        }
    }

    @Override
    public Result queryById(Integer id) {
            return Result.ok(summaryProjectDao.queryById(id));
    }

    @Override
    public Result queryRoleById(Integer id) {
        return Result.ok(summaryProjectDao.queryRoleById(id));
    }
}
