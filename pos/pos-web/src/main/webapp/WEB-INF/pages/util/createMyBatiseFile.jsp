<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>mybaties文件创建</title>
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
    </style>
</head>

<body>
<!--main-->
<ol class="breadcrumb">
    <li><a href="#">开发者选项</a></li>
    <li class="active">mybaties文件创建</li>
</ol>

<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="create()">创建</button>
    <button class="btn btn-warning" onclick="cancel()">取消</button>
</div>
<form id="frmEdit">
    <div class="row" style="padding-top: 15px">
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">保存路径*</span>
                <input type="text" id="savePath" name="savePath" value="f:code" class="form-control" placeholder=""
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">包名*</span>
                <input type="text" id="packageName" name="packageName" value="kj.pos.entity" class="form-control"
                       placeholder="" aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">表名*</span>
                <input type="text" id="tableName" name="tableName" class="form-control" placeholder=""
                       aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="col-sm-3">
        </div>
    </div>
</form>
<!--dialog-->


<!--关于-->
<div class="modal fade" id="version">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    关于
                </h4>
            </div>
            <div class="modal-body">
                <p>版本version 1.0</p>
            </div>
            <div class="modal-footer">
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
    function create() {
        var savePath = $("#savePath").val();
        var packageName = $("#packageName").val();
        var tableName = $("#tableName").val();

        $.post('${ctx}/createMyBatiseFile/createFile',{savePath: savePath, packageName: packageName, tableName: tableName},
            function(result){
                var resultArray = result.split(",");
                var status = resultArray[0];
                var message = resultArray[1];
                alertLittle(message);
            }
        );

    }
    /* 取消修改（编辑） */
    function cancel() {
        document.getElementById("frmEdit").reset();
    }
</script>
</body>
</html>