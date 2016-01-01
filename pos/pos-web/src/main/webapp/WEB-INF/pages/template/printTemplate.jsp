<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>历史查询</title>
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
    <script type="text/javascript" src="${ctx}/static/pos/pos/pos.js"></script>
    <!--图片上传js-->
    <script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput_locale_zh.js"></script>

    <!--lodop 打印控件-->
    <script type="text/javascript" src="${ctx}/static/pos/js/LodopFuncs.js"></script>
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
    <li><a href="#">门店设置</a></li>
    <li class="active">模板设置</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="save()">保存</button>
    <button class="btn btn-info" onclick="importTemp()">导入模板</button>
    <button class="btn btn-warning" onclick="setPrinter()">选择打印机</button>
</div>

<div class="row" style="margin-top: 10px">
    <input id="id" hidden="hidden" type="text"/>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">模板名称</span>
            <input type="text" id="name" name="name" class="form-control" placeholder=""
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">模板类型</span>
            <select id="type" name="type" style="width: 168px" class="form-control selectpicker">
                <option value="1" selected="selected">小票模板</option>
                <option value="2">标签模板</option>
            </select>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">打印机名称</span>
            <input type="text" id="printer" name="printer" class="form-control" placeholder="" readonly="readonly"
                   aria-describedby="basic-addon1">
        </div>
    </div>
</div>
<div class="row" style="margin-top: 10px">
    <div class="col-sm-12">
        <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=1000 height=390>
            <param name="Caption" value="模板设计">
            <param name="Border" value="1">
            <param name="Color" value="#C0C0C0">
            <embed id="LODOP_EM" TYPE="application/x-print-lodop" width=1000 height=390 PLUGINSPAGE="install_lodop.exe">
        </object>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $.post('${ctx}/printTemplate/getData',{type:1},function(msg){
            displayDesign(msg.printTemplate.data);
        });

    });
    /**
     * 导入模板
     */
    function importTemp(){
        var LODOP = getLodop($("#LODOP_OB")[0], $("#LODOP_EM")[0]);
        var strFilename = LODOP.GET_DIALOG_VALUE("LocalFileFullName", "导入的模版.txt")
        $("#template_Content").val(LODOP.GET_FILE_TEXT(strFilename))
        LODOP.PRINT_INIT("模板设计");
        LODOP.SET_PRINT_PAGESIZE(1, parseFloat($("#width").val()) * 100, parseFloat($("#height").val()) * 100, "A5");
        eval($("#template_Content").val());
        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE", 1);
        LODOP.SET_SHOW_MODE("SETUP_ENABLESS", "11111111000000");//隐藏关闭(叉)按钮
        LODOP.PRINT_DESIGN();
    }

    /**
     * 选择打印机
     */
    function setPrinter() {
        var LODOP = getLodop($("#LODOP_OB")[0], $("#LODOP_EM")[0]);
        var name = LODOP.GET_PRINTER_NAME(LODOP.SELECT_PRINTER());
        $("#printer").val(name);
    }

    function displayDesign(data) {
        var LODOP = getLodop($("#LODOP_OB")[0], $("#LODOP_EM")[0]);
        LODOP.SET_PRINT_PAGESIZE(1, parseFloat($("#width").val()) * 100, parseFloat($("#height").val()) * 100, "A5");
        eval(data);
        LODOP.SET_SHOW_MODE("DESIGN_IN_BROWSE", 1);
        LODOP.SET_SHOW_MODE("SETUP_ENABLESS", "11111111000000");//隐藏关闭(叉)按钮
        LODOP.PRINT_DESIGN();
    }
</script>
<script type="text/javascript">
    var contextPath = '${ctx}';
</script>
</body>
</html>