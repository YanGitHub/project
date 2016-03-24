<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>库存统计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css" />
    <!--图片上传-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-fileInput/css/fileinput.min.css" />

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
    <li class="active">库存统计</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="search()">查询</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url:'${ctx}/inventory/getList',
            title: '库存统计',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height:430,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'warehouseCode', title: '仓库代码', width: 120 },
                    { field: 'productCode', title: '商品代码', width: 120 },
                    { field: 'skuCode', title: '规格代码', width: 120},
                    { field: 'qty', title: '数量', width: 140 }
                ]
            ]
        });
    });

    //查询
    function search(){

    }

    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>