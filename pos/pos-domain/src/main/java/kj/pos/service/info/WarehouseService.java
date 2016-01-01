package kj.pos.service.info;

import kj.pos.dao.mysql.info.WarehouseDao;
import kj.pos.entity.info.Warehouse;
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
 * Created by Yan on 15-12-24.
 */
@Service("warehouseService")
public class WarehouseService {
    
    Log logger = LogFactory.getLog(WarehouseService.class);

    @Autowired
    private WarehouseDao warehouseDao;

    public Integer getTotal(Warehouse warehouse)throws SQLException {
        return warehouseDao.getTotal(warehouse);
    }

    public List<Warehouse> getList(Warehouse warehouse)throws SQLException{
        return warehouseDao.getList(warehouse);
    }
    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> add(Warehouse warehouse)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        Integer n = warehouseDao.getCode(warehouse.getCode(),null);
        if(n > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此品牌代码已经存在");
        }else{
            warehouseDao.create(warehouse);
            map.put("status",Boolean.TRUE);
            map.put("msg","添加成功");
        }
        return map;
    }
}
