<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>供应商类型</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

        .breadcrumb {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">基础信息</a></li>
    <li class="active">供应商类型</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createOpen()">新增</button>
    <button class="btn btn-success" onclick="ModifyOpen()">修改</button>
    <button class="btn btn-warning" onclick="deleteOp()">删除</button>
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
                    新增/修改
                </h4>
            </div>
            <div class="modal-body">
                <input id="id" hidden="hidden" type="text"/>

                <div class="row" style="margin-top: 10px">
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">类型代码</span>
                            <input type="text" id="code" name="barcode" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">类型名称</span>
                            <input type="text" id="name" name="barcode" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>

                <div class="row" style="margin-top: 10px">
                    <div class="col-sm-12">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">备注</span>
                            <input type="text" id="note" name="note" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info" onclick="create()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url: '${ctx}/supplierType/getList',
            title: '供应商类型',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 430,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '供应商类型代码', width: 120 },
                    { field: 'name', title: '供应商类型名称', width: 120},
                    { field: 'note', title: '备注', width: 120}
                ]
            ]
        });
    });

    //保存
    function create() {
        var code = $('#code').val();
        var name = $('#name').val();
        var note = $('#note').val();
        $.post('${ctx}/supplierType/create',{code:code,name:name,note:note},function(msg){
            createClose();
            alert('提示',msg.msg,3000);
            $('#grid').datagrid('reload');
        });
    }
    //打开新增、修改对话框
    function createOpen() {
        $('#modifyDialog').modal('show');
    }
    //关闭新增、修改对话框
    function createClose() {
        $('#modifyDialog').modal('hide');
    }
    //修改
    function ModifyOpen() {

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