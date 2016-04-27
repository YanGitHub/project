package kj.pos.controller.common;

import kj.pos.entity.admin.OrganizationInfo;
import kj.pos.entity.info.*;
import kj.pos.entity.product.Brand;
import kj.pos.entity.product.Category;
import kj.pos.entity.stock.PurchaseEntryType;
import kj.pos.entity.stock.PurchaseType;
import kj.pos.service.admin.OrganizationService;
import kj.pos.service.info.*;
import kj.pos.service.product.BrandService;
import kj.pos.service.product.CategoryService;
import kj.pos.service.stock.PurchaseEntryTypeService;
import kj.pos.service.stock.PurchaseTypeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-25.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    Log logger = LogFactory.getLog(CommonController.class);

    @Autowired
    private RegionService regionService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private VipTypeService vipTypeService;
    @Autowired
    private SupplierTypeService supplierTypeService;
    @Autowired
    private SupplierInfoService supplierInfoService;
    @Autowired
    private PurchaseTypeService purchaseTypeService;
    @Autowired
    private PurchaseEntryTypeService purchaseEntryTypeService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EmployeeInfoService employeeInfoService;
    /**
     * 获取省市区
     * @param region
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getRegions",method = RequestMethod.POST)
    @ResponseBody
    public List<Region> getRegion(@RequestBody Region region)throws SQLException{
        List<Region> list = new ArrayList<Region>();
        try{
            if(region.getParentId() == null || region.getParentId().equals("")){
                region.setParentId("root_china");
            }
            list = regionService.getList(region);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            list = null;
        }
        return list;
    }

    /**
     * 获取所有可用的店铺
     * @param organizationInfo
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getShops",method = RequestMethod.POST)
    @ResponseBody
    public List<OrganizationInfo> getShops(OrganizationInfo organizationInfo)throws SQLException{
        List<OrganizationInfo> list = new ArrayList<OrganizationInfo>();
        try {
            //取所有可用的店铺
            organizationInfo.setStatus(1);
            organizationInfo.setProperty(1);
            list = organizationService.getList(organizationInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 获取所有可用的会员类型
     * @param vipType
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getVipTypes",method = RequestMethod.POST)
    @ResponseBody
    public List<VipType> getVipTypes(VipType vipType)throws SQLException{
        List<VipType> list = new ArrayList<VipType>();
        try {
            vipType.setDel(false);
            list = vipTypeService.getList(vipType);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 供应商类型
     * @param supplierType
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getSupplierType",method = RequestMethod.POST)
    @ResponseBody
    public List<SupplierType> getSupplierType(SupplierType supplierType)throws SQLException{
        List<SupplierType> list = new ArrayList<SupplierType>();
        try {
            list = supplierTypeService.getList(supplierType);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 采购类型
     * @param purchaseType
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getPurchaseTypes",method = RequestMethod.POST)
    @ResponseBody
    public List<PurchaseType> getPurchaseTypes(PurchaseType purchaseType)throws SQLException{
        List<PurchaseType> list = new ArrayList<PurchaseType>();
        try {
            purchaseType.setDel(false);
            list = purchaseTypeService.getList(purchaseType);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    @RequestMapping(value = "/getPurchaseEntryType",method = RequestMethod.POST)
    @ResponseBody
    public List<PurchaseEntryType> getPurchaseEntryType(PurchaseEntryType purchaseEntryType)throws SQLException{
        List<PurchaseEntryType> list = new ArrayList<PurchaseEntryType>();
        try {
            purchaseEntryType.setDel(false);
            list = purchaseEntryTypeService.getList(purchaseEntryType);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 仓库列表
     * @param warehouse
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getWarehouseByShopCode",method = RequestMethod.POST)
    @ResponseBody
    public List<Warehouse> getPurchaseTypes(Warehouse warehouse)throws SQLException{
        List<Warehouse> list = new ArrayList<Warehouse>();
        try {
            list = warehouseService.getList(warehouse);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    @RequestMapping(value = "/getSupplierInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<SupplierInfo> getSupplierInfo(SupplierInfo supplierInfo)throws SQLException{
        List<SupplierInfo> list = new ArrayList<SupplierInfo>();
        try {
            list = supplierInfoService.getList(supplierInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 商品品牌
     * @param brand
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getProductBrands",method = RequestMethod.POST)
    @ResponseBody
    public List<Brand> getProductBrands(Brand brand)throws SQLException{
        List<Brand> list = new ArrayList<Brand>();
        try {
            list = brandService.getList(new Brand());
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 商品品类
     * @param category
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getProductCategories",method = RequestMethod.POST)
    @ResponseBody
    public List<Category> getProductCategories(Category category)throws SQLException{
        List<Category> list = new ArrayList<Category>();
        try {
            list = categoryService.getList(category);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }

    /**
     * 员工 信息 （在职）
     * @param employeeInfo
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/getEmployees",method = RequestMethod.POST)
    @ResponseBody
    public List<EmployeeInfo> getEmployees(EmployeeInfo employeeInfo)throws SQLException{
        List<EmployeeInfo> list = new ArrayList<EmployeeInfo>();
        try {
            //在职 0 离职 1
            employeeInfo.setIsJob(false);
            list = employeeInfoService.getList(employeeInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return list;
    }
}
