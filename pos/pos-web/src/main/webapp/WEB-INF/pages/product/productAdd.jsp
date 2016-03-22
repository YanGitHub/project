<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加商品</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${ctx}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-confirm/css/jquery-confirm.css" />
    <!--图片-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-fileInput/css/fileinput.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-select/css/bootstrap-select.min.css" />
    <!--[if lt IE 9]>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.5-dist/js/respond.min.js"></script>
    <![endif]-->

    <style>
        body{
            padding: 10px 15px 0px 15px;
        }
        .breadcrumb{
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">商品管理</a></li>
    <li class="active">添加商品</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="save()">保存</button>
    <button class="btn btn-success" onclick="watchProduct()">查看</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">商品代码<font style="color: red">*</font></span>
            <input type="text" id="code" name="code" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">商品名称<font style="color: red">*</font></span>
            <input type="text" id="name" name="name" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">品类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
            <select id="categoryCode" name="categoryCode" class="form-control selectpicker">
                <c:if test="${categories != null}">
                    <c:forEach items="${categories}" var="c">
                        <option>${c.name}|${c.code}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>

    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">品牌&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
            <select id="brandCode" name="brandCode" class="form-control selectpicker">
                <c:if test="${brands != null}">
                    <c:forEach items="${brands}" var="b">
                        <option>${b.name}|${b.code}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
    </div>
</div>
<div class="row" style="padding-top: 10px">
    <div class="col-sm-12">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
            <input type="text" id="note" name="note" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>

</div>
<div class="row" style="padding-top: 10px">
    <div class="col-sm-12">
        <table id="grid"></table>
    </div>
</div>

<!--dialog-->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    添加商品规格
                </h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding-top: 10px">
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">规格代码<font style="color: red">*</font></span>
                            <input type="text" id="skuCode" name="skuCode" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">规格名称<font style="color: red">*</font></span>
                            <input type="text" id="skuName" name="skuName" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-top: 10px">
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">国标码&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
                            <input type="text" id="skuGbCode" name="skuGbCode" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">标准售价<font style="color: red">*</font></span>
                            <input type="text" id="skuUntPrice" name="skuUntPrice" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-top: 15px">
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">成本价&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
                            <input type="text" id="skuCostPrice" name="skuCostPrice" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">代理价&nbsp;&nbsp;&nbsp;&nbsp;<font style="color: red">*</font></span>
                            <input type="text" id="skuUsePrice" name="skuUsePrice" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-top: 15px">
                    <div class="col-sm-6">
                        <div class="input-group input-group-sm">
                            <span class="input-group-addon">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                            <input type="text" id="skuMemo" name="skuMemo" class="form-control" placeholder=""
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>
                    <div class="col-sm-6"></div>
                </div>
            </div>
            <!--警告框-->
            <div class="alert alert-warning alert-dismissible hidden" id="warning" role="alert">
                <button type="button" class="close" data-dismiss="alert" onclick="closeWarn()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Warning!</strong> <label id="alertMsg"></label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info" onclick="continueAddMx('Y')">继续添加</button>
                <button type="button" class="btn btn-primary" onclick="continueAddMx('N')">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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

<script src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
<script src="${ctx}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${ctx}/static/jquery-easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/static/jquery-easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-confirm/js/jquery-confirm.js"></script>
<script type="text/javascript" src="${ctx}/static/pos/js/common.js"></script>
<!--图片上传-->
<script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap-fileInput/js/fileinput_locale_zh.js"></script>

<script type="text/javascript" src="${ctx}/static/bootstrap-select/js/bootstrap-select.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#grid").datagrid({
            title: '商品规格添加',
            singleSelect: true,
            selectOnCheck: false,
            rownumbers: true,
            height:375,
            pagination: false,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'checked',checkbox:true},
                    { field: 'code', title: '规格代码', width: 120 },
                    { field: 'name', title: '规格名称', width: 120},
                    { field: 'gbCode', title: '国标码', width: 120},
                    { field: 'untPrice', title: '标准售价', width: 120, align: 'right' },
                    { field: 'costPrice', title: '成本价', width: 120, align: 'right' },
                    { field: 'usePrice', title: '代理价', width: 120, align: 'right' },
                    { field: 'memo', title: '备注', width: 120, align: 'left' }
                ]
            ],
            toolbar: [
                {
                    text: '添加', iconCls: 'icon-add', handler: function () {
                    openAdd();
                }
                },
                '-',
                {
                    text: '删除', iconCls: 'icon-remove', handler: function () {
                    deleteRow();
                }
                }
            ],
            onClickRow: function (rowIndex, rowData) {

            }
        });
    });
    //打开添加明细窗口
    function openAdd() {
        $('#myModal').modal('show');
    }
    //继续添加明细
    function continueAddMx(flog) {
        var skuCode = $('#skuCode').val().trim();
        var skuName = $('#skuName').val().trim();
        var skuGbCode = $('#skuGbCode').val().trim();
        var skuUntPrice = $('#skuUntPrice').val().trim();
        var skuCostPrice = $('#skuCostPrice').val().trim();
        var skuUsePrice = $('#skuUsePrice').val().trim();
        var skuMemo = $('#skuMemo').val().trim();
        if(skuCode == null || skuCode ==""){
            alert('提示','规格代码不能为空',5000);
            return;
        }
        if(skuName == null || skuName ==""){
            alert('提示','规格名称不能为空',5000);
            return;
        }
        if(skuGbCode == null || skuGbCode ==""){
            alert('提示','国标码不能为空',5000);
            return;
        }
        if(skuUntPrice == null || skuUntPrice ==""){
            alert('提示','标准售价不能为空',5000);
            return;
        }
        if(skuCostPrice == null || skuCostPrice ==""){
            alert('提示','成本价不能为空',5000);
            return;
        }
        if(skuUsePrice == null || skuUsePrice ==""){
            alert('提示','代理价不能为空',5000);
            return;
        }
        var items = $('#grid').datagrid('getRows');
        for(var i = 0;i < items.length;i++){
            if(items[i].code == skuCode){
                alert('提示','此规格代码已存在',5000);
                return;
            }
        }
        $('#grid').datagrid('appendRow',{
            code: skuCode,
            name: skuName,
            gbCode: skuGbCode,
            untPrice:skuUntPrice,
            costPrice:skuCostPrice,
            usePrice:skuUsePrice,
            memo:skuMemo
        });
        if(flog == "N"){
            $('#myModal').modal('hide');
        }
    }
    //删除商品明细
    function deleteRow(){
        var items = $('#grid').datagrid("getChecked");
        if(items.length == 0){
            alert('提示','请选择数据',5000);
            return;
        }
        $.confirm({
            title: '确认?',
            content: '是否删除?',
            confirm: function () {
                var items = $('#grid').datagrid("getChecked");
                if (items && items.length > 0) {
                    for(var i = 0;i < items.length;i++){
                        var k = $('#grid').datagrid('getRowIndex',items[i]);
                        $('#grid').datagrid('deleteRow',k);
                    }
                }else{
                    alert('提示','未选中数据',5000);
                }
            },
            cancel: function () {
            }
        });
    }
    //商品资料保存
    function save(){
        var code = $('#code').val().trim();
        var name = $('#name').val().trim();
        var categoryCode = $('#categoryCode').val().trim();
        var brandCode = $('#brandCode').val().trim();
        if(code == null || code ==""){
            alert('提示','商品代码不能为空',5000);
            return;
        }
        if(name == null || name ==""){
            alert('提示','商品名称不能为空',5000);
            return;
        }
        if(categoryCode == null || categoryCode ==""){
            alert('提示','商品品类不能为空',5000);
            return;
        }
        if(brandCode == null || brandCode ==""){
            alert('提示','商品品牌不能为空',5000);
            return;
        }
        var items = $('#grid').datagrid('getRows');
        if(items.length == 0){
            alert('提示','请添加商品规格',5000);
            return;
        }
        var prama = {code:code,name:name,categoryCode:categoryCode,brandCode:brandCode,productSkuList:items};
        $.ajax({
            url: '${ctx}/product/add/save',
            type: 'post',
            dataType: "json",
            data: JSON.stringify(prama),
            processData: false,
            contentType: 'application/json',
            success: function (data) {
                if(data.status){
                    alert("提示",data.msg,3000);
                }else{
                    alert("提示",data.msg,3000);
                }
            }
        });
    }

    function watchProduct(){
        window.location.href = "${ctx}/product";
    }

    $(window).resize(function () {
        $('#grid').datagrid('resize');
    });

</script>
</body>
</html>