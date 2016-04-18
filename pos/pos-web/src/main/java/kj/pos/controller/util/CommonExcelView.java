package kj.pos.controller.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Excel 导出公用类
 *
 * @author Yan Jingchao
 *         2014/4/2
 */
@SuppressWarnings({"unchecked"})
public class CommonExcelView extends AbstractExcelView {

    /**
     * @param params   传入参数 包含以下数据
     *                 fileName String 文件名称
     *                 titleCN String[] 导出excel表头中文
     *                 titleMap Map<String, Object> excel表头与字段中名称的对应关系
     *                 exportData List<Map>需要导出的数据
     * @param workbook excel
     * @param request  request
     * @param response response
     * @throws Exception 异常
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> params, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileName = params.get("fileName") + ".xls";
        String[] titles = (String[]) params.get("titleCN");
        Map<String, Object> titleMap = (Map<String, Object>) params.get("titleMap");
        List<Map> exportDatas = (List<Map>) params.get("exportData");

        HSSFSheet sheet = workbook.createSheet();
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            header.createCell(i).setCellValue(titles[i]);
        }

        int rowCount = 1;

        //导入数据
        for (Map exportData : exportDatas) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            for (int i = 0; i < titles.length; i++) {
                Object o = exportData.get(titleMap.get(titles[i]));
                if (o != null) {
                    String classSimpleName = o.getClass().getSimpleName();
                    //TODO more types ...
                    if (classSimpleName.equals("Double")) {
                        aRow.createCell(i).setCellValue((Double) o);
                    } else if (classSimpleName.equals("Timestamp") || classSimpleName.equals("Date")) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        aRow.createCell(i).setCellValue(df.format(o));
                    } else if (classSimpleName.equals("Long")) {
                        aRow.createCell(i).setCellValue((Long) o);
                    } else if (classSimpleName.equals("Boolean") || classSimpleName.equals("boolean")){
                        aRow.createCell(i).setCellValue(((Boolean) o) ? "是" : "否");
                    }
                     else   {
                        //默认转化为String
                        aRow.createCell(i).setCellValue(o.toString());
                    }
                }
            }
        }

        fileName = ReportUtils.encodeFilename(fileName, request);            //处理中文文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * @param response
     * @param request
     * @param list     查询的数据集
     * @param fileName 导出的文件名
     * @param titleCN  导出的第一行数据
     * @param titleMap 存放表头与数据字段的对应关系
     * @throws Exception
     */
    public void buildCsvDocument(HttpServletResponse response, HttpServletRequest request, List list, String fileName,
                                 String[] titleCN, Map<String, String> titleMap) throws Exception {
        fileName += ".csv";
        fileName = ReportUtils.encodeFilename(fileName, request);           //处理中文文件名
        response.setContentType("application/csv;charset=gbk");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        PrintWriter out = response.getWriter();
        for (int i = 0; i < titleCN.length; i++) {                             //遍历titleCN,表的第一行写入titleCN
            if (i == titleCN.length - 1) {
                out.print(titleCN[i] + "\n");
            } else {
                out.print(titleCN[i] + ",");
            }
        }
        for (Object object : list) {                                         //遍历list里的数据
            readClassAttr(object, out, titleCN, titleMap);
        }
        out.flush();
        out.close();
    }


