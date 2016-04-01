<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>支付方式</title>
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
        .breadcrumb{
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">门店管理</a></li>
    <li class="active">支付方式</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createOp()">新增</button>
    <button class="btn btn-success" onclick="modifyOP()">修改</button>
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
                    修改支付方式
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
            url:'${ctx}/shopPayment/getList',
            nowrap: false,
            striped: true,
            title: '支付方式',
            height: 434,
            singleSelect: true,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'op', title: '操作', width: 80,align:'center',formatter:op},
                    { field: 'code', title: '支付代码', width: 120},
                    { field: 'name', title: '支付名称', width: 120},
                    { field: 'createDate', title: '创建日期', width: 140 },
                    { field: 'delName', title: '状态', align:'center', width: 120 },
                    { field: 'isDefaultName', title: '是否系统默认', align:'center', width: 120},
                    { field: 'note', title: '备注', width: 120}
                ]
            ]
        });
    });

    //操作
    function op(v,r,i){
        var btn = "";
        if(r.del == 0){
            btn = "<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"disableOp('" + r.id + "')\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span> 禁用</button>";
        }else{
            btn = "<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"ableOp('" + r.id + "')\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span> 启用</button>";
        }
        return btn;
    }

    //禁用
    function disableOp(id){
        $.post('${ctx}/shopPayment/op',{id:id,del:1},function(data){
            warning('操作',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }
    //启用
    function ableOp(id){
        $.post('${ctx}/shopPayment/op',{id:id,del:0},function(data){
            warning('操作',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }

    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>