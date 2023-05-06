package com.zpepdi.eureka_client.feign;

import com.zpepdi.eureka_client.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("AUDIT-SERVICE")
public interface AuditInformationFeign {

    @RequestMapping("auditInformation/addAuditInformation")
    Result addAuditInformation(@RequestBody Map<String,Object> map);

    @RequestMapping("auditInformation/renew")
    Result renew(@RequestBody Map<String,Object> map);
}
