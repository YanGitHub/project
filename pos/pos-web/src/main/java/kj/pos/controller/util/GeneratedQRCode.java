package kj.pos.controller.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;

import com.google.zxing.common.BitMatrix;
import kj.pos.util.qrCode.MatrixToImageWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yan on 16-4-18.
 */
@Controller
@RequestMapping("/generatedQRCode")
public class GeneratedQRCode {

    Log logger = LogFactory.getLog(GeneratedQRCode.class);

    public boolean flag;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String generatedQRCode(){
        return "util/generatedQRCode";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> create(HttpServletRequest request,
                                     @RequestParam(value = "url",required = false,defaultValue = "http://www.baidu.com")String url,
                                     @RequestParam(value = "content",required = false)String content)throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            String strPath = "";
            String id = ID.getGuid();
            logger.info("--------------------------"+path);
            strPath ="/image/";
            deleteDirectory(path + "//image//");
            path += "//image//"+id+".jpg";
            logger.info("--------------------------"+path);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();                                                         //创建文件夹
            }
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
            map.put("path",strPath + id + ".jpg");
            map.put("status", Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("path","");
            map.put("status", Boolean.FALSE);
        }
        return map;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
