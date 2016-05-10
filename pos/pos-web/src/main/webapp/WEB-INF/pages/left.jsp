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
<div class="row" style="margin-right: 0;height: 640px">
    <div class="col-sm-2" style="padding-right: 0">
            <ul id="accordion" class="accordion">
                <c:forEach items="${menu}" var="m">
                    <c:if test="${m.pid == 0}">
                        <li>
                            <div class="link"><i class="fa fa-paint-brush"></i>${m.name}<i class="fa fa-chevron-down"></i></div>
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

<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

<script src="${ctx}/static/pos/js/index.js"></script>

<script type="text/javascript">
    function addTab(title, url) {
        $('#myiframe').attr('src',url);
    }
</script>
</body>

</html>