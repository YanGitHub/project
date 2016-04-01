package kj.pos.controller.product;

import kj.pos.controller.report.CompressPic;
import kj.pos.controller.util.ID;
import kj.pos.entity.PageUtil;
import kj.pos.entity.product.Brand;
import kj.pos.entity.product.Category;
import kj.pos.entity.product.ProductInfo;
import kj.pos.entity.product.ProductSku;
import kj.pos.service.product.BrandService;
import kj.pos.service.product.CategoryService;
import kj.pos.service.product.ProductService;
import kj.pos.service.product.ProductSkuService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Yan on 15-9-16.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    Log logger = LogFactory.getLog(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String product(){
        return "product/productList";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView productAdd()throws SQLException{
        return new ModelAndView("product/productAdd");
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(ProductInfo productInfo,
                                      @RequestParam(value = "page",defaultValue = "1")int page,
                                      @RequestParam(value = "rows",defaultValue = "10")int rows)throws SQLException{
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            int total = productService.getTotal(productInfo);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            productInfo.setPageUtil(pageUtil);
            List<ProductInfo> list = productService.getList(productInfo);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            map.put("total",0);
            map.put("rows",null);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/add/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> add(@RequestBody ProductInfo productInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            map = productService.add(productInfo);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",Boolean.FALSE);
            map.put("msg","添加商品失败");
        }
        return map;
    }

    @RequestMapping(value = "/getSkuList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getSkuList(ProductSku productSku,
                                         @RequestParam(value = "page",defaultValue = "1")int page,
                                         @RequestParam(value = "rows",defaultValue = "10")int rows){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            int total = productSkuService.getTotal(productSku);
            PageUtil pageUtil = new PageUtil(page,rows,total);
            productSku.setPageUtil(pageUtil);
            List<ProductSku> list = productSkuService.getList(productSku);
            map.put("total",total);
            map.put("rows",list);
        }catch (Exception e){
            e.printStackTrace();
            map.put("total",0);
            map.put("rows",null);
        }
        return map;
    }

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView image(HttpServletRequest request,
                              @RequestParam(value = "width",required = false)String width,
                              @RequestParam(value = "height",required = false)String height,
                              @RequestParam(value = "flog",required = false)Boolean flog)throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        if(width == "" || width == null){
            width = "120";
        }
        if(height == "" || height == null){
            height = "120";
        }
        try {
            String strPath = "";
            String strName = "";
            String StringID = ID.getGuid();
            request.setCharacterEncoding("utf-8");                                           //设置request编码，主要是为了处理普通输入框中的中文问题
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String path = request.getSession().getServletContext().getRealPath("/");   //获取当前路径
            logger.info("--------------------------"+path);
            strPath ="/image/";
            path += "//image//";
            path = path.replace("webapps\\ROOT\\//image//","webapps\\image\\");
            logger.info("--------------------------"+path);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();                                                         //创建文件夹
            }
            factory.setRepository(new File(path));                                    //设置文件的缓存路径
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(-1);                                                    //设置上传文件大小的上限，-1表示无上限（100*1024*1024）
            List items = new ArrayList();
            items = upload.parseRequest(request);                                     //上传文件，并解析出所有的表单字段，包括普通字段和文件字段
            Iterator it = items.iterator();                                           //(迭代器)
            while (it.hasNext()) {
                FileItem fileItem = (FileItem) it.next();
                if (fileItem.getName() != null && fileItem.getSize() != 0) {
                    String strItem = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
                    String realPath = path + StringID + "-o" + strItem;
                    File newFile = new File(realPath);                               //写入文件的路径
                    fileItem.write(newFile);
                    strPath += StringID + "-s" + strItem;
                    strName = fileItem.getName().substring(0,fileItem.getName().lastIndexOf(".")) + "-o" + strItem;
                    //图片处理
                    CompressPic mypic = new CompressPic();
                    System.out.println("输入的图片大小：" + mypic.getPicSize(realPath)/1024 + "KB");
                    //int count = 0; // 记录全部图片压缩所用时间
                    int start = (int) System.currentTimeMillis();   // 开始时间
                    //compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
                    mypic.compressPic(path, path, StringID + "-o" + strItem, StringID + "-s" + strItem, Integer.parseInt(width), Integer.parseInt(height), true);
                    int end = (int) System.currentTimeMillis(); // 结束时间
                    int re = end-start; // 但图片生成处理时间
                    System.out.println("图片压缩处理使用了: " + re + "毫秒");
                }
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=new Date();
            //更新图片路径
            ProductInfo productInfo = new ProductInfo();
            productInfo.setId(Long.parseLong(((FileItem)items.get(1)).getString()));
            productInfo.setAttachDate(simpleDateFormat.format(date));
            productInfo.setAttachName(strName);
            productInfo.setAttachHttp(strPath);
            productService.update(productInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return new ModelAndView("product/productList");
    }
}
