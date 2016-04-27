<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>物流公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css"/>
    <!--图片上传-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-fileInput/css/fileinput.min.css"/>
    <!--日期-->
    <link href="${ctx}/static/bootstrap-datetimepicker/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap-datetimepicker/css/prettify-1.0.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap-datetimepicker/css/base.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-datetimepicker/css/default.css">

    <script src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script src="${ctx}/static/jquery-easyui/easyui-lang-zh_CN.js"></script>
    <!--弹出窗-->
    <script type="text/javascript" src="${ctx}/static/jquery-confirm/js/jquery-confirm.js"></script>
    <script type="text/javascript" src="${ctx}/static/pos/js/common.js"></script>
    <!--图片上传js-->
    <script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput_locale_zh.js"></script>
    <!--日期-->
    <script src="${ctx}/static/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
    <script src="${ctx}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

    <!--[if lt IE 9]>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            padding: 10px 15px 0px 15px;
        }

        .input-group-addon {
            width: 90px;
        }

        .form-control {
            width: 166px;
        }

        .breadcrumb {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">基础信息</a></li>
    <li class="active">员工信息</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-default" onclick="search()">查询</button>
    <button class="btn btn-primary" onclick="createOpen()">新增</button>
    <button class="btn btn-success" onclick="modifyOpen()">修改</button>
    <button class="btn btn-warning" onclick="deleteOp()">删除</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url: '${ctx}/info/employeeInfo/getList',
            title: '员工信息',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 430,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '员工代码', fitColumns: true },
                    { field: 'name', title: '员工名称', fitColumns: true},
                    { field: 'orgCode', title: '店铺代码', fitColumns: true},
                    { field: 'orgName', title: '店铺名称', fitColumns: true},
                    { field: 'sex', title: '性别', fitColumns: true,formatter: sexString},
                    { field: 'isJob', title: '状态', fitColumns: true,formatter: isJobString},
                    { field: 'birthday', title: '生日', fitColumns: true},
                    { field: 'identityCard', title: '身份证', fitColumns: true},
                    { field: 'phone', title: '电话', fitColumns: true},
                    { field: 'mobile', title: '手机', fitColumns: true},
                    { field: 'qq', title: 'QQ', fitColumns: true},
                    { field: 'email', title: 'Email', fitColumns: true},
                    { field: 'wangwang', title: '旺旺', fitColumns: true},
                    { field: 'weixin', title: '微信', fitColumns: true},
                    { field: 'address', title: '地址', fitColumns: true},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
    });

    function sexString(v,r,i){
        if(v == 0){
            return "未定义";
        }else if(v == 1){
            return "男";
        }else if(v == 2){
            return "女";
        }
    }

    function isJobString(v,r,i){
        if(v == 0){
            return "在职";
        }else if(v == 1){
            return "离职";
        }
    }
    //查询
    function search(){

    }

    function save() {

    }

    //新增
    function createOpen() {

    }
    //修改
    function modifyOpen() {

    }
    //delete
    function deleteOp() {

    }
    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>