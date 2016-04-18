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
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-default" onclick="showDiv()">查询</button>
    <button class="btn btn-info" onclick="create()">新增</button>
    <button class="btn btn-success" onclick="see()">查看</button>
    <button class="btn btn-primary" onclick="audit()">审核</button>
    <button class="btn btn-warning" onclick="cancel()">终止</button>
    <button class="btn btn-info" onclick="exportExcel()">导出Excel</button>
</div>

<div id="searchDiv" style="margin-top: 10px" hidden="hidden">
    <div class="row">
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">单据编号</span>
                <input type="text" id="billNo" name="billNo" class="form-control" placeholder=""
                       style="width: 168px"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">采购类型</span>
                <select id="purchaseTypeCode" name="purchaseTypeCode" style="width: 168px"
                        class="form-control selectpicker">
                </select>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">店铺</span>
                <select id="orgCode" name="orgCode" style="width: 168px" onchange="joinShop(this)"
                        class="form-control selectpicker">
                </select>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">仓库</span>
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
                        开始日期
                    </span>
                    <input type='text' id="beginDate" name="begainDate" class="form-control" style="width: 168px"/>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker({
                    format: 'YYYY-MM-DD HH:mm:ss'
                });
            });
        </script>
        <div class='col-sm-3'>
            <div class="form-group">
                <div class='input-group input-group-sm date' id='datetimepicker2'>
                    <span class="input-group-addon">
                        结束日期
                    </span>
                    <input type='text' id="endDate" name="endDate" class="form-control" style="width: 168px"/>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker2').datetimepicker({
                    format: 'YYYY-MM-DD HH:mm:ss'
                });
            });
        </script>
        <div class="col-sm-3">
            <div class="btn-group btn-group-sm">
                <button class="btn btn-info" onclick="search()">搜索</button>
                <button class="btn btn-warning" onclick="reset()">重置</button>
            </div>
        </div>
    </div>
</div>

<div class="row" style="margin-top: 5px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>


<script type="text/javascript">
var exportUrl = '${ctx}/export/purchaseOrderReport';
$(document).ready(function () {
    $("#grid").datagrid({
        url: '${ctx}/stock/purchaseOrderMain/getList',
        title: '采购订单列表',
        selectOnCheck: true,
        pagination: true,
        rownumbers: true,
        striped: true,
        height: 430,
        columns: [
            [
                { field: 'id', hidden: true },
                { field: 'ck', checkbox: true },
                { field: 'billNo', title: '单据编号', fitColumns: true},
                { field: 'billDate', title: '单据日期', fitColumns: true},
                { field: 'status', title: '状态', fitColumns: true, formatter: statusString},
                { field: 'purchaseTypeName', title: '采购类型', fitColumns: true},
                { field: 'orgName', title: '店铺名称', fitColumns: true},
                { field: 'warehouseName', title: '采购店仓', fitColumns: true},
                { field: 'supplierInfoName', title: '供应商', fitColumns: true},
                { field: 'createUser', title: '创建人', fitColumns: true},
                { field: 'createDate', title: '创建日期', fitColumns: true},
                { field: 'modifyUser', title: '修改人', fitColumns: true},
                { field: 'modifyDate', title: '修改日期', fitColumns: true},
                { field: 'auditUser', title: '审核人', fitColumns: true},
                { field: 'auditDate', title: '审核日期', fitColumns: true},
                { field: 'cancelUser', title: '终止人', fitColumns: true},
                { field: 'cancelDate', title: '终止日期', fitColumns: true},
                { field: 'note', title: '备注', fitColumns: true}
            ]
        ]
    });
    loadShop();
    loadPurchaseTypes();
    loadSupplierInfo();
});
//加载 店铺
function loadShop() {
    $.ajax({
        async: false,
        type: 'POST',
        url: '${ctx}/common/getShops',
        contentType: "application/json",
        data: null,
        success: function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#orgCode').html(op);
                //第一次加载仓库
                loadStock(data[0].code);
            }
        }
    });
}
//根店铺加载仓库
function loadStock(orgCode) {
    $.ajax({
        async: false,
        type: 'POST',
        url: '${ctx}/common/getWarehouseByShopCode?pcode=' + orgCode,
        contentType: "application/json",
        success: function (data) {
            var op = "";
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
            } else {
                op = "";
            }
            $('#warehouseCode').html(op);
        }
    });
}
//与店铺下列联动
function joinShop(obj) {
    var orgCode = obj.options[obj.selectedIndex].value;
    loadStock(orgCode);
}
//加载 采购类型
function loadPurchaseTypes() {
    $.ajax({
        async: false,
        type: 'POST',
        url: '${ctx}/common/getPurchaseTypes',
        contentType: "application/json",
        data: null,
        success: function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#purchaseTypeCode').html(op);
            }
        }
    });
}

