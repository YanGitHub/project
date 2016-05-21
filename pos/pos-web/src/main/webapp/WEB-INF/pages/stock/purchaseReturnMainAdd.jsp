<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>采购退货单</title>
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
    <li class="active">采购退货单</li>
    <li class="active">新增</li>
    <li class="active">修改</li>
</ol>

<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" id="modifyBtn" onclick="modify()">修改</button>
    <button class="btn btn-info" id="saveBtn" onclick="save()">保存</button>
    <button class="btn btn-success" onclick="returnList()">返回</button>
</div>
<hr/>
<form id="editForm">
    <input id="id" name="id" hidden="hidden"/>
    <div class="row">
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">单据编号</span>
                <input type="text" id="billNo" name="billNo" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">退货类型<span style="color: red">*</span></span>
                <select id="purchaseReturnTypeCode" name="purchaseReturnTypeCode" style="width: 168px"
                        class="form-control selectpicker">
                </select>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">店铺<span style="color: red">*</span></span>
                <select id="orgCode" name="orgCode" style="width: 168px" onchange="joinShop(this)"
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
                        单据日期<span style="color: red">*</span>
                    </span>
                    <input type='text' id="billDate" name="billDate" class="form-control" style="width: 168px"/>
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
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">状态</span>
                <input type="text" id="status" name="status" class="form-control" placeholder="" readonly="readonly"
                       style="width: 168px"
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">备注</span>
                <input type="text" id="note" name="note" class="form-control" placeholder=""
                       style="width: 168px"
                       aria-describedby="basic-addon1">
            </div>
        </div>
    </div>
</form>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist" style="margin-top: 10px">

    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">采购明细</a>
    </li>
</ul>
<!-- Tab panes -->
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">

        <div class="btn-group btn-group-sm" style="margin-top: 10px">
            <button class="btn btn-info" id="newRowBtn" onclick="newRow()">新增行</button>
            <button class="btn btn-warning" id="delRowBtn" onclick="delRow()">删除行</button>
        </div>

        <div class="row" style="margin-top: 10px">
            <div class="col-sm-12">
                <table id="grid"></table>
            </div>
        </div>
    </div>
</div>
<!-- Nav tabs -->

<!--dialog-->
<div class="modal fade bs-example-modal-lg" id="productInfoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    添加商品
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">商品代码</span>
                            <input type="text" id="cprodcutCode" name="cprodcutCode" class="form-control" placeholder=""
                                   style="width: 168px"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">商品名称</span>
                            <input type="text" id="cproductName" name="cproductName" class="form-control" placeholder=""
                                   style="width: 168px"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="btn-group btn-group-sm">
                            <button class="btn btn-info" onclick="search()">搜索</button>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-sm-4">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">规格代码</span>
                            <input type="text" id="cskuCode" name="cskuCode" class="form-control" placeholder=""
                                   style="width: 168px"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">规格名称</span>
                            <input type="text" id="cskuName" name="cskuName" class="form-control" placeholder=""
                                   style="width: 168px"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="btn-group btn-group-sm">
                            <button class="btn btn-info" onclick="reset()">重置</button>
                        </div>
                    </div>
                </div>
                <!---grid-->
                <div class="row" style="margin-top: 10px">
                    <div class="col-sm-12">
                        <table id="productInfos" class="easyui-datagrid" data-options="
                               rownumbers: true,
                               url:'${ctx}/product/getSkuList',
                               height:280,
                               width:868,
                               pagination:true,
                               selectOnCheck:false"
                               toolbar="#toolbar">
                            <thead>
                            <tr>
                                <th data-options="field:'ck',checkbox:true"></th>
                                <th data-options="field:'id',hidden:true"></th>
                                <th data-options="field:'productCode',fitColumns:true,width:100">商品代码</th>
                                <th data-options="field:'productName',fitColumns:true,width:90">商品名称</th>
                                <th data-options="field:'code',fitColumns:true,width:100">规格代码</th>
                                <th data-options="field:'name',fitColumns:true,width:90">规格名称</th>
                                <th data-options="field:'gbCode',fitColumns:true,width:90">国标码</th>
                                <th data-options="field:'untPrice',fitColumns:true,width:90">标准售价</th>
                                <th data-options="field:'costPrice',fitColumns:true,width:90">成本价</th>
                                <th data-options="field:'usePrice',fitColumns:true,width:90">代理价</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="cAdd()">添加</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script type="text/javascript">
