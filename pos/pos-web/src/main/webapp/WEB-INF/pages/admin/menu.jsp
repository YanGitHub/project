<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>角色信息</title>
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

        .form-control {
            width: 166px;
        }

        .breadcrumb {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
<!--main-->
<ol class="breadcrumb">
    <li><a href="#">系统管理</a></li>
    <li class="active">菜单维护</li>
</ol>

<div class="btn-group btn-group-sm">
    <button class="btn btn-success" onclick="collapseBtn()">折叠</button>
    <button class="btn btn-primary" onclick="newMenu()">新增</button>
    <button class="btn btn-info" onclick="modifyOpen()">修改</button>
</div>

<div class="row" style="margin-top: 10px">
    <div class="col-sm-12">
        <ul id="treeview" class="easyui-tree"></ul>
    </div>
</div>

<!--dialog-->
<div class="modal fade" id="newModal" style="height: 600px">
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
                <form id="editTreeForm">
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">菜单编码</span>
                                <input type="text" id="code" name="code" class="form-control"
                                       placeholder="" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">菜单名称</span>
                                <input type="text" id="name" name="name" class="form-control"
                                       placeholder="" aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">菜单路径</span>
                                <input type="text" id="url" name="url" class="form-control"
                                       placeholder="" aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addMenu()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<script type="text/javascript">
    $(function(){
        $("#treeview").tree({
            rownumbers: true,
            idField: 'id',
            textField: 'name',
            parentField: 'pid',
            checkbox: false,
            url: '${ctx}/menu/getMenu'
        });
    });

    function addMenu(){
        var item = $('#treeview').tree('getSelected');
        var pid = 0;
        if(item != null){
            pid = item.id;
        }
        var data = $('#editTreeForm').serializeObject();
        data.pid = pid;
        $.ajax({
            type: 'POST',
            url: '${ctx}/menu/add',
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    alert("提示",map.msg,3000);
                    $('#treeview').tree('reload');
                    $('#newModal').modal('hide');
                }
            }
        });
    }

    //折叠
    function collapseBtn(){
        $('#treeview').tree('collapseAll');
    }
    //新增菜单
    function newMenu(){
        $('#newModal').modal('show');
    }
</script>
</body>
</html>