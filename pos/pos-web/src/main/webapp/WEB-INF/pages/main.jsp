<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>POS收银系统</title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css" />
    <!--图片上传-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-fileInput/css/fileinput.min.css" />

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
        body{
            padding: 50px 15px 10px 15px;
        }
        .input-group-addon{
            width: 90px;
        }
        .breadcrumb{
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div style="width: 100%">
    <div>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">营销存</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#" onclick="addTab('我的工作台','${ctx}/pos')">我的工作台 <span class="sr-only">(current)</span></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">基础资料 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="addTab('仓库信息','${ctx}/warehouse')">仓库信息</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">门店管理 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="addTab('门店资料','${ctx}/organization')">门店资料</a></li>
                                <li><a href="#">用户信息</a></li>
                                <li><a href="#">会员管理</a></li>
                                <li><a href="#" onclick="addTab('支付方式','${ctx}/shopPayment')">支付方式</a></li>
                                <li><a href="#" onclick="addTab('模板设置','${ctx}/printTemplate')">模板设置</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#" onclick="addTab('历史查询','${ctx}/pos/shopSalesDetail')">历史查询</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">商品管理 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="addTab('商品资料','${ctx}/product')">商品资料</a></li>
                                <li><a href="#" onclick="addTab('添加商品','${ctx}/product/add')">添加商品</a></li>
                                <li><a href="#" onclick="addTab('商品条码','${ctx}/product/barcode')">商品条码</a></li>
                                <li><a href="#" onclick="addTab('商品品牌','${ctx}/brand')">商品品牌</a></li>
                                <li><a href="#" onclick="addTab('商品品类','${ctx}/category')">商品品类</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="addTab('用户信息','${ctx}/userInfo')">用户信息</a></li>
                                <li><a href="#" onclick="addTab('系统参数','${ctx}/sysParameters')">系统参数</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">开发者选项 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="addTab('mybaties文件创建','${ctx}/createMyBatiseFile')">mybaties文件创建</a></li>
                            </ul>
                        </li>
                        <li><a href="#" data-toggle="modal" data-target="#version">关于</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${organizationInfo != null}">
                            <li><a href="#">${organizationInfo.name}</a></li>
                        </c:if>

                        <c:if test="${userInfo != null}">
                            <li><a href="#">${userInfo.name}</a></li>
                            <%--<li><a href="#">${userInfo.mobile}</a></li>--%>
                        </c:if>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">修改密码</a></li>
                                <li><a href="/">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
    <div>
        <iframe id="myiframe" width="100%" frameborder="no" scrolling="auto" src=""></iframe>
    </div>
</div>

<!--页脚-->
<div id="footer" class="container">
    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="navbar-inner navbar-content-center">
            <p class="text-muted credit" style="padding: 10px;text-align: center">
                ©2016 YAN 使用POS前必读 意见反馈
            </p>
        </div>
    </nav>
</div>

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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    $(function(){
        var height = window.innerHeight;
        console.log(height);
        $('#myiframe').attr('height',height - 67);

    })
    function addTab(title, url) {
        $('#myiframe').attr('src',url);
    }

</script>
</body>
</html>