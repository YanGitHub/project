package kj.pos.service.info;

import kj.pos.dao.mysql.info.SupplierTypeDao;
import kj.pos.entity.info.SupplierType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-23.
 */
@Service("supplierTypeService")
public class SupplierTypeService {

    Log logger = LogFactory.getLog(SupplierTypeService.class);

    @Autowired
    private SupplierTypeDao supplierTypeDao;

    public Integer getTotal(SupplierType supplierType)throws SQLException{
        return supplierTypeDao.getTotal(supplierType);
    }

    public List<SupplierType> getList(SupplierType supplierType)throws SQLException{
        return supplierTypeDao.getList(supplierType);
    }

    public void create(SupplierType supplierType)throws SQLException{
        supplierTypeDao.create(supplierType);
    }
}
