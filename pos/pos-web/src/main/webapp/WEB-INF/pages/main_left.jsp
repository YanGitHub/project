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
    <style>
        .scrollbar
        {
            margin-left: 10px;
            float: left;
            height: 100%;
            width: 13.47%;
            background: #F5F5F5;
            overflow-y: scroll;
        }

        /*
            *  STYLE 4
        */
        #style-4::-webkit-scrollbar-track
        {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
            background-color: #F5F5F5;
        }

        #style-4::-webkit-scrollbar
        {
            width: 10px;
            background-color: #F5F5F5;
        }

        #style-4::-webkit-scrollbar-thumb
        {
            background-color: #c4c8cb;
            border: 1px solid orange;
        }

    </style>
</head>

<body>

<!-- Contenedor -->
<div style="height: 100%">
    <div class="scrollbar" id="style-4">
        <div class="force-overflow">
            <ul id="accordion" class="accordion">
                <c:forEach items="${menu}" var="m">
                    <c:if test="${m.pid == 0}">
                        <li>
                            <div class="link"><i class="fa ${m.icon}"></i>${m.name}<i class="fa fa-chevron-down"></i></div>
                            <ul class="submenu">
                                <c:forEach items="${menu}" var="d">
                                    <c:if test="${d.pid == m.id}">
                                        <li><a href="#" onclick="addTab('${d.name}','${ctx}${d.url}')">${d.name}</a></li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>

<%--    <div id="left" style="float: left;width: 190px;height: 640px;background-color: floralwhite">

    </div>--%>


    <div id="main" style="float: left;width: 85%">
        <iframe id="myiframe" width="100%" height="100%" frameborder="no" scrolling="auto" src="${ctx}/background"></iframe>
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