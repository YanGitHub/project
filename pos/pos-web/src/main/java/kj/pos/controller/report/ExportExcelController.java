package kj.pos.controller.report;

import kj.pos.controller.util.BeanMapper;
import kj.pos.controller.util.CommonExcelView;
import kj.pos.entity.stock.PurchaseOrderMain;
import kj.pos.service.stock.PurchaseOrderMainService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 16-4-14.
 */
@Controller
@RequestMapping(value = "/export")
public class ExportExcelController {

    Log logger = LogFactory.getLog(ExportExcelController.class);

    @Autowired
    private PurchaseOrderMainService purchaseOrderMainService;

    @RequestMapping(value = "/purchaseOrderReport")
    public ModelAndView exportRefundNoReceiving(PurchaseOrderMain purchaseOrderMain) {
        CommonExcelView excelView = new CommonExcelView();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> results = new HashMap<String, Object>();
        List<Map> list = new ArrayList<Map>();
        try {
            list = BeanMapper.mapList(purchaseOrderMainService.getList(purchaseOrderMain), Map.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        String fileName = "采购订单报表";
        String[] titleCN = {"单据编号", "单据日期", "状态", "采购类型", "店铺名称", "采购店仓", "供应商", "创建人", "创建日期",
                "修改人", "修改日期", "审核人", "审核日期", "终止人", "终止日期", "备注"};
        result.put("单据编号", "billNo");
        result.put("单据日期", "billDate");
        result.put("状态", "statusStr");
        result.put("采购类型", "purchaseTypeName");
        result.put("店铺名称", "orgName");
        result.put("采购店仓", "warehouseName");
        result.put("供应商", "supplierInfoName");
        result.put("创建人", "createUser");
        result.put("创建日期", "createDate");
        result.put("修改人", "modifyUser");
        result.put("修改日期", "modifyDate");
        result.put("审核人", "auditUser");
        result.put("审核日期", "auditDate");
        result.put("终止人", "cancelUser");
        result.put("终止日期", "cancelDate");
        result.put("备注", "note");
        results.put("exportData", list);
        results.put("titleCN", titleCN);
        results.put("fileName", fileName);
        results.put("titleMap", result);
        return new ModelAndView(excelView, results);
    }
}
