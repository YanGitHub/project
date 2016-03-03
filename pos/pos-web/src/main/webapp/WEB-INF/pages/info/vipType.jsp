<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>会员类型</title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css"/>
    <!--图片上传-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-fileInput/css/fileinput.min.css"/>

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
    <li class="active">会员类型</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createOpen()">新增</button>
    <button class="btn btn-success" onclick="modifyOpen()">修改</button>
    <button class="btn btn-warning" onclick="deleteOp()">删除</button>
</div>

<div class="row" style="margin-top: 10px">
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">代码</span>
            <input type="text" id="code" name="code" class="form-control" placeholder="" style="width: 168px"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">名称</span>
            <input type="text" id="name" name="name" class="form-control" placeholder="" style="width: 168px"
                   aria-describedby="basic-addon1">
        </div>
    </div>

</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>



<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url: '${ctx}/vipType/getList',
            title: '会员类型',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 400,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '代码', fitColumns: true },
                    { field: 'name', title: '名称', fitColumns: true},
                    { field: 'consDiscount', title: '折扣', fitColumns: true,align:'right'},
                    { field: 'createDate', title: '创建日期', fitColumns: true},
                    { field: 'validDate', title: '有效期', fitColumns: true},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
    });

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