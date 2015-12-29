package kj.pos.controller.admin;

import kj.pos.entity.admin.SysParameters;
import kj.pos.entity.admin.UserInfo;
import kj.pos.service.admin.SysParametersService;
import kj.pos.service.admin.UserInfoService;
import kj.pos.util.web.WebContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-11-27.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    Log logger = LogFactory.getLog(LoginController.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private SysParametersService sysParametersService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public ModelAndView main(){
        UserInfo userInfo = WebContextUtil.getCurrentUser();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userInfo",userInfo);
        if(userInfo != null){
            return new ModelAndView("main",map);
        }else{
            return new ModelAndView("index");
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(UserInfo userInfo,HttpServletRequest request)throws SQLException{
        HttpSession session = request.getSession();
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            //获取登录用户信息
            List<UserInfo> userInfoList = userInfoService.getList(userInfo);
            //获取系统参数列表
            SysParameters sysParameters = new SysParameters();
            sysParameters.setStatus(true);
            List<SysParameters> sysParametersList = sysParametersService.getList(sysParameters);
            if(userInfoList.size() == 1){
                session.setAttribute("currentUser",userInfoList.get(0));
                session.setAttribute("sysParametersList",sysParametersList);
                map.put("status",Boolean.TRUE);
                map.put("msg","登录成功");
            }else{
                map.put("status",Boolean.FALSE);
                map.put("msg","用户名或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","请联系管理员");
        }
        return map;
    }
}
