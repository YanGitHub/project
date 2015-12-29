package kj.pos.controller.common;

import kj.pos.entity.info.Region;
import kj.pos.service.info.RegionService;
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
import java.util.List;

/**
 * Created by Yan on 15-12-25.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    Log logger = LogFactory.getLog(CommonController.class);

    @Autowired
    private RegionService regionService;

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
}
