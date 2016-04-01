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
        body {
            padding: 10px 15px 0px 15px;
        }

        .input-group-addon {
            width: 90px;
        }

        .breadcrumb {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">仓库管理</a></li>
    <li class="active">采购订单</li>
    <li class="active">新增</li>
</ol>

<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="save()">保存</button>
    <button class="btn btn-info" onclick="returnList()">返回</button>
</div>
<hr/>
<div class="row">
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">单据编号</span>
            <input type="text" id="billNo" name="billNo" class="form-control" placeholder="" style="width: 168px"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">采购类型<span style="color: red">*</span></span>
            <select id="purchaseTypeCode" name="purchaseTypeCode" style="width: 168px"
                    class="form-control selectpicker">
            </select>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">店铺<span style="color: red">*</span></span>
            <select id="shopCode" name="shopCode" style="width: 168px" onchange="joinShop(this)"
                    class="form-control selectpicker">
            </select>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">仓库<span style="color: red">*</span></span>
            <select id="warehouseCode" name="warehouseCode" style="width: 168px"
                    class="form-control selectpicker">
            </select>
        </div>
    </div>
</div>

<div class="row" style="margin-top: 10px">
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">供应商<span style="color: red">*</span></span>
            <select id="supplierInfoCode" name="supplierInfoCode" style="width: 168px"
                    class="form-control selectpicker">
            </select>
        </div>
    </div>

    <div class='col-sm-3'>
        <div class="form-group">
            <div class='input-group input-group-sm date' id='datetimepicker1'>
                    <span class="input-group-addon">
                        单据日期
                    </span>
                <input type='text' id="billDate" name="billDate" class="form-control" style="width: 168px" />
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#datetimepicker1').datetimepicker();
        });
    </script>

    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">备注</span>
            <input type="text" id="note" name="note" class="form-control" placeholder=""
                   style="width: 168px"
                   aria-describedby="basic-addon1">
        </div>
    </div>
</div>


<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist" style="margin-top: 10px">

    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">采购明细</a>
    </li>
</ul>
<!-- Tab panes -->
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">

        <div class="btn-group btn-group-sm" style="margin-top: 10px">
            <button class="btn btn-info" onclick="newRow()">新增行</button>
            <button class="btn btn-warning" onclick="delRow()">删除行</button>
        </div>

        <div class="row" style="margin-top: 10px">
            <div class="col-sm-12">
                <table id="grid"></table>
            </div>
        </div>
    </div>
</div>
<!-- Nav tabs -->


<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 250,
            columns: [
                [
                    { field: 'productSkuId', hidden: true },
                    { field: 'productCode', title: '商品代码', width: 120 },
                    { field: 'productName', title: '商品名称', width: 120 },
                    { field: 'skuCode', title: '规格代码', width: 120 },
                    { field: 'skuName', title: '规格名称', width: 120 },
                    { field: 'untPrice', title: '标准单价', width: 100 },
                    { field: 'discount', title: '折扣', width: 100},
                    { field: 'cosPrice', title: '入库单价', width: 100 },
                    { field: 'qty', title: '采购数量', width: 100},
                    { field: 'untAmount', title: '标准金额', width: 100},
                    { field: 'comAmount', title: '采购金额', width: 100},
                    { field: 'note', title: '备注', width: 120}

                ]
            ]
        });

        loadShop();
        loadPurchaseTypes();
        loadSupplierInfo();
    });

    //与店铺下列联动
    function joinShop(obj) {
        var shopCode = obj.options[obj.selectedIndex].value;
        loadStock(shopCode);
    }
    //根店铺加载仓库
    function loadStock(shopCode) {
        $.post('${ctx}/common/getWarehouseByShopCode', {pcode: shopCode}, function (data) {
            var op = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
            } else {
                op = "";
                alertLittle("请为店铺代码为:" + shopCode + "的维护仓库");
            }
            $('#warehouseCode').html(op);
        });
    }
    //加载 店铺
    function loadShop() {
        $.post('${ctx}/common/getShops', null, function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#shopCode').html(op);
                //第一次加载仓库
                loadStock(data[0].code);
            }
        });
    }

    //加载 采购类型
    function loadPurchaseTypes() {
        $.post('${ctx}/common/getPurchaseTypes', null, function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#purchaseTypeCode').html(op);
            }
        });
    }

    //加载 供应商
    function loadSupplierInfo() {
        $.post('${ctx}/common/getSupplierInfo', null, function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#supplierInfoCode').html(op);
            }
        });
    }

    //保存
    function save() {

    }

    //返回列表
    function returnList() {
        window.location.href = "${ctx}/stock/purchaseOrderMain";
    }
    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>