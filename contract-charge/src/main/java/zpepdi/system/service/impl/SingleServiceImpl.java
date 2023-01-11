package zpepdi.system.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.SingleDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.SingleService;

@Service
public class SingleServiceImpl implements SingleService {
    @Autowired
    private SingleDao singleDao;

    @Override
    public Result query() {
        return Result.ok(singleDao.query());
    }
}
