package kj.pos.service.product;

import kj.pos.dao.mysql.product.ProductBarcodeDao;
import kj.pos.entity.product.ProductBarcode;
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
 * Created by Yan on 15-12-9.
 */
@Service("productBarCodeService")
public class ProductBarcodeService {

    Log logger = LogFactory.getLog(ProductBarcodeService.class);

    @Autowired
    private ProductBarcodeDao productBarcodeDao;

    public Integer getTotal(ProductBarcode productBarcode)throws SQLException {
        return productBarcodeDao.getTotal(productBarcode);
    }

    public List<ProductBarcode> getList(ProductBarcode productBarcode)throws SQLException{
        return productBarcodeDao.getList(productBarcode);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void create(ProductBarcode productBarcode)throws SQLException{
        delete(productBarcode);
        productBarcodeDao.create();
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void delete(ProductBarcode productBarcode)throws SQLException{
        String array[] = null;
        if(productBarcode.getIdArray() != null && !productBarcode.getIdArray().equals("")){
            array = productBarcode.getIdArray().split(",");
        }
        productBarcodeDao.delete(array);
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> modify(ProductBarcode productBarcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        int total = productBarcodeDao.getBarcode(productBarcode);
        if(total > 0){
            map.put("status",Boolean.FALSE);
            map.put("msg","此条码已存在");
            return map;
        }
        productBarcodeDao.update(productBarcode);
        map.put("status",Boolean.TRUE);
        map.put("msg","修改成功");
        return map;
    }
}
