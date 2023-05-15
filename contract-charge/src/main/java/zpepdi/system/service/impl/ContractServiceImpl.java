package zpepdi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import zpepdi.system.dao.fd.ContractDao;
import zpepdi.system.dao.fd.ContractSplitDao;
import zpepdi.system.dao.fd.ContractUserDao;
import zpepdi.system.dao.fd.ProjectDao;
import zpepdi.system.dao.zjepdi.ContractDataDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ContractSplitDao contractSplitDao;
    @Autowired
    private ContractUserDao contractUserDao;
    @Autowired
    private ContractDataDao contractDataDao;
    @Override
    @Transactional
    public Result insertSingle(Integer userId, Map<String, Object> map) {
        if (map.get("ContractID") == null || map.get("ContractID").toString().equals("")){
            map.put("ContractID", UUID.randomUUID());
        }
        contractDao.insertSingle(userId,map);
        if (map.get("operator") != null && !map.get("operator").equals("")){
            JSONObject operator = JSONObject.parseObject(JSON.toJSONString(map.get("operator")));
            operator.put("userId",operator.get("id"));
            operator.put("id",map.get("ContractID").toString());
            operator.put("role",0);
            contractUserDao.addContractUser(userId,operator);
        }
        if (map.get("checker") != null && !map.get("checker").equals("")){
            JSONObject checker = JSONObject.parseObject(JSON.toJSONString(map.get("checker")));
            checker.put("userId",checker.get("id"));
            checker.put("id",map.get("ContractID").toString());
            checker.put("role",1);
            contractUserDao.addContractUser(userId,checker);
        }
        if (map.get("ContractCode") != null && !map.get("ContractCode").toString().equals("")){
            String code = map.get("ContractCode").toString();
            String search = code.substring(0,code.indexOf("-001"));
            List<Map<String,Object>> childrenList = contractDataDao.queryChildrenBySearch(search);
            for (Map<String,Object>  children : childrenList){
                children.put("parent", map.get("ContractID"));
                contractDao.insertSingle(userId,children);
            }
        }
        return Result.ok();
    }

    @Override
    public Result addRelativeProject(Integer userId, Map<String, Object> map) {
        projectDao.addRelateContract(userId,map);
        projectDao.updateData(map);
        return Result.ok();
    }

    @Override
    public Result setType(Map<String, Object> map) {
        contractDao.setType(map);
        return Result.ok();
    }

    @Override
    public Result setContractDate(Map<String, Object> map) {
        contractDao.setContractDate(map);
        contractSplitDao.setContractZCBSplit(map);
        return Result.ok();
    }

    @Override
    public Result setEndTime(Map<String, Object> map) {
        contractDao.setEndTime(map);
        return Result.ok();
    }

    @Override
    public Result query(Integer income) {
        return Result.ok(contractDao.query(income));
    }

    @Override
    public Result queryParent(Map<String, Object> map) {
        return Result.ok(contractDao.queryParent(map));
    }

    @Override
    public Result queryChildren(String id) {
        return Result.ok(contractDao.queryChildren(id));
    }

    @Override
    public Result queryZCBChildren(String id) {
        return Result.ok(contractDao.queryZCBChildren(id));
    }


    @Override
    public Result queryRelativeProject(String id) {
        return Result.ok(contractDao.queryRelativeProject(id));
    }

    @Override
    public Result addChildren(Map<String, Object> map) {
        contractDao.addChildren(map);
        return Result.ok();
    }

    @Override
    public Result delChildren(String id) {
        contractDao.delChildren(id);
        return Result.ok();
    }

    @Override
    public Result queryBlur(String search) {
        return Result.ok(contractDao.queryBlur(search));
    }

    @Override
    public Result queryById( String id) {
        return Result.ok(contractDao.queryById(id));
    }

    @Override
    public Result contractReceive(String id) {
        return Result.ok(contractDao.contractReceive(id));
    }

    @Override
    public Result queryConfirm(Integer userId) {
        return Result.ok(contractDao.queryConfirm(userId));
    }

    @Override
    public Result setConfirm(Integer userId, List<Map<String, Object>> list) {
        contractDao.setConfirm(userId,list);
        return Result.ok();
    }

    @Override
    public Result queryParentBySearchFromZpepdi(Integer userId, Map<String,Object> map) {
        return Result.ok(contractDataDao.queryParentBySearch(map));
    }
}
