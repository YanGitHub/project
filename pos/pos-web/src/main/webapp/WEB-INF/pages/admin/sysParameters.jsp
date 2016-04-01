<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>mybaties文件创建</title>
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
    <li><a href="#">系统管理</a></li>
    <li class="active">系统参数</li>
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
                    新增/修改系统参数
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
            url : '${ctx}/sysParameters/getList',
            title: '系统参数',
            height: 430,
            singleSelect: true,
            pagination: true,
            columns: [
                [
                    {field: 'id', title: '', hidden: true},
                    {field: 'sysKey', title: '参数名', width: 120, fitColumns: true},
                    {field: 'sysValue', title: '参数值', width: 70, align: 'center', fitColumns: true},
                    {field: 'status', title: '状态', width: 100, fitColumns: true,align: 'center',formatter:statusString},
                    {field: 'note', title: '备注', width: 200, fitColumns: true},
                    {field: 'op', title: '操作', width: 120, align: 'center', fitColumns: true, formatter: opration}
                ]
            ]
        });
    });
    //状态
    function statusString(v,r,i){
        if(v){
            return "可用";
        }else{
            return "禁用";
        }
    }
    //打开新增窗口
    function openDialog() {
        $('#id').val("");
        $('#sysKey').val("");
        $('#sysValue').val("");
        $('#note').val("");
        $('#sysKey').removeAttr('readonly');
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
        $.post('${ctx}/sysParameters/status',{id:id,status:false},function(data){
            alert('提示',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }

    function ableOp(id){
        $.post('${ctx}/sysParameters/status',{id:id,status:true},function(data){
            alert('提示',data.msg,3000);
            $('#grid').datagrid('reload');
        });
    }
    function modify(i){
        var row = $('#grid').datagrid('getRows')[i];
        $('#sysKey').attr('readonly','readonly');
        $('#id').val(row.id);
        $('#sysKey').val(row.sysKey);
        $('#sysValue').val(row.sysValue);
        $('#note').val(row.note);
        $('#myModal').modal('show');
    }
</script>
</body>
</html>