package kj.pos.controller.common;

import kj.pos.entity.admin.OrganizationInfo;
import kj.pos.entity.info.Region;
import kj.pos.entity.info.VipType;
import kj.pos.service.admin.OrganizationService;
import kj.pos.service.info.RegionService;
import kj.pos.service.info.VipTypeService;
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
}
