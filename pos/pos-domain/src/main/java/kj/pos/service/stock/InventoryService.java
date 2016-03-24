package kj.pos.service.stock;

import kj.pos.dao.mysql.stock.InventoryDao;
import kj.pos.entity.stock.Inventory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 16-3-22.
 */
@Service("inventoryService")
public class InventoryService {

    Log logger = LogFactory.getLog(InventoryService.class);

    @Autowired
    private InventoryDao inventoryDao;

    public Integer getTotal(Inventory inventory)throws SQLException{
        return inventoryDao.getTotal(inventory);
    }

    public List<Inventory> getList(Inventory inventory)throws SQLException{
        return inventoryDao.getList(inventory);
    }
}