    /**
     * @param o       list集全里面的对象
     * @param out     把结果放到缓冲区
     * @param titleCN 导出的第一行数据
     * @param map     存放表头与数据字段的对应关系
     * @throws Exception
     */
    private void readClassAttr(Object o, PrintWriter out, String[] titleCN, Map<String, String> map) throws Exception {
        if(o instanceof Map){
            Map<String, Object> entity = (Map<String, Object>) o;
            Set<String> keySet = entity.keySet();
            for (int n = 0; n < titleCN.length; n++) {
                String str = map.get(titleCN[n]);
                if(!entity.containsKey(str)){
                    entity.put(str,null);
                }
                for(String key : keySet){
                    Object value = entity.get(key);
                    if(key!=null&&key.equals(str)){
                        if(value!=null&&!"".equals(value.toString())){
                            String simpleClass = value.getClass().getSimpleName();
                            if (simpleClass.equals("Date")) {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                if (n == titleCN.length - 1) {
                                    out.print(df.format(value + "\n"));
                                } else {
                                    out.print(df.format(value + ","));
                                }
                            } else if (simpleClass.equals("Integer")) {
                                if (n == titleCN.length - 1) {
                                    out.print((Integer) value + "\n");
                                } else {
                                    out.print((Integer) value + ",");
                                }

                            } else if (simpleClass.equals("Double")) {
                                if (n == titleCN.length - 1) {
                                    out.print((Double) value + "\n");
                                } else {
                                    out.print((Double) value + ",");
                                }
                            } else {
                                String temp = value.toString().trim();
                                boolean result = temp.matches("[0-9]+");
                                if (result == true) {
                                    temp = "\t" + temp;
                                } else {
                                    //if (temp.indexOf(",")>0)
                                    temp = "\"" + temp + "\"";
                                }
                                if (n == titleCN.length - 1) {
                                    out.print(temp + "\n");
                                } else {
                                    out.print(temp + ",");
                                }
                            }
                        }else {                                                                 //如果字段没有值，做处理
                            if (n == titleCN.length - 1) {
                                out.print("" + "\n");
                            } else {
                                out.print("" + ",");
                            }
                        }
                        break;
                    }
                }
            }
        }else{
            Field[] fields = o.getClass().getDeclaredFields();
            for (int n = 0; n < titleCN.length; n++) {
                String str = map.get(titleCN[n]);
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().equals(str)) {
                        if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                            String simpleClass = field.get(o).getClass().getSimpleName();
                            if (simpleClass.equals("Date")) {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                if (n == titleCN.length - 1) {
                                    out.print(df.format(field.get(o)) + "\n");
                                } else {
                                    out.print(df.format(field.get(o)) + ",");
                                }
                            } else if (simpleClass.equals("Integer")) {
                                if (n == titleCN.length - 1) {
                                    out.print((Integer) field.get(o) + "\n");
                                } else {
                                    out.print((Integer) field.get(o) + ",");
                                }

                            } else if (simpleClass.equals("Double")) {
                                if (n == titleCN.length - 1) {
                                    out.print((Double) field.get(o) + "\n");
                                } else {
                                    out.print((Double) field.get(o) + ",");
                                }
                            } else {
                                String temp = field.get(o).toString().trim();
                                boolean result = temp.matches("[0-9]+");
                                if (result == true) {
                                    temp = "\t" + temp;
                                } else {
                                    //if (temp.indexOf(",")>0)
                                    temp = "\"" + temp + "\"";
                                }
                                if (n == titleCN.length - 1) {
                                    out.print(temp + "\n");
                                } else {
                                    out.print(temp + ",");
                                }
                            }
                        } else {                                                                 //如果字段没有值，做处理
                            if (n == titleCN.length - 1) {
                                out.print("" + "\n");
                            } else {
                                out.print("" + ",");
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void buildExcel(HttpServletResponse response, HttpServletRequest request, List list, String fileName,
                           String[] titleCN, Map<String, String> titleMap) throws Exception {
        fileName += ".xls";
        fileName = ReportUtils.encodeFilename(fileName, request);            //处理中文文件名
        response.setContentType("application/vnd.ms-excel");
        String data= new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" ";
        response.setHeader("Content-disposition", "attachment;filename=" +data+ fileName);
        OutputStream outputStream = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = null;
        long dataIndex = 0;
        int rowCount = 0;

        //遍历list里的数据
        for (Object o : list) {
            if (dataIndex % 65535 == 0) {
                rowCount = 0;
                long sheetIndex = dataIndex / 65535 + 1;
                sheet = workbook.createSheet("sheet" + sheetIndex);//行数超过65535重新创建sheet
                HSSFRow header = sheet.createRow(0);
                for (int i = 0; i < titleCN.length; i++) {
                    header.createCell(i).setCellValue(titleCN[i]);
                }
                rowCount += 1;
            }

            if (o instanceof Map) {
                Map<String, Object> entity = (Map<String, Object>) o;
                HSSFRow aRow = sheet.createRow(rowCount++);
                Set<String> keySet = entity.keySet();

                for (int i = 0; i < titleCN.length; i++) {
                    String str = titleMap.get(titleCN[i]);
                    if(!entity.containsKey(str)){
                        entity.put(str,null);
                    }
                    for (String key : keySet) {
                        if (key.equals(str)) {
                            if (entity.get(key) != null && !"".equals(entity.get(key).toString())) {
                                Object value = entity.get(key);
                                String simpleClass = value.getClass().getSimpleName();
                                if (simpleClass.equals("Double")) {
                                    aRow.createCell(i).setCellValue((Double) entity.get(key));
                                } else if (simpleClass.equals("Timestamp") || simpleClass.equals("Date")) {
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    aRow.createCell(i).setCellValue(df.format(entity.get(key)));
                                } else if (simpleClass.equals("Integer")) {
                                    aRow.createCell(i).setCellValue((Integer) entity.get(key));
                                } else if (simpleClass.equals("Long")) {
                                    aRow.createCell(i).setCellValue((Long) entity.get(key));
                                } else if (simpleClass.equals("Boolean") || simpleClass.equals("boolean")) {
                                    aRow.createCell(i).setCellValue(((Boolean) entity.get(key)) ? "是" : "否");
                                } else {
                                    //默认转化为String
                                    aRow.createCell(i).setCellValue(entity.get(key).toString());
                                }
                            } else {
                                aRow.createCell(i).setCellValue("");
                            }
                            break;
                        }
                    }
                }
            } else {
                HSSFRow aRow = sheet.createRow(rowCount++);
                Field[] fields = o.getClass().getDeclaredFields();
                for (int i = 0; i < titleCN.length; i++) {
                    String str = titleMap.get(titleCN[i]);
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.getName().equals(str)) {
                            if (field.get(o) != null && !"".equals(field.get(o).toString())) {
                                String simpleClass = field.get(o).getClass().getSimpleName();
                                if (simpleClass.equals("Double")) {
                                    aRow.createCell(i).setCellValue((Double) field.get(o));
                                } else if (simpleClass.equals("Timestamp") || simpleClass.equals("Date")) {
                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    aRow.createCell(i).setCellValue(df.format(field.get(o)));
                                } else if (simpleClass.equals("Integer")) {
                                    aRow.createCell(i).setCellValue((Integer) field.get(o));
                                } else if (simpleClass.equals("Boolean") || simpleClass.equals("boolean")) {
                                    aRow.createCell(i).setCellValue(((Boolean) field.get(o)) ? "是" : "否");
                                } else {
                                    //默认转化为String
                                    aRow.createCell(i).setCellValue(field.get(o).toString());
                                }
                            } else {
                                aRow.createCell(i).setCellValue("");
                            }
                            break;
                        }
                    }
                }
            }
            dataIndex++;
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
