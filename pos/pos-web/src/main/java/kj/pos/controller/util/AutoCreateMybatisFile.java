package kj.pos.controller.util;

/**
 * Created by Yan on 15-9-25.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoCreateMybatisFile {
    private static  List<TableInfo> tableInfos = null;
    private static  String url = null;
    private static  String driver = null;
    private static  String user = null;
    private static  String password = null;
    private String saveDiv;
    private String tableName;
    private String packagePath;

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        try{
            initData(tableName);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("初始化数据失败，请检测数据库连接是否正常，或者你的表名是否存在");
        }

        this.tableName = tableName;
    }

    public String getSaveDiv() {
        return saveDiv;
    }

    public void setSaveDiv(String saveDiv) {
        this.saveDiv = saveDiv;
    }
    public AutoCreateMybatisFile(){
        this.saveDiv = "D:/code";
    }
    public AutoCreateMybatisFile(String saveDiv,String tableName,String packagePath){
        try{
            this.saveDiv = saveDiv;
            this.tableName = tableName;
            this.packagePath = packagePath;
            initData(tableName);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("初始化数据失败，请检测数据库连接是否正常，或者你的表名是否存在");
        }
    }
    private void initData(String tableName) throws Exception{
        tableInfos = getTableInfosByTableName(tableName);
    }

    public List<TableInfo> getTableInfosByTableName(String tableName) throws Exception{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("show full columns from "+tableName);
        List<TableInfo> infos = new ArrayList<TableInfo>();
        TableInfo info = null;
        while (rs.next()){
            info = new TableInfo();
            info.setComment(rs.getString(9));
            info.setFiled(rs.getString(1));
            info.setType(rs.getString(2));
            infos.add(info);
        }
        this.close(connection);
        return infos;
    }
    private Connection getConnection(){
        Connection connection = null;
        url = "jdbc:MYSQL://127.0.0.1/pos?useUnicode=true&characterEncoding=utf-8";
        driver = "com.mysql.jdbc.Driver";
        user = "root";
        password = "root";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    private void close(Connection connection){
        try{
            if(connection!=null&&!connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void createMapperFile(List<TableInfo> tableInfos, String tableName) throws Exception{
        String javaFileName = getJavaFileName(tableName);
        File file = new File(saveDiv+"/"+javaFileName+"Mapper.xml");
        if (!file.exists())
            file.createNewFile();
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        sb.append("<mapper namespace=\""+packagePath.replaceAll(".entity.",".dao.")+"."+javaFileName+"Dao\">\n");
        //生成resultMap
        sb.append("\t<resultMap id=\"ResultMap\" type=\""+javaFileName+"\">\n");
        String type = null;
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t<result property=\""+formatFiled(tableInfo.getFiled())+"\" column=\""+tableInfo.getFiled()+"\"/>\n");
        }
        sb.append("\t</resultMap>\n");
        sb.append("\n");
        //生成getList查询
        sb.append("\t<select id=\"getList\" resultMap=\"ResultMap\" parameterType=\""+javaFileName+"\" >\n");
        sb.append("\t\tselect \n");
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t\t"+tableInfo.getFiled()+",\n");
        }
        sb = new StringBuffer(sb.substring(0,sb.length()-2)+"\n");
        sb.append("\t\tfrom "+tableName+"\n");
        sb.append("\t\twhere 1=1 \n");
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t<if test=\""+formatFiled(tableInfo.getFiled())+"!=null\">\n");
            sb.append("\t\t\t\tand "+tableInfo.getFiled()+"=#{"+formatFiled(tableInfo.getFiled())+"}\n");
            sb.append("\t\t</if>\n");
        }
        sb.append("\t</select>\n");
        //生成insert
        sb.append("\t<insert id=\"create\" parameterType=\""+javaFileName+"\">\n");
        sb.append("\t\tinsert into "+tableName+"(\n");
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t\t"+tableInfo.getFiled()+",\n");
        }
        sb = new StringBuffer(sb.substring(0,sb.length()-2)+"\n\t\t)");
        sb.append(" values (\n");
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t\t#{"+formatFiled(tableInfo.getFiled())+"},\n");
        }
        sb = new StringBuffer(sb.substring(0,sb.length()-2)+"\n\t\t)\n");
        sb.append("\t</insert>\n");
        sb.append("\n");
        //生成update
        sb.append("\t<update id=\"update\" parameterType=\""+javaFileName+"\">\n");
        sb.append("\t\tupdate "+tableName+" set \n");
        for(TableInfo tableInfo : tableInfos){
            sb.append("\t\t<if test=\""+formatFiled(tableInfo.getFiled())+"!=null\">\n");
            sb.append("\t\t\t"+tableInfo.getFiled()+"=#{"+formatFiled(tableInfo.getFiled())+"},\n");
            sb.append("\t\t</if>\n");
        }
        sb = new StringBuffer(sb.substring(0,sb.length()-10)+"\n\t\t</if>\n");
        sb.append("\t</update>\n");
        sb.append("</mapper>\n");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
    }

    private void createJavaFile(List<TableInfo> tableInfos, String javaFileName) throws Exception{
        File file = new File(saveDiv+"/"+javaFileName+".java");
        if (!file.exists())
            file.createNewFile();
        StringBuffer sb = new StringBuffer();
        sb.append("package "+packagePath+";\n");
        sb.append("\n");
        sb.append("import org.apache.ibatis.type.Alias;\nimport java.kj.pos.util.Date;\n");
        sb.append("import java.io.Serializable;\n");
        sb.append("\n");
        sb.append("@Alias(\""+javaFileName+"\")\n");
        sb.append("public class "+javaFileName+" implements Serializable{\n");
        sb.append("\t//创建字段\n");
        createFiles(sb);
        sb.append("\t//创建getter和setter方法\n");
        createGetAndSetMethod(sb);
        sb.append("}");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
    }
    private void createFiles(StringBuffer sb){
        String type = null;
        for(TableInfo tableInfo : tableInfos){
            type = tableInfo.getType();
            if(type.startsWith("bigint")){
                sb.append("\tprivate Long "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }else if(type.startsWith("datetime")||type.startsWith("timestamp")){
                sb.append("\tprivate Date "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }else if(type.startsWith("int")||type.startsWith("integer")){
                sb.append("\tprivate Integer "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }else if(type.startsWith("tinyint")||type.startsWith("bit")){
                sb.append("\tprivate Boolean "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }else if(type.startsWith("decimal")||type.startsWith("double")){
                sb.append("\tprivate Double "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }else{
                sb.append("\tprivate String "+formatFiled(tableInfo.getFiled())+";"+"//"+tableInfo.getComment()+"\n");
            }
        }
    }
    private void createGetAndSetMethod(StringBuffer sb){
        String type = null;
        for(TableInfo tableInfo : tableInfos){
            type = tableInfo.getType();
            if(type.startsWith("bigint")){
                sb.append("\tpublic Long get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(Long "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }else if(type.startsWith("datetime")||type.startsWith("timestamp")){
                sb.append("\tpublic Date get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(Date "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }else if(type.startsWith("int")||type.startsWith("integer")){
                sb.append("\tpublic Integer get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(Integer "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }else if(type.startsWith("tinyint")||type.startsWith("bit")){
                sb.append("\tpublic Boolean get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(Boolean "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }else if(type.startsWith("decimal")||type.startsWith("double")){
                sb.append("\tpublic Double get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(Double "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }else{
                sb.append("\tpublic String get"+getJavaFileName(tableInfo.getFiled())+"(){\n");
                sb.append("\t\treturn this."+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
                sb.append("\tpublic void set"+getJavaFileName(tableInfo.getFiled())+"(String "+formatFiled(tableInfo.getFiled())+"){\n");
                sb.append("\t\tthis."+formatFiled(tableInfo.getFiled())+" = "+formatFiled(tableInfo.getFiled())+";\n");
                sb.append("\t}\n");
                sb.append("\n");
            }
        }
    }
    private String formatFiled(String filed){
        String returnFiled = "";
        char[] cs = filed.toCharArray();
        for(int i = 0;i<cs.length;i++){
            char c = cs[i];
            if(String.valueOf(c).equals("_")){
                c = cs[i+1];
                returnFiled += String.valueOf(c).toUpperCase();
                i+=1;
            }else{
                returnFiled += String.valueOf(c);
            }
        }
        return returnFiled;
    }

    public String getJavaFileName(String tableName){
        String javaFileName = "";
        char[] cs = tableName.toCharArray();
        for(int i = 0;i<cs.length;i++){
            char c = cs[i];
            if(i==0){
                javaFileName += String.valueOf(c).toUpperCase();
            }else{
                if(String.valueOf(c).equals("_")){
                    c = cs[i+1];
                    javaFileName += String.valueOf(c).toUpperCase();
                    i+=1;
                }else{
                    javaFileName += String.valueOf(c);
                }
            }
        }
        return javaFileName;
    }
    public void startCreateFile(){
        if(tableName == null){
            throw new RuntimeException("tableName不可为空");
        }
        File file = new File(saveDiv);
        if (!file.exists()){
            file.mkdirs();
        }
        String javaFileName = getJavaFileName(tableName);
        try{
            createJavaFile(tableInfos,javaFileName);
            createMapperFile(tableInfos,tableName);
            createDao();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("创建文件异常");
        }

    }

    private void createDao() throws Exception{
        String javaFileName = getJavaFileName(tableName);
        String filedName = formatFiled(tableName);
        File file = new File(saveDiv+"/"+javaFileName+"Dao.java");
        if (!file.exists())
            file.createNewFile();
        StringBuffer sb = new StringBuffer();
        sb.append("package "+packagePath.replaceAll(".entity.",".dao.")+";\n");
        sb.append("\n");
        sb.append("import kj.o2o.core.mybatis.MyBatisRepository;\n");
        sb.append("import "+packagePath+"."+javaFileName+";\n");
        sb.append("import java.kj.pos.util.List;\n");
        sb.append("@MyBatisRepository\n");
        sb.append("public interface "+javaFileName+"Dao{\n");
        sb.append("\tpublic List<"+javaFileName+"> getList("+javaFileName+" "+filedName+");\n");
        sb.append("\tpublic void create("+javaFileName+" "+filedName+");\n");
        sb.append("\tpublic void update("+javaFileName+" "+filedName+");\n");
        sb.append("}");
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
    }
}