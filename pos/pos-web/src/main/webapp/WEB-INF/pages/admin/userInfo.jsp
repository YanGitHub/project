<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户信息</title>
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
    <li class="active">用户信息</li>
</ol>

<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="openDialog()">新增</button>
    <button class="btn btn-info" onclick="modifyOpen()">修改</button>
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
                    新增/修改用户信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input id="id" name="id" hidden="hidden"/>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">用户编码</span>
                                <input type="text" id="code" name="code" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">用户名称</span>
                                <input type="text" id="name" name="name" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">密码</span>
                                <input type="password" id="password" name="password" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">上级组织<font style="color: red">*</font></span>
                                <select id="orgCode" name="orgCode" style="width: 168px"
                                        class="form-control selectpicker">

                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">手机</span>
                                <input type="text" id="mobile" name="mobile" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">电话</span>
                                <input type="text" id="phone" name="phone" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>

                    </div>

                    <div class="row" style="padding-top: 15px">
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">邮箱</span>
                                <input type="text" id="email" name="email" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">备注</span>
                                <input type="text" id="note" name="note" class="form-control"
                                       placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="create()">保存</button>
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
            url : '${ctx}/userInfo/getList',
            title: '用户信息',
            height: 430,
            singleSelect: true,
            pagination: true,
            columns: [
                [
                    {field: 'id', title: '', hidden: true},
                    {field: 'op', title: '操作', width: 120, align: 'center', fitColumns: true, formatter: opration},
                    {field: 'code', title: '用户编码', width: 120},
                    {field: 'name', title: '用户名称', width: 120},
                    {field: 'orgName', title: '上级织组', width: 120},
                    {field: 'status', title: '状态', width: 120,align:'center',formatter:statusString},
                    {field: 'phone', title: '电话', width: 120},
                    {field: 'mobile', title: '手机', width: 120},
                    {field: 'email', title: '电子邮件', width: 140},
                    {field: 'createDate', title: '创建日期', width: 140},
                    {field: 'note', title: '备注', width: 120}
                ]
            ]
        });
        loadShop();
    });
    //状态
    function statusString(v,r,i){
        if(v){
            return "可用";
        }else{
            return "禁用";
        }
    }
    //加载 店铺
    function loadShop(){
        $.post('${ctx}/common/getShops',null,function(data){
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
            }
            $('#orgCode').html(op);
        });
    }

    //打开新增窗口
    function openDialog() {
        $('#id').val("");
        $('#code').val("");
        $('#name').val("");
        $('#phone').val("");
        $('#mobile').val("");
        $('#email').val("");
        $('#orgCode').val("");
        $('#note').val("");
        $('#myModal').modal('show');
    }

    //新增系统参数
    function create() {
        var data = $('#editForm').serializeObject();
        if(data.code == null || data.code == ''){
            alert('提示','请输入用户编码',3000);
            return;
        }
        if(data.name == null || data.name == ''){
            alert('提示','请输入用户名称',3000);
            return;
        }
        if(data.password == null || data.password == ''){
            alert('提示','请输入用户密码',3000);
            return;
        }
        $.ajax({
            type: 'POST',
            url: '${ctx}/userInfo/save',
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    alert("提示",map.msg,3000);
                    $('#grid').datagrid('reload');
                    $('#myModal').modal('hide');
                }
            }
        });
    }

    //操作
    function opration(v,r,i){
        var btn = "";
        if(r.status){
            btn = "<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"disableOp('" + r.id + "')\"><span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\"></span> 禁用</button>";
        }else{
            btn = "<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"ableOp('" + r.id + "')\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span> 启用</button>";
        }
        btn += "&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"modify('" + i + "')\"><span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span> 修改</button>";
        return btn;
    }

    function disableOp(id){
        $.post('${ctx}/userInfo/status',{id:id,status:0},function(data){
            alert('提示',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }

    function ableOp(id){
        $.post('${ctx}/userInfo/status',{id:id,status:1},function(data){
            alert('提示',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }
    //判断 修改时是否选中数据
    function modifyOpen(){
        var item = $('#grid').datagrid('getSelected');
        var index = $('#grid').datagrid('getRowIndex',item);
        if(index != null && index != -1){
            modify(index);
        }else{
            alert('提示','请选择数据',3000);
            return;
        }
    }
    function modify(i){
        var row = $('#grid').datagrid('getRows')[i];
        $('#id').val(row.id);
        $('#code').val(row.code);
        $('#name').val(row.name);
        $('#password').val(row.password);
        $('#phone').val(row.phone);
        $('#mobile').val(row.mobile);
        $('#email').val(row.email);
        $('#orgCode').val(row.orgCode);
        $('#note').val(row.note);
        $('#myModal').modal('show');
    }
</script>
</body>
</html>