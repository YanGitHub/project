<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>门店资料</title>
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

    <style>
        body {
            padding: 10px 15px 0px 15px;
        }

        .input-group-addon {
            width: 90px;
        }

        .form-control {
            width: 168px;
        }

        .breadcrumb {
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
    <button class="btn btn-primary" onclick="openDialog()">新增</button>
</div>

<div class="row" style="margin-top: 10px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<!--dialog-->
<div class="modal fade bs-example-modal-lg" id="myModal">
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
                    <input id="id" name="id" hidden="hidden"/>

                    <div class="row">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">店铺代码<font style="color: red">*</font></span>
                                <input type="text" id="code" name="code" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">店铺名称<font style="color: red">*</font></span>
                                <input type="text" id="name" name="name" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">类型<font style="color: red">*</font></span>
                                <select id="property" name="property" style="width: 168px"
                                        class="form-control selectpicker">
                                    <option value="1">店铺</option>
                                    <option value="2">服务商</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">省<font style="color: red">*</font></span>
                                <select id="provinceCode" name="provinceCode" style="width: 168px"
                                        onchange="getvalue(this,'cityCode',true)" class="form-control selectpicker">

                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">市<font style="color: red">*</font></span>
                                <select id="cityCode" name="cityCode" style="width: 168px"
                                        onchange="getvalue(this,'districtCode',false)"
                                        class="form-control selectpicker">

                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">区<font style="color: red">*</font></span>
                                <select id="districtCode" name="districtCode" style="width: 168px"
                                        class="form-control selectpicker">

                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">地址<font style="color: red">*</font></span>
                                <input type="text" id="address" name="address" class="form-control" placeholder="" style="width: 466px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">联系人</span>
                                <input type="text" id="person" name="person" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">手机</span>
                                <input type="text" id="phone" name="phone" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">电话</span>
                                <input type="text" id="mobile" name="mobile" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">邮箱</span>
                                <input type="text" id="email" name="email" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">网址</span>
                                <input type="text" id="website" name="website" class="form-control" placeholder="" style="width: 168px"
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

    $(function () {
        $('#grid').datagrid({
            nowrap: false,
            striped: true,
            url: '${ctx}/organization/getList',
            title: '门店列表',
            height: 430,
            singleSelect: true,
            pagination: true,
            columns: [
                [
                    {field: 'id', title: '', hidden: true},
                    {field: 'code', title: '店铺代码', width:100},
                    {field: 'name', title: '店铺名称', width:100},
                    {field: 'property', title: '类型', width:100,align:'center', formatter: propertyString},
                    {field: 'status', title: '状态', width:100, align: 'center', formatter: statusString},
                    {field: 'person', title: '联系人', width:100},
                    {field: 'phone', title: '手机', fitColumns: true},
                    {field: 'mobile', title: '电话', fitColumns: true},
                    {field: 'email', title: '邮箱', fitColumns: true},
                    {field: 'website', title: '网址', fitColumns: true},
                    { field: 'provinceCode', title: '省', fitColumns: true},
                    { field: 'cityCode', title: '市', fitColumns: true},
                    { field: 'districtCode', title: '区', fitColumns: true},
                    {field: 'address', title: '地址', fitColumns: true}
                ]
            ]
        });
    });
    function getvalue(obj, se, d) {
        var parentId = obj.options[obj.selectedIndex].value;
        getCity(parentId, se, d);
    }
    //获取省市区 根据上级id
    function getCity(parentId, select, d) {
        var param = {parentId: parentId};
        $.ajax({
            url: '${ctx}/common/getRegions',
            type: 'post',
            dataType: "json",
            async: false,
            data: JSON.stringify(param),
            processData: false,
            contentType: 'application/json',
            success: function (data) {
                if (data.length > 0) {
                    var op = "";
                    for (var i = 0; i < data.length; i++) {
                        op += "<option value=" + data[i].regionId + ">" + data[i].regionName + "</option>";
                    }
                }
                if (d) {
                    getCity(data[0].regionId, 'districtCode');
                }
                $('#' + select).html(op);
            }
        });
    }
    //状态
    function statusString(v, r, i) {
        if (v == 1) {
            return "可用";
        } else {
            return "禁用";
        }
    }
    //
    function propertyString(v, r, i) {
        if (v == 1) {
            return "店铺";
        } else if (v == 2) {
            return "服务商";
        }
    }
    //打开新增窗口
    function openDialog() {
        $('#id').val("");
        $('#code').val("");
        $('#name').val("");
        $('#address').val("");
        $('#person').val("");
        $('#mobile').val("");
        $('#phone').val("");
        $('#email').val("");
        $('#website').val("");
        getCity('root_china', 'provinceCode', false);
        getCity($('#provinceCode').val(), 'cityCode', false);
        getCity($('#cityCode').val(), 'districtCode', false);
        $('#myModal').modal('show');
    }
    //新增系统参数
    function create() {
        var data = $('#editForm').serializeObject();
        if(data.code == null || data.code == ""){
            alert('提示','请输入店铺代码',3000);
            return;
        }
        if(data.name == null || data.name == ""){
            alert('提示','请输入店铺名称',3000);
            return;
        }
        $.ajax({
            type: 'POST',
            url: '${ctx}/organization/save',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (map) {
                if (map.status) {
                    alert("提示", map.msg, 3000);
                    $('#grid').datagrid('reload');
                    $('#myModal').modal('hide');
                }
            }
        });
    }

</script>
</body>
</html>