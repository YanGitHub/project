package kj.pos.service.template;


import kj.pos.dao.mysql.template.PrintTemplateDao;
import kj.pos.entity.template.PrintTemplate;
import kj.pos.util.web.WebContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Yan on 15-12-31.
 */
@Service
public class PrintTemplateService {

    Log logger = LogFactory.getLog(PrintTemplateService.class);

    @Autowired
    private PrintTemplateDao printTemplateDao;

    @Transactional(value = "mysql",rollbackFor = Exception.class)
    public void create(PrintTemplate printTemplate)throws SQLException{
        printTemplateDao.create(printTemplate);
    }

    public List<PrintTemplate> getList(PrintTemplate printTemplate)throws SQLException{
        printTemplate.setOrgCode(WebContextUtil.getOrganizationInfo().getCode());
        return printTemplateDao.getList(printTemplate);
    }
}
