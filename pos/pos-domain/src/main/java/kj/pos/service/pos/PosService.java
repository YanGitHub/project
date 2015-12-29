package kj.pos.service.pos;

import kj.pos.dao.mysql.pos.ShopSalesDetailDao;
import kj.pos.dao.mysql.pos.ShopSalesLineDao;
import kj.pos.dao.mysql.pos.ShopSalesPayDao;
import kj.pos.dao.mysql.product.ProductBarcodeDao;
import kj.pos.dao.mysql.product.ProductSkuDao;
import kj.pos.entity.pos.*;
import kj.pos.entity.product.ProductBarcode;
import kj.pos.entity.product.ProductSku;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kj.pos.util.web.WebContextUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Yan on 15-12-10.
 */
@Service("posService")
public class PosService {

    Log logger = LogFactory.getLog(PosService.class);

    @Autowired
    private ProductBarcodeDao productBarcodeDao;
    @Autowired
    private ProductSkuDao productSkuDao;
    @Autowired
    private ShopSalesDetailDao shopSalesDetailDao;
    @Autowired
    private ShopSalesLineDao shopSalesLineDao;
    @Autowired
    private ShopSalesPayDao shopSalesPayDao;

    public Map<String,Object> scanBarcode(String barcode)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        ProductBarcode productBarcode = new ProductBarcode();
        productBarcode.setBarcode(barcode);
        List<ProductBarcode> productBarcodeList = productBarcodeDao.getList(productBarcode);
        if(productBarcodeList.size() == 0){
            map.put("status",Boolean.FALSE);
            map.put("productSku",null);
            map.put("msg","系统中没有此条码，请检查商品条码中是否生成条码");
        }else{
            ProductSku productSku = new ProductSku();
            productSku.setProductCode(productBarcodeList.get(0).getProductCode());
            productSku.setCode(productBarcodeList.get(0).getSkuCode());
            List<ProductSku> productSkuList = productSkuDao.getList(productSku);
            if(productSkuList.size() != 0){
                map.put("status",Boolean.TRUE);
                map.put("productSku",productSkuList.get(0));
                map.put("msg","");
            }else{
                map.put("status",Boolean.FALSE);
                map.put("productSku", null);
                map.put("msg","此条码对应的商品已删除，请重新生成条码");
            }
        }
        return map;
    }

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public Map<String,Object> save(List<PosInfo> posInfoList)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        //收银明细表
        ShopSalesDetail shopSalesDetail = getShopSalesDetail(posInfoList);
        shopSalesDetailDao.create(shopSalesDetail);
        Long id = shopSalesDetail.getId();
        //商品明细
        List<ShopSalesLine> shopSalesLineList = getShopSalesLineList(posInfoList,id);
        shopSalesLineDao.create(shopSalesLineList);
        //支付明细
        List<ShopSalesPay> shopSalesPayList = getShopSalesPayList(posInfoList,id,shopSalesDetail.getFlowNo());
        shopSalesPayDao.create(shopSalesPayList);

        map.put("status",Boolean.TRUE);
        map.put("msg","收银成功");
        return map;
    }

    /**
     * 织组收银商品明细 实体
     * @param posInfoList
     * @return
     */
    public ShopSalesDetail getShopSalesDetail(List<PosInfo> posInfoList){
        ShopSalesDetail shopSalesDetail = new ShopSalesDetail();
        shopSalesDetail.setFlowNo(posInfoList.get(0).getFlowNo());
        shopSalesDetail.setSaleDate(posInfoList.get(0).getSaleDate());
        shopSalesDetail.setVipNo("");
        shopSalesDetail.setIsOnline(false);
        shopSalesDetail.setSaleType(1);
        shopSalesDetail.setFundAmount(posInfoList.get(0).getChange());
        shopSalesDetail.setCreateUser(WebContextUtil.getCurrentUser().getName());
        shopSalesDetail.setModifyUser(WebContextUtil.getCurrentUser().getName());
        return shopSalesDetail;
    }

    /**
     * 收银商品明细 list
     * @param posInfoList
     * @param pid
     * @return
     */
    public List<ShopSalesLine> getShopSalesLineList(List<PosInfo> posInfoList,Long pid){
        List<ShopSalesLine> lines = new ArrayList<ShopSalesLine>();
        for(PosInfo p : posInfoList){
            ShopSalesLine s = new ShopSalesLine();
            s.setPid(pid);
            s.setSkuCode(p.getSkuCode());
            s.setProductCode(p.getProductCode());
            s.setUntPrice(p.getPrice());
            s.setRealPrice(p.getRelPrice());
            s.setSaleDiscount(p.getDiscount());
            s.setDisAmount(p.getQty() * p.getPrice() - p.getAmount());
            s.setQty(p.getQty());
            s.setRealAmount(p.getAmount());
            s.setIsGift(false);
            lines.add(s);
        }
        return lines;
    }

    /**
     * 组织收银支付明细
     * @param list
     * @param pid
     * @param flowNo
     * @return
     */
    public List<ShopSalesPay> getShopSalesPayList(List<PosInfo> list,Long pid,String flowNo){
        List<ShopSalesPay> payList = new ArrayList<ShopSalesPay>();
        List<ShopPayment> payments = list.get(0).getPaymentList();
        for(ShopPayment p: payments){
            ShopSalesPay s = new ShopSalesPay();
            s.setPid(pid);
            s.setFlowNo(flowNo);
            s.setPayNo(p.getCode());
            s.setAmount(p.getPayAmount());
            payList.add(s);
        }
        return payList;
    }
}
