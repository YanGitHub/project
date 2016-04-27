package kj.pos.service.info;

import kj.pos.dao.mysql.info.EmployeeInfoDao;
import kj.pos.entity.info.EmployeeInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-4-27.
 */
@Service("employeeInfoService")
public class EmployeeInfoService {

    Log logger = LogFactory.getLog(EmployeeInfoService.class);

    @Autowired
    private EmployeeInfoDao employeeInfoDao;

    public List<EmployeeInfo> getList(EmployeeInfo employeeInfo)throws SQLException{
        return employeeInfoDao.getList(employeeInfo);
    }

    public Integer getTotal(EmployeeInfo employeeInfo)throws SQLException{
        return employeeInfoDao.getTotal(employeeInfo);
    }


}
