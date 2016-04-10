<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>采购订单</title>
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
        body{
            padding: 10px 15px 0px 15px;
        }
        .input-group-addon{
            width: 90px;
        }
        .breadcrumb{
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">仓库管理</a></li>
    <li class="active">采购订单</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-info" onclick="create()">新增</button>
    <button class="btn btn-primary" onclick="audit()">审核</button>
    <button class="btn btn-warning" onclick="cancel()">终止</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url:'${ctx}/stock/purchaseOrderMain/getList',
            title: '采购订单列表',
            singleSelect: true,
            selectOnCheck: true,
            pagination: true,
            rownumbers: true,
            height:430,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'ck', checkbox: true },
                    { field: 'billNo', title: '单据编号', fitColumns:true},
                    { field: 'billDate', title: '单据日期',fitColumns:true},
                    { field: 'purchaseTypeName', title: '采购类型',fitColumns:true},
                    { field: 'orgName', title: '店铺名称',fitColumns:true},
                    { field: 'warehouseName', title: '采购店仓',fitColumns:true},
                    { field: 'supplierInfoName', title: '供应商',fitColumns:true},
                    { field: 'status', title: '状态',fitColumns:true,formatter:statusString},
                    { field: 'createUser', title: '创建人',fitColumns:true},
                    { field: 'createDate', title: '创建日期',fitColumns:true},
                    { field: 'modifyUser', title: '修改人',fitColumns:true},
                    { field: 'modifyDate', title: '修改日期',fitColumns:true},
                    { field: 'auditUser', title: '审核人',fitColumns:true},
                    { field: 'auditDate', title: '审核日期',fitColumns:true},
                    { field: 'cancelUser', title: '终止人',fitColumns:true},
                    { field: 'cancelDate', title: '终止日期',fitColumns:true},
                    { field: 'note', title: '备注',fitColumns:true}
                ]
            ]
        });
    });

    function statusString(v,r,i){
        if(v == 1){
            return "未审核";
        }else if(v == 2){
            return "已审核";
        }else if(v == 3){
            return "已终止";
        }
    }
    //审核
    function audit(){

    }
    //终止
    function cancel(){

    }
    //跳到新增页面
    function create(){
        window.location.href = "${ctx}/stock/purchaseOrderMain/add"
    }
    //查询
    function search(){

    }

    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>