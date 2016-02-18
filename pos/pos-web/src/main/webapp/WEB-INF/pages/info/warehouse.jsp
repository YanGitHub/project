<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>仓库信息</title>
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
    <li><a href="#">基础信息</a></li>
    <li class="active">仓库信息</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="createOpen()">新增</button>
    <button class="btn btn-success" onclick="modifyOpen()">修改</button>
    <button class="btn btn-warning" onclick="deleteOp()">删除</button>
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

                    <div class="row">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">仓库代码<font style="color: red">*</font></span>
                                <input type="text" id="code" name="code" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">仓库名称<font style="color: red">*</font></span>
                                <input type="text" id="name" name="name" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">上级组织<font style="color: red">*</font></span>
                                <select id="pcode" name="pcode" style="width: 168px"
                                    class="form-control selectpicker">

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
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">默认发货仓<font style="color: red">*</font></span>
                                <select id="isDefaultDeliver" name="isDefaultDeliver" style="width: 168px"
                                        class="form-control selectpicker">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">默认收货仓<font style="color: red">*</font></span>
                                <select id="isDefaultReceive" name="isDefaultReceive" style="width: 168px"
                                        class="form-control selectpicker">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">控制负库存<font style="color: red">*</font></span>
                                <select id="isNegativeStock" name="isNegativeStock" style="width: 168px"
                                        class="form-control selectpicker">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">联系人</span>
                                <input type="text" id="contactName" name="contactName" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">联系人电话</span>
                                <input type="text" id="contactPhone" name="contactPhone" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">联系人手机</span>
                                <input type="text" id="contactMobile" name="contactMobile" class="form-control"
                                       placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="padding-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">传真</span>
                                <input type="text" id="fax" name="fax" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">邮编</span>
                                <input type="text" id="post" name="post" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4"></div>
                    </div>

                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-12">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">备注</span>
                                <input type="text" id="note" name="note" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info" onclick="save()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url: '${ctx}/warehouse/getList',
            title: '仓库信息',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 430,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '仓库代码', fitColumns: true },
                    { field: 'name', title: '仓库名称', fitColumns: true},
                    { field: 'pname', title: '上级组织', fitColumns: true},
                    { field: 'contactName', title: '联系人', fitColumns: true},
                    { field: 'contactPhone', title: '联系人电话', fitColumns: true},
                    { field: 'contactMobile', title: '联系人手机', fitColumns: true},
                    { field: 'fax', title: '传真', fitColumns: true},
                    { field: 'post', title: '邮编', fitColumns: true},
                    { field: 'provinceCode', title: '省', fitColumns: true},
                    { field: 'cityCode', title: '市', fitColumns: true},
                    { field: 'districtCode', title: '区', fitColumns: true},
                    { field: 'address', title: '仓库地址', fitColumns: true},
                    { field: 'isDefaultDeliver', title: '是否是默认发货仓',align:'center', fitColumns: true,formatter:statusString},
                    { field: 'isDefaultReceive', title: '是否是默认收货仓',align:'center', fitColumns: true,formatter:statusString},
                    { field: 'isNegativeStock', title: '控制负库存',align:'center', fitColumns: true ,formatter:statusString},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
    });
    function statusString(v,r,i){
        if(v == 1){
            return '是';
        }else{
            return '否';
        }
    }
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

    function save() {
        var data = $('#editForm').serializeObject();
        if(data.code == null || data.code == ''){
            alert('提示','仓库代码不能为空',3000);
            return;
        }
        if(data.name == null || data.name == ''){
            alert('提示','仓库名称不能为空',3000);
            return;
        }
        $.ajax({
            type: 'POST',
            url: '${ctx}/warehouse/add',
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    alert("提示",map.msg,3000);
                    $('#grid').datagrid('reload');
                    $('#modifyDialog').modal('hide');
                }
            }
        });
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
            $('#pcode').html(op);
        });
    }
    //新增
    function createOpen() {
        $('#modifyDialog').modal('show');
        loadShop();//加载店铺
        getCity('root_china', 'provinceCode', false);
        getCity($('#provinceCode').val(), 'cityCode', false);
        getCity($('#cityCode').val(), 'districtCode', false);
    }
    //修改
    function modifyOpen() {

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