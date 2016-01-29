<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>POS收银</title>
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

        .breadcrumb {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<ol class="breadcrumb">
    <li><a href="#">我的工作台</a></li>
    <li class="active">收银台</li>
</ol>
<div class="row" style="text-align: right">
    <div class="col-sm-9">
        <table id="grid"></table>
        <div class="row">
            <div class="col-sm-3">
                <div class="input-group" style="padding-top: 10px">
                    <span class="input-group-addon">会员折扣</span>
                    <input type="text" readonly="readonly" id="vipDiscount" class="form-control" placeholder="0.00"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-sm-3">
                <div class="input-group" style="padding-top: 10px">
                    <span class="input-group-addon">库存数量</span>
                    <input type="text" readonly="readonly" id="inventoryQty" class="form-control" placeholder="0.00"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-sm-3">
                <div class="input-group" style="padding-top: 10px">
                    <span class="input-group-addon">数量</span>
                    <input type="text" readonly="readonly" id="qty" class="form-control" placeholder="0.00"
                           aria-describedby="basic-addon1">
                </div>
            </div>
            <div class="col-sm-3">
                <div class="input-group" style="padding-top: 10px">
                    <span class="input-group-addon">总计</span>
                    <input type="text" id="amount" readonly="readonly" class="form-control" placeholder="0.00"
                           aria-describedby="basic-addon1">
                </div>
            </div>
        </div>
        <div class="btn-group" style="padding-top: 10px">
            <button class="btn btn-danger" onclick="escBtn()">ESC撤单</button>
            <button class="btn btn-primary" onclick="showHistory()">历史查询</button>
            <button class="btn btn-success" onclick="">库存查询</button>
            <button class="btn btn-warning" onclick="">重打印小票</button>
            <button class="btn btn-primary" onclick="">支付宝支付</button>
            <button class="btn btn-success" onclick="">微信支付</button>
            <button class="btn btn-warning" onclick="openCash()">现金支付</button>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group">
            <span class="input-group-addon">条码</span>
            <input type="text" id="barcode" class="form-control" placeholder="商品条码"
                   aria-describedby="basic-addon1">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon">会员</span>
            <input type="text" class="form-control" id="cvipcode" placeholder="会员卡号/名称/手机" aria-describedby="basic-addon1">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon">数量</span>
            <input type="text" class="form-control" id="cqty"
                   aria-label="Amount (to the nearest dollar)">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon">折扣</span>
            <input type="text" class="form-control" id="cdiscount"
                   aria-label="Amount (to the nearest dollar)">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon">金额</span>
            <input type="text" class="form-control" id="camount"
                   aria-label="Amount (to the nearest dollar)">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon" id="basic-name">品名</span>
            <input type="text" readonly="readonly" id="productName" class="form-control" placeholder="商品名称"
                   aria-describedby="basic-addon1">
        </div>
        <div class="input-group" style="padding-top: 10px">
            <span class="input-group-addon">小计</span>
            <input type="text" class="form-control" readonly="readonly" id="subtotal" placeholder="0.00"
                   aria-describedby="basic-addon1">
        </div>
        <div class="btn-group btn-group-justified" role="group" aria-label="..." style="padding-top: 10px">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">1</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">2</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">3</button>
            </div>
        </div>
        <div class="btn-group btn-group-justified" role="group" aria-label="..." style="padding-top: 10px">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">4</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">5</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">6</button>
            </div>
        </div>
        <div class="btn-group btn-group-justified" role="group" aria-label="..." style="padding-top: 10px">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">7</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">8</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">9</button>
            </div>
        </div>
        <div class="btn-group btn-group-justified" role="group" aria-label="..." style="padding-top: 10px">
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">.</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">0</button>
            </div>
            <div class="btn-group" role="group">
                <button type="button" class="btn btn-default">Cls</button>
            </div>
        </div>
    </div>
</div>
<!-- /.row -->

<!--收银对话框-->
<div class="modal fade" id="cashDialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    收银
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon">应收金额</span>
                            <input type="text" id="receivable" class="form-control"
                                   style="color: orange;font-weight: 700" readonly="readonly" placeholder="0.00"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
                <!--支付方式遍历-->
                <c:forEach items="${shopPaymentList}" var="s">
                    <div class="row" style="margin-top: 10px">
                        <div class="col-sm-12">
                            <div class="input-group">
                                <span class="input-group-addon">${s.name}</span>
                                <input type="text" id="${s.code}" class="form-control"
                                       style="font-weight: 700" placeholder="0.00" aria-describedby="basic-addon1">
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <div class="row" style="margin-top: 10px">
                    <div class="col-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon">找零金额</span>
                            <input type="text" id="change" readonly="readonly" class="form-control"
                                   style="font-weight: 700;color: red" placeholder="0.00"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="cash()" data-dismiss="modal">结算</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

<!--历史查询-->
<div class="modal fade" id="historyModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">历史查询</h4>
            </div>
            <div class="modal-body">
                <iframe id="historyFrame" width="100%" frameborder="no" scrolling="auto" src=""></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="payDiv" hidden="hidden">
    <c:forEach items="${shopPaymentList}" var="l">
        <input type="text" hidden="hidden" name="pay" value="${l.code}"/>
    </c:forEach>
</div>

<div id="printTable" hidden="hidden">
    <table id="pTable"></table>
</div>

<script type="text/javascript">
    var contextPath = '${ctx}';
    var print_receipts = '${print_receipts}';
</script>

</body>
</html>