var url = "${ctx}/stock/purchaseReturnMain/create";
var edit = true;
$(document).ready(function () {
    $("#grid").datagrid({
        singleSelect: true,
        selectOnCheck: false,
        rownumbers: true,
        onClickRow:editCell,
        height: 250,
        columns: [
            [
                { field: 'ck', checkbox: true},
                { field: 'productSkuId', hidden: true },
                { field: 'productCode', title: '商品代码', width: 120},
                { field: 'productName', title: '商品名称', width: 120 },
                { field: 'skuCode', title: '规格代码', width: 120 },
                { field: 'skuName', title: '规格名称', width: 120 },
                { field: 'untPrice', title: '标准单价', width: 100, align: 'right', editor: {type: 'numberbox', options: {min: 0, max: 999999, precision: 2, readonly: 'readonly', required: true}}},
                { field: 'discount', title: '折扣', width: 100, align: 'right', editor: {type: 'validatebox', options: {required: true}}},
                { field: 'cosPrice', title: '入库单价', width: 100, align: 'right', formatter: twoDecimal, editor: {type: 'validatebox', options: {required: true}}},
                { field: 'qty', title: '采购数量', width: 100, align: 'right', editor: {type: 'validatebox', options: {required: true}}},
                { field: 'untAmount', title: '标准金额', width: 100, align: 'right', formatter: twoDecimal, editor: {type: 'numberbox', options: {min: 0, max: 999999, precision: 2, readonly: 'readonly', required: true}}},
                { field: 'comAmount', title: '采购金额', width: 100, align: 'right', formatter: twoDecimal, editor: {type: 'numberbox', options: {min: 0, max: 999999, precision: 2, readonly: 'readonly', required: true}}},
                { field: 'note', title: '备注', width: 120}
            ]
        ]
    });
    //加载下拉框
    loadShop();
    loadPurchaseReturnTypes();
    loadSupplierInfo();
    //加载明细
    if('${id}' != '0'){
        $.post('${ctx}/stock/purchaseReturnMain/getEdit',{id:${id}},function(data){
            url = "${ctx}/stock/purchaseReturnMain/update";
            $('#id').val(data.id);
            $('#billNo').val(data.billNo);
            $('#purchaseReturnTypeCode').val(data.purchaseReturnTypeCode);
            $('#orgCode').val(data.orgCode);
            loadStock(data.orgCode);
            $('#warehouseCode').val(data.warehouseCode);
            $('#supplierInfoCode').val(data.supplierInfoCode);
            $('#billDate').val(data.billDate);
            $('#note').val(data.note);
            //datagrid 不可编缉
            edit = false;
            //明细
            $('#grid').datagrid('loadData', { total: data.purchaseReturnDetailList.length, rows: data.purchaseReturnDetailList });
            //禁用
            disable();
            //状态
            $('#status').val(changeStatus(data.status));
        });
    }else{
        $('#modifyBtn').attr("disabled","disabled");
    }
});
function changeStatus(v){
    if(v == 1){
        return "未审核";
    }else if(v == 2){
        return "业务审核";
    }else if(v == 3){
        return "终止";
    }
}
function banding(index) {
    var editors = $('#grid').datagrid('getEditors', index);
    //给折扣绑定事件
    var discount = editors[1];
    discount.target.bind('keyup', function () {
        setCosAndCom(index);
    });
    //给数量绑定事件
    var qty = editors[3];
    qty.target.bind('keyup', function () {
        setUntAmountAndCosAmount(index);
    });
    //给采购金额绑定事件
    var cosPrice = editors[2];
    cosPrice.target.bind('keyup', function () {
        setDisCountAndCosAmount(index);
    });
}
var editIndex = undefined;
function endEditing() {
    if (editIndex == undefined) {
        return true;
    }
    if ($('#grid').datagrid('validateRow', editIndex)) {
        var rowLen = $('#grid').datagrid('getRows').length;
        for (var i = 0; i < rowLen; i++) {
            $('#grid').datagrid('unselectRow', i);
            $('#grid').datagrid('endEdit', i);
        }
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function editCell(index, row) {
    if (endEditing() && edit) {
        $('#grid').datagrid('selectRow', index).datagrid('beginEdit', index);
        editIndex = index;
        banding(index);
    }
}
//计算 折扣 采购金额
function setDisCountAndCosAmount(rowIndex) {
    var grid = $("#grid");
    //获取输入框对象
    var setQtyEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'qty'});
    var setUntPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'untPrice'});
    var setDiscountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'discount'});
    var setCosPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'cosPrice'});
    var setCosAmountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'comAmount'});
    var setUntAmountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'untAmount'});
    //获取输入框里的值
    var editQty = parseFloat(setQtyEdt.target.val());
    var editUntPrice = parseFloat(setUntPriceEdt.target.val());
    var editDiscount = parseFloat(setDiscountEdt.target.val());
    var editCosPrice = parseFloat(setCosPriceEdt.target.val());
    var editCosAmount = parseFloat(setCosAmountEdt.target.val());
    var editUntAmount = parseFloat(setUntAmountEdt.target.val());

    setDiscountEdt.target.val((parseFloat(editCosPrice / editUntPrice * 10)).toFixed(2));  //采购金额
    setCosAmountEdt.target.numberbox("setValue", parseFloat(editCosPrice * editQty).toFixed(2));  //标准金额
}
//计算 标准金额，采购金额
function setUntAmountAndCosAmount(rowIndex) {
    var grid = $("#grid");
    //获取输入框对象
    var setQtyEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'qty'});
    var setUntPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'untPrice'});
    var setDiscountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'discount'});
    var setCosPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'cosPrice'});
    var setCosAmountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'comAmount'});
    var setUntAmountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'untAmount'});
    //获取输入框里的值
    var editQty = parseFloat(setQtyEdt.target.val());
    var editUntPrice = parseFloat(setUntPriceEdt.target.val());
    var editDiscount = parseFloat(setDiscountEdt.target.val());
    var editCosPrice = parseFloat(setCosPriceEdt.target.val());
    var editCosAmount = parseFloat(setCosAmountEdt.target.val());
    var editUntAmount = parseFloat(setUntAmountEdt.target.val());

    setCosAmountEdt.target.numberbox("setValue", (parseFloat(editCosPrice * editQty).toFixed(2)));  //采购金额
    setUntAmountEdt.target.numberbox("setValue", (parseFloat(editUntPrice * editQty).toFixed(2)));  //标准金额
}
//计算 采购单价，采购金额
function setCosAndCom(rowIndex) {
    var grid = $("#grid");
    //获取输入框对象
    var setQtyEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'qty'});
    var setUntPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'untPrice'});
    var setDiscountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'discount'});
    var setCosPriceEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'cosPrice'});
    var setCosAmountEdt = grid.datagrid('getEditor', {index: rowIndex, field: 'comAmount'});
    //获取输入框里的值
    var editQty = parseFloat(setQtyEdt.target.val());
    var editUntPrice = parseFloat(setUntPriceEdt.target.val());
    var editDiscount = parseFloat(setDiscountEdt.target.val());
    var editCosPrice = parseFloat(setCosPriceEdt.target.val());
    var editCosAmount = parseFloat(setCosAmountEdt.target.val());

    setCosPriceEdt.target.val(parseFloat(editUntPrice * editDiscount / 10).toFixed(2));  //采购单价
    setCosAmountEdt.target.numberbox("setValue", (parseFloat(editUntPrice * editDiscount / 10) * editQty).toFixed(2));
}
//删除行
function delRow() {
    var items = $('#grid').datagrid('getChecked');
    for (var i = items.length - 1; i >= 0; i--) {
        var k = $('#grid').datagrid('getRowIndex', items[i]);
        $('#grid').datagrid('deleteRow', k);
    }
}
//打开商品列表
function newRow() {
    $('#productInfoModal').modal('show');
    setTimeout("$('#productInfos').datagrid('reload')", 500);
}
//商品搜索
function search() {
    var productCode = $('#cprodcutCode').val().trim();
    var productName = $('#cproductName').val().trim();
    var skuCode = $('#cskuCode').val().trim();
    var skuName = $('#cskuName').val().trim();
    var param = {productCode: productCode, productName: productName, code: skuCode, name: skuName};
    $("#productInfos").datagrid("load", param);
}
//重置搜索条件
function reset() {
    $('#cprodcutCode').val("");
    $('#cproductName').val("");
    $('#cskuCode').val("");
    $('#cskuName').val("");
}
//添加商品
function addSku(items) {
    $('#grid').datagrid('appendRow', {
        productSkuId: items.id,
        productCode: items.productCode,
        productName: items.productName,
        skuCode: items.code,
        skuName: items.name,
        untPrice: items.untPrice,
        discount: 10,
        cosPrice: items.untPrice,
        qty: 1,
        untAmount: items.untPrice,
        comAmount: items.untPrice,
        note: ''
    });

}
//添加商品判断
function cAdd() {
    var items = $('#productInfos').datagrid('getChecked');
    $('#productInfoModal').modal('hide');
    if (items.length == 0) {
        alertLittle("请选择商品数据...");
        return;
    } else {
        var skus = $('#grid').datagrid('getRows');
        if (skus.length == 0) {
            for (var i = 0; i < items.length; i++) {
                addSku(items[i]);
            }
        } else {
            for (var i = 0; i < items.length; i++) {
                var num = 0;
                for (var j = 0; j < skus.length; j++) {
                    if (skus[j].productSkuId == items[i].id) {
                        var untPrice = parseFloat(skus[j].untPrice);
                        var cosPrice = parseFloat(skus[j].cosPrice);
                        var qty = parseFloat(skus[j].qty) + 1;
                        $('#grid').datagrid('updateRow', {
                            index: j,
                            row: {
                                qty: qty,
                                untAmount: qty * untPrice,
                                comAmount: cosPrice * qty
                            }
                        });
                        break;
                    } else {
                        num++;
                    }
                }
                if (num == skus.length) {
                    addSku(items[i]);
                }
            }
        }
    }
}
//与店铺下列联动
function joinShop(obj) {
    var orgCode = obj.options[obj.selectedIndex].value;
    loadStock(orgCode);
}
//根店铺加载仓库
function loadStock(orgCode) {
    $.ajax({
        async:false,
        type: 'POST',
        url: '${ctx}/common/getWarehouseByShopCode?pcode=' + orgCode,
        contentType:"application/json",
        success: function(data){
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
//加载 店铺
function loadShop() {
    $.ajax({
        async:false,
        type: 'POST',
        url: '${ctx}/common/getShops',
        contentType:"application/json",
        data: null,
        success: function(data){
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

//加载 采购退货类型
function loadPurchaseReturnTypes() {
    $.ajax({
        async: false,
        type: 'POST',
        url: '${ctx}/common/getPurchaseReturnTypes',
        contentType: "application/json",
        data: null,
        success: function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
                $('#purchaseReturnTypeCode').html(op);
            }
        }
    });
}

//加载 供应商
function loadSupplierInfo() {
    $.ajax({
        async:false,
        type: 'POST',
        url: '${ctx}/common/getSupplierInfo',
        contentType:"application/json",
        data: null,
        success: function(data){
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

//禁用
function disable(){
    $('#billDate').attr("disabled","disabled");
    $('#purchaseReturnTypeCode').attr("disabled","disabled");
    $('#orgCode').attr("disabled","disabled");
    $('#warehouseCode').attr("disabled","disabled");
    $('#supplierInfoCode').attr("disabled","disabled");
    $('#note').attr("disabled","disabled");

    $('#newRowBtn').attr("disabled","disabled");
    $('#delRowBtn').attr("disabled","disabled");
    $('#saveBtn').attr("disabled","disabled");
}

//修改
function modify(){
    var status = $('#status').val();
    if(status != "未审核"){
        alertLittle("已经审核或已经终止订单不能修改");
        return;
    }
    $('#billDate').removeAttr("disabled","disabled");
    $('#purchaseReturnTypeCode').removeAttr("disabled","disabled");
    $('#orgCode').removeAttr("disabled","disabled");
    $('#warehouseCode').removeAttr("disabled","disabled");
    $('#supplierInfoCode').removeAttr("disabled","disabled");
    $('#note').removeAttr("disabled","disabled");
    $('#newRowBtn').removeAttr("disabled","disabled");
    $('#delRowBtn').removeAttr("disabled","disabled");
    $('#saveBtn').removeAttr("disabled","disabled");
    edit = true;
}

//保存
function save() {
    $('#grid').datagrid('selectRow', editIndex).datagrid('endEdit', editIndex);
    var data = $('#editForm').serializeObject();
    if(data.purchaseReturnTypeCode == null || data.purchaseReturnTypeCode == ""){
        alertLittle("请选择退货类型");
        return;
    }
    if(data.orgCode == null || data.orgCode == ""){
        alertLittle("请选择店铺");
        return;
    }
    if(data.warehouseCode == null || data.warehouseCode == ""){
        alertLittle("请选择仓库");
        return;
    }
    if(data.billDate == null || data.billDate == ""){
        alertLittle("请选择单据日期");
        return;
    }
    if(data.supplierInfoCode == null || data.supplierInfoCode == ""){
        alertLittle("请选择供应商");
        return;
    }
    data.status = 1;
    var items = $('#grid').datagrid('getRows');
    data.purchaseReturnDetailList = items;

    $.ajax({
        type: 'POST',
        url: url,
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(map){
            window.location.href = "${ctx}/stock/purchaseReturnMain";
        }
    });
}

//返回列表
function returnList() {
    window.location.href = "${ctx}/stock/purchaseReturnMain";
}
$(window).resize(function () {
    $('#grid').datagrid('resize');
});

</script>
</body>
</html>