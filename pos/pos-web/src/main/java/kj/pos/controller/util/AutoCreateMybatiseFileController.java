package kj.pos.controller.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2014/10/23.
 */
@Controller
@RequestMapping("/createMyBatiseFile")
public class AutoCreateMybatiseFileController {

    @RequestMapping(value="",method = RequestMethod.GET)
    public String create(){
        return "util/createMyBatiseFile";
    }
    @RequestMapping(value = "/createFile",method = RequestMethod.POST)
    @ResponseBody
    public String createFile(String savePath,String packageName,String tableName){
        String result = null;
        try{
            AutoCreateMybatisFile autoCreateMybatisFile = new AutoCreateMybatisFile(savePath,tableName,packageName);
            autoCreateMybatisFile.startCreateFile();
            result = "true,文件创建成功-文件存放在"+savePath+"目录下";
        }catch (Exception e){
            e.printStackTrace();
            result = "false,"+e.getMessage();
        }
        return result;
    }
}

