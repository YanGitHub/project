package kj.pos.service.admin;

import kj.pos.dao.mysql.admin.OrganizationInfoDao;
import kj.pos.entity.admin.OrganizationInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-28.
 */
@Service("organizationService")
public class OrganizationService {

    Log logger = LogFactory.getLog(OrganizationService.class);

    @Autowired
    private OrganizationInfoDao organizationInfoDao;

    public Integer getTotal(OrganizationInfo organizationInfo)throws SQLException{
        return organizationInfoDao.getTotal(organizationInfo);
    }

    public List<OrganizationInfo> getList(OrganizationInfo organizationInfo)throws SQLException{
        return organizationInfoDao.getList(organizationInfo);
    }
}
