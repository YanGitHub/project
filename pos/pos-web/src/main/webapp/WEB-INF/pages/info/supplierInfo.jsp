<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>供应商信息</title>
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
    <li><a href="#">基础资料</a></li>
    <li class="active">供应商信息</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createOpen()">新增</button>
    <button class="btn btn-success" onclick="ModifyOpen()">修改</button>
    <button class="btn btn-warning" onclick="deleteOp()">删除</button>
    <button class="btn btn-info" onclick="showDiv()">查询</button>
</div>

<div id="searchDiv" style="margin-top: 10px" hidden="hidden">
    <div class="row">
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">供应商代码</span>
                <input type="text" id="varcode" name="varcode" class="form-control" placeholder=""
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">供应商名称</span>
                <input type="text" id="varname" name="varname" class="form-control" placeholder=""
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">类型</span>
                <select id="vartypeCode" name="vartypeCode" style="width: 168px"
                        class="form-control selectpicker">
                </select>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="btn-group btn-group-sm">
                <button class="btn btn-info" onclick="showDiv()">GO</button>
            </div>
        </div>
    </div>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<div class="modal fade bs-example-modal-lg" id="modifyDialog">
    <div class="modal-dialog modal-lg">
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
                    <input id="id" hidden="hidden" type="text"/>

                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">供应商代码<font style="color: red">*</font></span>
                                <input type="text" id="code" name="code" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">供应商名称<font style="color: red">*</font></span>
                                <input type="text" id="name" name="name" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">类型</span>
                                <select id="typeCode" name="typeCode" style="width: 168px"
                                        class="form-control selectpicker">
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">联系人</span>
                                <input type="text" id="person" name="person" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">手机</span>
                                <input type="text" id="phone" name="phone" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">电话</span>
                                <input type="text" id="mobile" name="mobile" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">邮箱</span>
                                <input type="text" id="email" name="email" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">网址</span>
                                <input type="text" id="website" name="website" class="form-control" placeholder=""
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">地址</span>
                                <input type="text" id="address" name="address" class="form-control" placeholder=""
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
                </form>
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
            url: '${ctx}/supplierInfo/getList',
            title: '供应商信息',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 430,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '供应商代码', fitColumns: true},
                    { field: 'name', title: '供应商名称', fitColumns: true},
                    { field: 'typeCode', title: '供应商类型代码', fitColumns: true},
                    { field: 'typeName', title: '供应商类型名称', fitColumns: true},
                    { field: 'person', title: '联系人', fitColumns: true},
                    { field: 'phone', title: '手机', fitColumns: true},
                    { field: 'mobile', title: '电话', fitColumns: true},
                    { field: 'email', title: '邮箱', fitColumns: true},
                    { field: 'website', title: '网址', fitColumns: true},
                    { field: 'address', title: '地址', fitColumns: true},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
        loadSupplierType();
    });

    function showDiv() {
        $('#searchDiv').slideToggle(200);
    }
    //加载 供应商类型
    function loadSupplierType() {
        $.post('${ctx}/common/getSupplierType', null, function (data) {
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
            }
            $('#typeCode').html(op);
            $('#vartypeCode').html(op);

        });
    }

    //保存
    function create() {
        var data = $('#editForm').serializeObject();
        if(data.code == null || data.code == ""){
            alertLittle("代码不能为空");
            return;
        }
        if(data.name == null || data.name == ""){
            alertLittle("名称不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            url: '${ctx}/supplierInfo/create',
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    $('#grid').datagrid('reload');
                    $('#modifyDialog').modal('hide');
                    $("#editForm :input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");//核心
                }
                alert("提示",map.msg,3000);
            }
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