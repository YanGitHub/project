<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加商品</title>
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
    <li><a href="#">商品管理</a></li>
    <li class="active">商品条码</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createBarCode()">生成条码</button>
    <button class="btn btn-success" onclick="openModifyDialog()">修改</button>
    <button class="btn btn-warning" onclick="deleteBarcode()">删除</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<div class="modal fade" id="modifyDialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    修改条码
                </h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding-top: 10px">
                    <div class="col-sm-12">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">商品条码</span>
                            <input type="text" id="barcode" name="barcode" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                            <input id="id" hidden="hidden" type="text"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info" onclick="modify()">修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url:'${ctx}/product/barcode/list',
            title: '商品条码',
            singleSelect: true,
            selectOnCheck: true,
            rownumbers: true,
            height:460,
            pagination: true,
            columns: [
                [
                    { field: 'ck',checkbox:true},
                    { field: 'id', hidden: true },
                    { field: 'barcode', title: '商品条码', width: 120 },
                    { field: 'productCode', title: '商品代码', width: 120},
                    { field: 'productName', title: '商品名称', width: 120},
                    { field: 'skuCode', title: '规格代码', width: 120 },
                    { field: 'skuName', title: '规格名称', width: 120}
                ]
            ],
            onClickRow: function (rowIndex, rowData) {

            }
        });
    });

    function createBarCode(){
        $.post('${ctx}/product/barcode/create',null,function(data){
            alertLittle(data.msg);
            $('#grid').datagrid('reload');
        });
    }

    function openModifyDialog(){
        var item = $('#grid').datagrid('getChecked');
        if(item.length == 0){
            alertLittle("请选择您要修改的数据！");
            return;
        }
        if(item[0].id == null || item[0].id == ""){
            alertLittle("请先成功条码！");
            return;
        }
        $('#barcode').val(item[0].barcode);
        $('#id').val(item[0].id);
        $('#modifyDialog').modal('show');
    }

    function modify(){
        var id = $('#id').val();
        var barcode = $('#barcode').val().trim();
        $.post('${ctx}/product/barcode/modify',{id:id,barcode:barcode},function(data){
            if(data.status){
                $('#modifyDialog').modal('hide');
                alertLittle(data.msg);
                $('#grid').datagrid('reload');
            }else{
                alertLittle(data.msg);
            }
        });
    }

    function deleteBarcode(){
        var item = $('#grid').datagrid('getChecked');
        if(item.length == 0){
            alertLittle("请选择您要删除的数据!");
            return;
        }
        $.confirm({
            title: '是否删除条码?',
            content: '删除后收银时将无法扫描,是否继续?',
            autoClose: 'cancel|6000',
            confirm: function(){
                var idArray = "";
                for(var i = 0;i < item.length;i++){
                    idArray += item[i].id + ",";
                }
                $.post('${ctx}/product/barcode/delete',{idArray:idArray},function(data){
                    alertLittle(data.msg);
                    $('#grid').datagrid('reload');
                });
            },
            cancel:function(){
            }
        });
    }
    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>