<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>历史查询</title>
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
    <li><a href="#">我的工作台</a></li>
    <li class="active">历史查询</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-info" onclick="search()">查询</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<div class="easyui-tabs" style="margin-top: 8px">
    <div title="商品明细">
        <table id="gridProduct"></table>
    </div>
    <div title="支付明细">
        <table id="gridPayment"></table>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url:'${ctx}/pos/shopSalesDetail/getList',
            title: '历史订单查询',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height:250,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'flowNo', title: '小票号', width: 135 },
                    { field: 'saleDate', title: '销售日期', width: 135},
                    { field: 'vipNo', title: '会员卡', width: 120},
                    { field: 'saleTypeName', title: '销售类型', width: 120,align:'center'},
                    { field: 'isOnlineName', title: '是否网上订单', width: 120 ,align:'center'},
                    { field: 'fundAmount', title: '找零', width: 120,formatter:twoDecimal,align:'right'},
                    { field: 'note', title: '备注', width: 120}
                ]
            ],
            onClickRow: function (rowIndex, rowData) {
                $('#gridProduct').datagrid({url:'${ctx}/pos/shopSalesLine/getList?pid=' + rowData.id});
                $('#gridPayment').datagrid({url:'${ctx}/pos/shopSalesPay/getList?pid=' + rowData.id});
            }
        });

        $("#gridProduct").datagrid({
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height:200,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'productName', title: '商品名称', width: 135 },
                    { field: 'productCode', title: '商品代码', width: 135},
                    { field: 'skuName', title: '规格名称', width: 120},
                    { field: 'skuCode', title: '规格代码', width: 120,align:'center'},
                    { field: 'untPrice', title: '标准单价', width: 80,formatter:twoDecimal,align:'right'},
                    { field: 'saleDiscount', title: '销售折扣', width: 80,formatter:twoDecimal,align:'right'},
                    { field: 'disAmount', title: '折扣金额', width: 80,formatter:twoDecimal,align:'right'},
                    { field: 'qty', title: '销售数量', width: 80,formatter:twoDecimal,align:'right'},
                    { field: 'realAmount', title: '实售金额', width: 80,formatter:twoDecimal,align:'right'}
                ]
            ]
        });

        $("#gridPayment").datagrid({
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height:200,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'payName', title: '支付名称', width: 120},
                    { field: 'payNo', title: '支付代码', width: 120},
                    { field: 'amount', title: '支付金额', width: 100,formatter:twoDecimal,align:'right'}
                ]
            ]
        });
    });


    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>