package kj.pos.service.admin;


import kj.pos.dao.mysql.admin.SysParametersDao;
import kj.pos.entity.admin.SysParameters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 15-12-22.
 */
@Service("sysParametersService")
public class SysParametersService {

    Log logger = LogFactory.getLog(SysParametersService.class);

    @Autowired
    private SysParametersDao sysParametersDao;

    public Integer getTotal(SysParameters sysParameters)throws SQLException{
        return sysParametersDao.getTotal(sysParameters);
    }

    public List<SysParameters> getList(SysParameters sysParameters)throws SQLException{
        return sysParametersDao.getList(sysParameters);
    }

    public Map<String,Object> save(SysParameters sysParameters)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        if(sysParameters.getId() != null){
            sysParametersDao.update(sysParameters);
            map.put("status",Boolean.TRUE);
            map.put("msg","更新成功");
        }else{
            SysParameters parameters = new SysParameters();
            parameters.setSysKey(sysParameters.getSysKey());
            List<SysParameters> list = sysParametersDao.getList(parameters);
            if(list.size() > 0){
                map.put("status",Boolean.FALSE);
                map.put("msg","此系统参数已经存在");
            }else{
                sysParametersDao.create(sysParameters);
                map.put("status",Boolean.TRUE);
                map.put("msg","新增成功");
            }
        }
        return map;
    }

    public void status(SysParameters sysParameters)throws SQLException{
        sysParametersDao.status(sysParameters);
    }
}
