<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门店资料</title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css"/>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/respond.min.js"></script>
    <![endif]-->

    <style>
        body{
            padding: 10 15px 10px 15px;
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
    <li class="active">门店资料</li>
</ol>

<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="openDialog()">创建</button>
</div>

<div class="row" style="margin-top: 10px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<!--dialog-->
<div class="modal fade" id="myModal">
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
                <form id="editForm">
                    <input id="id" name="id" hidden="hidden"/>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">参数名</span>
                                <input type="text" id="sysKey" name="sysKey" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">参数值</span>
                                <input type="text" id="sysValue" name="sysValue"
                                       class="form-control"
                                       placeholder="" aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">备注&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" id="note" name="note" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="create()" data-dismiss="modal">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${ctx}/static/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/static/jquery-easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-confirm/js/jquery-confirm.js"></script>
<script type="text/javascript" src="${ctx}/static/pos/js/common.js"></script>
<script type="text/javascript">

    $(function(){
        $('#grid').datagrid({
            nowrap: false,
            striped: true,
            url : '${ctx}/organization/getList',
            title: '门店列表',
            height: 430,
            singleSelect: true,
            pagination: true,
            columns: [
                [
                    {field: 'id', title: '', hidden: true},
                    {field: 'code', title: '店铺代码', fitColumns: true},
                    {field: 'name', title: '店铺名称', align: 'center', fitColumns: true},
                    {field: 'status', title: '状态', fitColumns: true,align: 'center',formatter:statusString},
                    {field: 'property', title: '类型',  fitColumns: true},
                    {field: 'person', title: '联系人',  fitColumns: true},
                    {field: 'phone', title: '手机',  fitColumns: true},
                    {field: 'mobile', title: '电话',  fitColumns: true},
                    {field: 'email', title: '邮箱',  fitColumns: true},
                    {field: 'website', title: '网址',  fitColumns: true},
                    {field: 'address', title: '地址',  fitColumns: true}
                ]
            ]
        });
    });
    //状态
    function statusString(v,r,i){
        if(v == 1){
            return "可用";
        }else{
            return "禁用";
        }
    }
    //打开新增窗口
    function openDialog() {

        $('#myModal').modal('show');
    }
    //新增系统参数
    function create() {
        var data = $('#editForm').serializeObject();
        $.ajax({
            type: 'POST',
            url: '${ctx}/sysParameters/save',
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    alert("提示",map.msg,3000);
                    $('#grid').datagrid('reload');
                }
            }
        });
    }

</script>
</body>
</html>