//加载 供应商
function loadSupplierInfo() {
    $.ajax({
        async: false,
        type: 'POST',
        url: '${ctx}/common/getSupplierInfo',
        contentType: "application/json",
        data: null,
        success: function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#supplierInfoCode').html(op);
            }
        }
    });
}
function statusString(v, r, i) {
    if (v == 1) {
        return "未审核";
    } else if (v == 2) {
        return "已审核";
    } else if (v == 3) {
        return "已终止";
    }
}
//查询
function showDiv() {
    $('#searchDiv').slideToggle(200);
}
//审核
function audit() {
    var item = $('#grid').datagrid('getChecked');
    var id = "";
    for (var i = 0; i < item.length; i++) {
        if (item[i].status == 1) {
            id += item[i].id + ",";
        }
    }
    if (id == "") {
        alertLittle("请选择未审核的数据");
        return;
    }
    $.post('${ctx}/stock/purchaseOrderMain/audit', {id: id}, function (data) {
        alert("提示", data.msg, 2000);
        $('#grid').datagrid('reload');
    });
}

//导出
function exportExcel(){
    window.location.href = exportUrl;
}
//终止
function cancel() {
    var item = $('#grid').datagrid('getChecked');
    var id = "";
    for (var i = 0; i < item.length; i++) {
        if (item[i].status == 1) {
            id += item[i].id + ",";
        }
    }
    if (id == "") {
        alertLittle("请选择未审核的数据");
        return;
    }
    $.post('${ctx}/stock/purchaseOrderMain/cancel', {id: id}, function (data) {
        alert("提示", data.msg, 2000);
        $('#grid').datagrid('reload');
    });
}
//跳到新增页面
function create() {
    window.location.href = "${ctx}/stock/purchaseOrderMain/add?id=" + 0;
}
//查看
function see() {
    var item = $('#grid').datagrid('getChecked');
    if (item.length < 1) {
        alertLittle("请选择您要查看的数据");
        return;
    }
    window.location.href = "${ctx}/stock/purchaseOrderMain/add?id=" + item[0].id
}
function reset(){
    $('#billNo').val("");
    $('#purchaseTypeCode').val("");
    $('#orgCode').val("");
    $('#warehouseCode').val("");
    $('#beginDate').val("");
    $('#endDate').val("");
}
//查询
function search() {
    var billNo = $('#billNo').val();
    var purchaseTypeCode = $('#purchaseTypeCode').val();
    var orgCode = $('#orgCode').val();
    var warehouseCode = $('#warehouseCode').val();
    var supplierInfoCode = $('#supplierInfoCode').val();
    var beginDate = $('#beginDate').val();
    var endDate = $('#endDate').val();
    $('#grid').datagrid('reload', {
        billNo: billNo,
        purchaseTypeCode: purchaseTypeCode,
        supplierInfoCode:supplierInfoCode,
        orgCode: orgCode,
        warehouseCode: warehouseCode,
        beginDate: beginDate,
        endDate: endDate
    });
    exportUrl = '${ctx}/export/purchaseOrderReport' +'?billNo='+billNo + '&purchaseTypeCode='+purchaseTypeCode + '&supplierInfoCode='+supplierInfoCode +'&orgCode=' + orgCode
            +'&warehouseCode='+warehouseCode +'&beginDate='+beginDate +'&endDate='+endDate;
}

$(window).resize(function () {
    $('#grid').datagrid('resize');
});

</script>
</body>
</html>