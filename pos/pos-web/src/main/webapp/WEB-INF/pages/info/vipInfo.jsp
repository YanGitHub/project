<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>会员管理</title>
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
    <li><a href="#">基础信息</a></li>
    <li class="active">会员管理</li>
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

<div style="margin-top: 10px">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">基础信息</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <div class="row" style="margin-top: 10px">
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">电话</span>
                        <input type="text" id="tel_" name="tel_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">手机</span>
                        <input type="text" id="phone_" name="phone_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">生日</span>
                        <input type="text" id="birthday_" name="birthday_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">身份证</span>
                        <input type="text" id="identityCard_" name="identityCard_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px">
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">QQ</span>
                        <input type="text" id="qq_" name="qq_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">Email</span>
                        <input type="text" id="email_" name="email_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">邮编</span>
                        <input type="text" id="post_" name="post_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">会员密码</span>
                        <input type="password" id="pwd_" name="pwd_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px">
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">省</span>
                        <input type="text" id="provinceCode_" name="provinceCode_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">市</span>
                        <input type="text" id="cityCode_" name="cityCode_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">区</span>
                        <input type="text" id="districtCode_" name="districtCode_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">地址</span>
                        <input type="text" id="address_" name="address_" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Nav tabs -->
</div>

<div class="modal fade bs-example-modal-lg" id="myDialog">
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
                                <span class="input-group-addon">会员卡号<font style="color: red">*</font></span>
                                <input type="text" id="code" name="code" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">会员名称<font style="color: red">*</font></span>
                                <input type="text" id="name" name="name" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">性别<font style="color: red">*</font></span>
                                <select id="sex" name="sex" style="width: 168px"
                                        class="form-control selectpicker">
                                    <option value="0">女</option>
                                    <option value="1">男</option>
                                    <option value="2">保密</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">店铺</span>
                                <select id="shopCode" name="shopCode" style="width: 168px"
                                        class="form-control selectpicker">
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">会员类型<font style="color: red">*</font></span>
                                <select id="vipTypeCode" name="vipTypeCode" style="width: 168px"
                                        class="form-control selectpicker">
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">会员密码</span>
                                <input type="password" id="pwd" name="pwd" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">电话</span>
                                <input type="text" id="tel" name="tel" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">手机</span>
                                <input type="text" id="phone" name="phone" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">生日</span>
                                <input type="text" id="birthday" name="birthday" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>

                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">QQ</span>
                                <input type="text" id="qq" name="qq" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">Email</span>
                                <input type="text" id="email" name="email" class="form-control" placeholder="" style="width: 168px"
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

                    </div>

                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">省</span>
                                <select id="provinceCode" name="provinceCode" style="width: 168px"
                                        onchange="getvalue(this,'cityCode',true)" class="form-control selectpicker">

                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">市</span>
                                <select id="cityCode" name="cityCode" style="width: 168px"
                                        onchange="getvalue(this,'districtCode',false)"
                                        class="form-control selectpicker">

                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">区</span>
                                <select id="districtCode" name="districtCode" style="width: 168px"
                                        class="form-control selectpicker">

                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">身份证</span>
                                <input type="text" id="identityCard" name="identityCard" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">地址</span>
                                <input type="text" id="address" name="address" class="form-control" placeholder="" style="width: 168px"
                                       aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <div class="col-sm-4">
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
                <button type="button" class="btn btn-info" onclick="createNo()">生成会员卡号</button>
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            url: '${ctx}/vipInfo/getList',
            title: '会员管理',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height: 280,
            pagination: true,
            pageSize:10,
            onClickRow: showDetail,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '会员卡号', fitColumns: true },
                    { field: 'name', title: '会员名称', fitColumns: true},
                    { field: 'sex', title: '性别', fitColumns: true,formatter:changeSex},
                    { field: 'vipTypeName', title: '会员类型', fitColumns: true},
                    { field: 'shopCode', title: '店铺代码', fitColumns: true},
                    { field: 'shopName', title: '店铺名称', fitColumns: true},
                    { field: 'createDate', title: '创建日期', fitColumns: true},
                    { field: 'issuingPerson', title: '发放人', fitColumns: true},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
        //加载 店铺
        loadShop();
        //加载 类型
        loadVipType();
        //加载省市区
        getCity('root_china', 'provinceCode', false);
        getCity($('#provinceCode').val(), 'cityCode', false);
        getCity($('#cityCode').val(), 'districtCode', false);
    });

    //性别转换
    function changeSex(v,r,i){
        if(v == "0"){
            return "女";
        }else if(v == "1"){
            return "男";
        }else{
            return "保密";
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
            $('#shopCode').html(op);
        });
    }

    //加载 类型
    function loadVipType(){
        $.post('${ctx}/common/getVipTypes',null,function(data){
            if (data.length > 0) {
                var op = "";
                for (var i = 0; i < data.length; i++) {
                    op += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
                }
            }
            $('#vipTypeCode').html(op);
        });
    }
    //生成会员卡号
    function createNo(){
        var id = $('#id').val();
        if(id == "" || id == null){
            $('#code').val(getSerialNo('vip'));
        }else{
            alertLittle("只有新增时才可以生成会员卡号");
            return;
        }

    }
    function getvalue(obj, se, d) {
        var parentId = obj.options[obj.selectedIndex].value;
        getCity(parentId, se, d);
    }

    //显示明细
    function showDetail(rowIndex, rowData){
        $('#tel_').val(rowData.tel);
        $('#phone_').val(rowData.phone);
        $('#birthday_').val(rowData.birthday);
        $('#identityCard_').val(rowData.identityCard);
        $('#qq_').val(rowData.qq);
        $('#email_').val(rowData.email);
        $('#post_').val(rowData.post);
        $('#pwd_').val(rowData.pwd);
        $('#provinceCode_').val(rowData.provinceName);
        $('#cityCode_').val(rowData.cityName);
        $('#districtCode_').val(rowData.districtName);
        $('#address_').val(rowData.address);
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
        var id = $('#id').val();
        var url = '${ctx}/vipInfo/create';
        var data = $('#editForm').serializeObject();
        if(id != null && id != ''){
            url = '${ctx}/vipInfo/update';
            data.id = id;
        }
        if(data.code == null || data.code == ''){
            alert('提示','会员卡号不能为空',3000);
            return;
        }
        if(data.name == null || data.name == ''){
            alert('提示','会员名称不能为空',3000);
            return;
        }
        if(data.vipTypeCode == null || data.vipTypeCode == ''){
            alert('提示','会员类型不能为空',3000);
            return;
        }
        $.ajax({
            type: 'POST',
            url: url,
            contentType:"application/json",
            data: JSON.stringify(data),
            success: function(map){
                if(map.status){
                    $('#grid').datagrid('reload');
                    $('#myDialog').modal('hide');
                    $("#editForm :input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");//核心
                }
                alert("提示",map.msg,3000);
            }
        });
    }

    //新增
    function createOpen() {
        $('#id').val("");
        $('#code').removeAttr('readonly');
        $('#code').val("");
        $('#name').val("");
        $('#sex').val("");
        $('#shopCode').val("");
        $('#vipTypeCode').val("");
        $('#pwd').val("");
        $('#tel').val("");
        $('#phone').val("");
        $('#birthday').val("");
        $('#qq').val("");
        $('#email').val("");
        $('#post').val("");
        $('#identityCard').val("");
        $('#address').val("");
        $('#note').val("");
        //加载省市区
        $('#provinceCode').val("");
        $('#cityCode').val("");
        $('#districtCode').val("");
        $('#myDialog').modal('show');
    }
    //修改
    function modifyOpen() {
        var item = $('#grid').datagrid('getSelected');
        if(item != null){
            $('#code').attr('readonly','readonly');
            $('#id').val(item.id);
            $('#code').val(item.code);
            $('#name').val(item.name);
            $('#sex').val(item.sex);
            $('#shopCode').val(item.shopCode);
            $('#vipTypeCode').val(item.vipTypeCode);
            $('#pwd').val(item.pwd);
            $('#tel').val(item.tel);
            $('#phone').val(item.phone);
            $('#birthday').val(item.birthday);
            $('#qq').val(item.qq);
            $('#email').val(item.email);
            $('#post').val(item.post);
            $('#identityCard').val(item.identityCard);
            $('#address').val(item.address);
            $('#note').val(item.note);
            //加载省市区
            $('#provinceCode').val(item.provinceCode);
            getCity(item.provinceCode,'cityCode',false);
            getCity(item.cityCode,'districtCode',false);
            $('#cityCode').val(item.cityCode);
            $('#districtCode').val(item.districtCode);
        }else{
            alertLittle("请选择数据");
            return;
        }
        $('#myDialog').modal('show');
    }
    //delete
    function deleteOp() {
        var item = $('#grid').datagrid('getSelected');
        if(item != null){
            $.confirm({
                title: '确认?',
                content: '是否删除?',
                confirm: function () {
                    $.post('${ctx}/vipInfo/delete',{id:item.id,del:1},function(map){
                        if(map.status){
                            $('#grid').datagrid('reload');
                        }
                        alert("提示",map.msg,3000);
                    });
                },
                cancel: function () {
                }
            });
        }else{
            alertLittle("请选择数据");
            return;
        }

    }

    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>