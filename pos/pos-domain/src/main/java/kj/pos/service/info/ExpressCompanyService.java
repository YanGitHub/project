package kj.pos.service.info;

import kj.pos.dao.mysql.info.ExpressCompanyDao;
import kj.pos.entity.info.ExpressCompany;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-1.
 */
@Service
public class ExpressCompanyService {

    Log logger = LogFactory.getLog(ExpressCompanyService.class);

    @Autowired
    private ExpressCompanyDao expressCompanyDao;

    public Integer getTotal(ExpressCompany expressCompany)throws SQLException{
        return expressCompanyDao.getTotal(expressCompany);
    }

    public List<ExpressCompany> getList(ExpressCompany expressCompany)throws SQLException{
        return expressCompanyDao.getList(expressCompany);
    }
}
