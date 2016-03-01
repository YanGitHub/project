<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>会员管理</title>
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
                        <input type="text" id="tel" name="tel" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">手机</span>
                        <input type="text" id="phone" name="phone" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">生日</span>
                        <input type="text" id="birthday" name="birthday" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">身份证</span>
                        <input type="text" id="identityCard" name="identityCard" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px">
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">QQ</span>
                        <input type="text" id="qq" name="qq" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">Email</span>
                        <input type="text" id="email" name="email" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">邮编</span>
                        <input type="text" id="post" name="post" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">会员密码</span>
                        <input type="text" id="pwd" name="pwd" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>

            <div class="row" style="margin-top: 10px">
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">省</span>
                        <input type="text" id="provinceCode" name="provinceCode" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">市</span>
                        <input type="text" id="cityCode" name="cityCode" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">区</span>
                        <input type="text" id="districtCode" name="districtCode" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">地址</span>
                        <input type="text" id="address" name="address" class="form-control" placeholder="" style="width: 168px" readonly="readonly"
                               aria-describedby="basic-addon1">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Nav tabs -->
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
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '会员卡号', fitColumns: true },
                    { field: 'name', title: '会员名称', fitColumns: true},
                    { field: 'sex', title: '性别', fitColumns: true},
                    { field: 'vipTypeCode', title: '会员类型', fitColumns: true},
                    { field: 'shopCode', title: '店铺代码', fitColumns: true},
                    { field: 'shopName', title: '店铺名称', fitColumns: true},
                    { field: 'createDate', title: '创建日期', fitColumns: true},
                    { field: 'issuingPerson', title: '发放人', fitColumns: true},
                    { field: 'note', title: '备注', fitColumns: true}
                ]
            ]
        });
    });

    //快递查询
    function searchExpress(){
        window.location.href = "${ctx}/expressCompany/search";
    }

    function save() {

    }

    //新增
    function createOpen() {

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