<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>
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
            padding-top: 50px;
        }

        .carousel {
            height: 400px;
            background-color: #000;
            margin-bottom: 60px;
        }

        .carousel .item {
            height: 400px;
            background-color: #000;
        }

        .carousel img {
            width: 100%;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">欢迎使用POS收银系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="../../static/images/IMG_1231.JPG" alt="...">

            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="../../static/images/IMG_1232.JPG" alt="...">

            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="../../static/images/IMG_1233.JPG" alt="...">

            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="../../static/images/IMG_1234.JPG" alt="...">

            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="../../static/images/IMG_1235.JPG" alt="...">

            <div class="carousel-caption">
                ...
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">上一页</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">下一页</span>
    </a>
</div>

<div class="row" style="margin-left: 0;margin-right: 0">
    <div class="col-sm-3"></div>
    <div class="col-sm-3">
        <div class="input-group">
            <span class="input-group-addon" style="width: 90px">用户名</span>
            <input type="text" id="name" class="form-control" placeholder="Username" value="张沿" onkeypress="login()"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group">
            <span class="input-group-addon" style="width: 90px">密码</span>
            <input type="password" id="password" class="form-control" onkeypress="login()" value="123" placeholder="password"
                   aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="btn-group">
            <button class="btn btn-warning" onclick="loginBtn()">登录</button>
        </div>
    </div>
</div>

<%--<div id="footer" class="container">--%>
    <%--<nav class="navbar navbar-default navbar-fixed-bottom">--%>
        <%--<div class="navbar-inner navbar-content-center">--%>
            <%--<p class="text-muted credit" style="padding: 10px;text-align: center">--%>
                <%--©2016 YAN 使用POS前必读 意见反馈--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</nav>--%>
<%--</div>--%>

<script type="text/javascript">
    function login() {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e.keyCode == 13) {
            var name = $('#name').val().trim();
            var password = $('#password').val().trim();
            $.post('${ctx}/login', {name: name, password: password}, function (data) {
                if (data.status) {
                    window.location.href = "${ctx}/main";
                } else {
                    alertLittle(data.msg);
                }
            });
        }
    }

    function loginBtn(){
        var name = $('#name').val().trim();
        var password = $('#password').val().trim();
        $.post('${ctx}/login', {name: name, password: password}, function (data) {
            if (data.status) {
                window.location.href = "${ctx}/main";
            } else {
                alertLittle(data.msg);
            }
        });
    }
</script>

</body>
</html>