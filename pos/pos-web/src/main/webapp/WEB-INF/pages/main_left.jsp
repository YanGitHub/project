<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>

<head>

    <meta charset="UTF-8">

    <title>POS收银系统</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Iconos -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/pos/css/style.css" media="screen" type="text/css"/>


    <script src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>

<body>

<!-- Contenedor -->
<div class="row" style="margin-right: 0">
    <div class="col-sm-2" style="padding-right: 0">
        <ul id="accordion" class="accordion">
            <li>
                <div class="link"><i class="fa fa-paint-brush"></i>首页<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#" onclick="addTab('我的工作台','${ctx}/pos')" >我的工作台</a></li>
                    <li><a href="#" onclick="addTab('门店资料','${ctx}/organization')">门店资料</a></li>
                    <li><a href="#" onclick="addTab('仓库信息','${ctx}/warehouse')">仓库信息</a></li>
                    <li><a href="#">Maquetacion web</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-code"></i>Desarrollo front-end<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Javascript</a></li>
                    <li><a href="#">jQuery</a></li>
                    <li><a href="#">Frameworks javascript</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-mobile"></i>Diseño responsive<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Tablets</a></li>
                    <li><a href="#">Dispositivos mobiles</a></li>
                    <li><a href="#">Medios de escritorio</a></li>
                    <li><a href="#">Otros dispositivos</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-globe"></i>Posicionamiento web<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Google</a></li>
                    <li><a href="#">Bing</a></li>
                    <li><a href="#">Yahoo</a></li>
                    <li><a href="#">Otros buscadores</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-code"></i>Desarrollo front-end<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Javascript</a></li>
                    <li><a href="#">jQuery</a></li>
                    <li><a href="#">Frameworks javascript</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-code"></i>Desarrollo front-end<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Javascript</a></li>
                    <li><a href="#">jQuery</a></li>
                    <li><a href="#">Frameworks javascript</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-code"></i>Desarrollo front-end<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Javascript</a></li>
                    <li><a href="#">jQuery</a></li>
                    <li><a href="#">Frameworks javascript</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="col-sm-10">
        <div>
            <iframe id="myiframe" width="100%" height="620px" frameborder="no" scrolling="auto" src=""></iframe>
        </div>
    </div>
</div>

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

<script src="${ctx}/static/pos/js/index.js"></script>

<script type="text/javascript">
    function addTab(title, url) {
        $('#myiframe').attr('src',url);
    }
</script>
</body>

</html>