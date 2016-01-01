package kj.pos.controller.admin;

import kj.pos.entity.PageUtil;
import kj.pos.entity.admin.UserInfo;
import kj.pos.service.admin.UserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-31.
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    Log logger = LogFactory.getLog(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String userInfo(){
        return "admin/userInfo";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> shopSalesLine(UserInfo userInfo,
                                            @RequestParam(value = "page",defaultValue = "1")int page,
                                            @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = userInfoService.getTotal(userInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            userInfo.setPageUtil(pageUtil);
            List<UserInfo> list = userInfoService.getList(userInfo);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(@RequestBody UserInfo userInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = userInfoService.save(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","操作失败");
        }
        return map;
    }

    @RequestMapping(value = "/status",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> status(UserInfo userInfo)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            userInfoService.status(userInfo);
            map.put("status",Boolean.TRUE);
            map.put("msg","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("status",Boolean.FALSE);
            map.put("msg","操作失败");
        }
        return map;
    }
}
