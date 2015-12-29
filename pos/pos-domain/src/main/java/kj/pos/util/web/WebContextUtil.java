package kj.pos.util.web;

import kj.pos.entity.admin.SysParameters;
import kj.pos.entity.admin.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Yan on 15-9-29.
 */
@Controller
public class WebContextUtil {

    private static HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        WebContextUtil.session = session;
    }

    public static UserInfo getCurrentUser() {
        UserInfo userInfo = null;
        Object object = session.getAttribute("currentUser");
        if(object != null){
            userInfo = (UserInfo) object;
        }
        return userInfo;
    }

    public static String getSysParameter(String key) {
        List<SysParameters> sysParameters = null;
        Object object = session.getAttribute("sysParametersList");
        if(object != null){
            sysParameters = (List<SysParameters>) object;
            for(SysParameters s : sysParameters){
                if(s.getSysKey().equals(key)){
                    return s.getSysValue();
                }
            }
        }
        return null;
    }


}
