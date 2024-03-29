package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface StatisticsService {

    Result queryPlan(String year);

    Result queryZCBMonth(String date);

    Result queryZCBYear(Integer year);

}
