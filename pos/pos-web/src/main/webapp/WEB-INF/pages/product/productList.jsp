<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>商品资料</title>
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
        body{
            padding: 10px 15px 0px 15px;
        }
        .input-group-addon{
            width: 90px;
        }
        .breadcrumb{
            margin-bottom: 10px;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body style="height: 580px">

<!--main-->
<ol class="breadcrumb">
    <li><a href="#">商品管理</a></li>
    <li class="active">商品资料</li>
</ol>
<!--操作-->
<div class="btn-group btn-group-sm">
    <button class="btn btn-primary" onclick="search()">查询</button>
    <button class="btn btn-warning" onclick="addProduct()">新增</button>
    <button class="btn btn-success" onclick="modify()">修改</button>
    <button class="btn btn-info" onclick="importExcel()">商品导入</button>
</div>

<div class="row" style="padding-top: 15px">
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">商品代码*</span>
            <input type="text" id="code" name="code" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">商品名称*</span>
            <input type="text" id="name" name="name" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">类别*</span>
            <input type="text" id="categoryId" name="categoryId" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
    <div class="col-sm-3">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">品牌*</span>
            <input type="text" id="brandId" name="brandId" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
</div>

<div class="row" style="padding-top: 10px">
    <div class="col-sm-12">
        <table id="productGrid"></table>
    </div>
</div>
<div class="row" style="padding-top: 10px">
    <div class="col-sm-12">
        <table id="productSkuGrid"></table>
    </div>
</div>
<!--dialog-->

<div class="modal fade" id="uploadImg">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    上传商品图片
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" id="formImg" enctype="multipart/form-data" action="${ctx}/product/image" method="post">
                    <input type="file" id="imageFileInput" name="image" class="file"/>
                    <input type="hidden" id="id" name="id"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<script type="text/javascript">
    //初始化fileinput控件（第一次初始化）
    function initFileInput(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions : ['jpg', 'png','gif'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });
    }
    //添加记录的窗体处理

    $(function () {
        $("#productGrid").datagrid({
            title: '商品明细',
            url:'${ctx}/product/list',
            singleSelect: true,
            rownumbers: true,
            height:240,
            pageSize:10,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'op', title: '上传', align:'center', width: 40,formatter:upload },
                    { field: 'attachHttp', title: '图片',align: 'center', width: 60,formatter:showImg },
                    { field: 'code', title: '商品代码', width: 120 },
                    { field: 'name', title: '商品名称', width: 120},
                    { field: 'shortName', title: '商品简称', width: 120},
                    { field: 'categoryId', title: '商品类别', width: 120, align: 'right' },
                    { field: 'brandId', title: '品牌', width: 120, align: 'right' },
                    { field: 'del', title: '是否可用', width: 120, align: 'right' },
                    { field: 'memo', title: '备注', width: 120, align: 'left' }
                ]
            ],
            onClickRow: function (rowIndex, rowData) {
                loadSku(rowData.code)
            }
        });

        $("#productSkuGrid").datagrid({
            title: '商品规格明细',
            singleSelect: true,
            rownumbers: true,
            height:240,
            pagination: true,
            columns: [
                [
                    { field: 'id', hidden: true },
                    { field: 'code', title: '规格代码', width: 120 },
                    { field: 'name', title: '规格名称', width: 120},
                    { field: 'gbCode', title: '国标码', width: 120},
                    { field: 'untPrice', title: '标准售价', width: 120, align: 'right' },
                    { field: 'costPrice', title: '成本价', width: 120, align: 'right' },
                    { field: 'usePrice', title: '代理价', width: 120, align: 'right' },
                    { field: 'memo', title: '备注', width: 120, align: 'left' }
                ]
            ],
            onClickRow: function (rowIndex, rowData) {

            }
        });
        //初始化fileinput控件（第一次初始化）
        initFileInput("imageFileInput", "${ctx}/product/image");
    });
    //datagrid 图片显示
    function showImg(v,r,i){
        if(v != null && v != ''){
            return '<img src="' + '..' + v + '" width="50px" height="50px"/>';
        }
    }
    //上传图片
    function upload(value,row,index){
        var act = "<a href=\"#\" onclick=\"editImg('" + row.id + "')\"><font style='color: orange'>上传</font></a>";
        return  act;
    }
    function editImg(id){
      //  $('#formImg').attr('action','${ctx}/product/image?id='+id);
        $('#id').val(id);
        $('#uploadImg').modal('show');
    }
    function loadSku(code){
        $('#productSkuGrid').datagrid({url:'${ctx}/product/getSkuList?productCode=' + code});
    }

    function addProduct(){
        window.location.href = "${ctx}/product/add";
    }


    function watchProduct(){

    }

    $(window).resize(function () {
        $('#productGrid').datagrid('resize');
        $('#productSkuGrid').datagrid('resize');
    });

</script>
</body>
</html>