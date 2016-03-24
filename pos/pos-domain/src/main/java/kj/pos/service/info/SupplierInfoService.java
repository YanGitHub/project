package kj.pos.service.info;

import kj.pos.dao.mysql.info.SupplierInfoDao;
import kj.pos.entity.info.SupplierInfo;
import kj.pos.util.web.WebContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-3-23.
 */
@Service("supplierInfoService")
public class SupplierInfoService {

    Log logger = LogFactory.getLog(SupplierInfoService.class);

    @Autowired
    private SupplierInfoDao supplierInfoDao;

    public Integer getTotal(SupplierInfo supplierInfo)throws SQLException{
        return supplierInfoDao.getTotal(supplierInfo);
    }

    public List<SupplierInfo> getList(SupplierInfo supplierInfo)throws SQLException{
        return supplierInfoDao.getList(supplierInfo);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> create(SupplierInfo supplierInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer n = supplierInfoDao.getCode(supplierInfo);
        if(n > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此代码已经存在");
        }else{
            supplierInfoDao.create(supplierInfo);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }
        return map;
    }

}
