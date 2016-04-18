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
        body {
            padding: 10px 15px 0px 15px;
        }

    </style>
</head>

<body>
<!--main-->
<ol class="breadcrumb">
    <li><a href="#">开发者选项</a></li>
    <li class="active">生成二维码</li>
</ol>

<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="create()">创建</button>
</div>
<form id="frmEdit">
    <div class="row" style="padding-top: 15px">
        <div class="col-sm-6">
            <div class="input-group input-group-sm">
                <span class="input-group-addon">二维内容</span>
                <textarea id="content" name="content" class="form-control" rows="3" style="height: 100px"></textarea>

            </div>
        </div>
    </div>
</form>
<!--dialog-->
<div id="imgDiv" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">二维码</h4>
            </div>
            <div class="modal-body">
                <img src="" id="img" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${ctx}/static/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/static/jquery-easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-confirm/js/jquery-confirm.js"></script>
<script type="text/javascript" src="${ctx}/static/pos/js/common.js"></script>
<script type="text/javascript">
    function create() {
        var content = $("#content").val();
        $.post('${ctx}/generatedQRCode/create',{content: content},function(data){
            if(data.status){
                $("#img").attr('src',data.path);
                $('#imgDiv').modal('show');
            }
        });
    }

</script>
</body>
</html>