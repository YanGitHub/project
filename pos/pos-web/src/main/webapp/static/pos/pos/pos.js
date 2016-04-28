var cvipDiscount = 10;
$(function () {
    $('#grid').datagrid({
        nowrap: false,
        striped: true,
        title: '商品明细',
        height: 386,
        singleSelect: true,
        columns: [
            [
                {field: 'id', title: '', hidden: true},
                {field: 'skuId', title: '', hidden: true},
                {field: 'employeeCode', title: '', hidden: true},
                {field: 'productCode',hidden:true},
                {field: 'code',hidden:true},//skuCode
                {field: 'barcode', title: '条码', width: 150, fitColumns: true},
                {field: 'isGift', title: '赠品', fitColumns: true,formatter:changeGift},
                {field: 'employeeName', title: '导购员', fitColumns: true},
                {field: 'productName', title: '品名', width: 150, fitColumns: true},
                {field: 'skuName', title: '规格名称', width: 100, fitColumns: true},
                {field: 'price', title: '单价', width: 70, align: 'right', fitColumns: true, formatter: twoDecimal},
                {field: 'discount', title: '折扣', width: 70, align: 'right', fitColumns: true, formatter: twoDecimal},
                {field: 'qty', title: '数量', width: 70, align: 'right', fitColumns: true, formatter: twoDecimal},
                {field: 'relPrice', title: '实售价', width: 70, align: 'right', fitColumns: true, formatter: twoDecimal},
                {field: 'amount', title: '金额', width: 70, align: 'right', fitColumns: true, formatter: twoDecimal},
                {field: 'op', title: '操作', width: 100, align: 'center', fitColumns: true, formatter: opration}
            ]
        ]
    });
    //扫描条码回车事件
    $('#barcode').keyup(function(e){
        var curKey = e.which;
        if(curKey == 13){
            scanBarcode();
        }
    });

    //支付方式
    var shopPay = $("#payDiv input[name='pay']").serializeArray();
    for(var i = 0;i < shopPay.length;i++){
        $('#' + shopPay[i].value).keyup(function(e){
            var curKey = e.which;
            calculateChange(curKey);
        });
    }

    //改数量
    $('#cqty').keyup(function(e){
        var curKey = e.which;
        if(curKey == 13){
            changeQty();
        }
    });

    //改折扣
    $('#cdiscount').keyup(function(e){
        var curKey = e.which;
        if(curKey == 13){
            changeDiscount();
        }
    });

    //改金额
    $('#camount').keyup(function(e){
        var curKey = e.which;
        if(curKey == 13){
            changeAmount();
        }
    });

    //扫描会员卡号
    $('#cvipcode').keyup(function(e){
        var curKey = e.which;
        if(curKey == 13){
            scanVipCode();
        }
    });
});
//赠品 数字转字符串
function changeGift(v,r,i){
    if(v == 0){
        return "✘";
    }else if(v == 1){
        return "✔";
    }
}
//显示导购员列表
function showEmployeeDialog(){
    var item = $('#grid').datagrid('getRows');
    if(item.length == 0){
        alertLittle("请先扫描商品条码");
        return;
    }
    $('#employeeDialog').modal('show');
    setTimeout("$('#employeeGrid').datagrid({url: contextPath + '/info/employeeInfo/getList?status = ' + 0})", 500);
}
//确定导购员
function confimEmployee(value){
    //验证 是否已经扫描商品
    var data = $('#grid').datagrid('getRows');
    if(data.length == 0){
        alertLittle("请先扫描商品条码");
        return;
    }
    var item = $('#employeeGrid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择导购员");
        return;
    }else{
        if(value){
            for(var i = 0;i < data.length;i++){
                $('#grid').datagrid('updateRow',{
                    index: i,
                    row: {
                        employeeCode:item.code,
                        employeeName:item.name
                    }
                });
            }
        }else{
            var single = $('#grid').datagrid('getSelected');
            if(single != null){
                var index = $('#grid').datagrid('getRowIndex',single);
                $('#grid').datagrid('updateRow',{
                    index: index,
                    row: {
                        employeeCode:item.code,
                        employeeName:item.name
                    }
                });
            }else{
                alertLittle("请选择已经扫描的商品");
                return;
            }
        }
    }
    $('#employeeDialog').modal('hide');
}

//设置 是赠品
function setIsGift(){
    var item = $('#grid').datagrid('getSelected');
    if(item == null){
        alertLittle("请先扫描商品条码");
        return;
    }else{
        var index = $('#grid').datagrid('getRowIndex',item);
        $('#grid').datagrid('updateRow',{
            index: index,
            row: {
                isGift:1,
                discount:0,
                relPrice:0,
                amount:0
            }
        });
        total();
    }
}
//显示重打印窗口
function showRePrintDialog(){
    $('#rePrintDialog').modal('show');
    setTimeout("$('#rePrintGrid').datagrid({url: contextPath + 'pos/shopSalesDetail/getList'})", 500);
}
//确定重打印数据
function confimRePrint(){
    var item = $('#rePrintGrid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择要重新打印的数据");
        return;
    }else{
        $('#rePrintDialog').modal('hide');
        print(item.flowNo,"重打印",'pos/print/getList');
    }
}
function opration(v, r, i) {
    var btn = "<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"plus('" + i + "','" + 1 + "')\"><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span></button>";
    btn += "&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"minus('" + i + "','" + 1 + "')\"><span class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></span></button>";
    btn += "&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"trash('" + r.id + "')\"><span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span></button>";
    return btn;
}

//数量加
function plus(i, n) {
    var num = parseFloat(n);
    var item = $('#grid').datagrid('getRows');
    var qty = parseFloat(item[i].qty);
    var price = parseFloat(item[i].price);
    var discount = parseFloat(item[i].discount);
    $.post(contextPath + '/pos/scanBarcode', {barcode: item[i].barcode,qty:qty + 1}, function (data) {
        if (data.status) {
            $('#grid').datagrid('updateRow', {
                index: i,
                row: {
                    qty: qty + num,
                    amount: ((qty + num) * price * discount / 10)
                }
            });
            total();
        } else {
            alertLittle(data.msg);
        }
    });
}

//数量减
function minus(i, n) {
    var num = parseFloat(n);
    var item = $('#grid').datagrid('getRows');
    var qty = parseFloat(item[i].qty);
    var price = parseFloat(item[i].price);
    var discount = parseFloat(item[i].discount);
    if (qty - n < 1) {
        alertLittle("商品数量已经是1了，不可以再减");
        return;
    }
    $('#grid').datagrid('updateRow', {
        index: i,
        row: {
            qty: qty - num,
            amount: ((qty - num) * price * discount / 10)
        }
    });
    total();
}
//删除
function trash(id) {
    var item = $('#grid').datagrid('getRows');
    for (var i = 0; i < item.length; i++) {
        if (item[i].id == id) {
            var k = $('#grid').datagrid('getRowIndex', item[i]);
            $('#grid').datagrid('deleteRow', k);
            total();
            return;
        }
    }
}
//扫描条码
function scanBarcode() {
    var item = $('#grid').datagrid('getRows');
    var barcode = $('#barcode').val().trim();
    var qty = 0;
    if(item.length > 0){
        for(var i = 0;i < item.length;i++){
            if(item[i].barcode == barcode){
                qty += item[i].qty;
            }
        }
        qty += 1;
    }else{
        qty = 1;
    }
    $.post(contextPath + '/pos/scanBarcode', {barcode: barcode,qty:qty}, function (data) {
        if (data.status) {
            addSkuJudge(data.productSku, barcode);
            scanSuccess(data.productSku);
        } else {
            alertLittle(data.msg);
        }
    });
}

//扫描条码完成时清空 扫描的条码 显示商品名称 计算数量 金额
function scanSuccess(data){
    $('#barcode').val("");
    $('#productName').val(data.productName);
    $('#subtotal').val(twoDecimal(data.untPrice));
    total();
}

//判断 添加的商品是否已经扫描过了，如果已经扫描过则累加数量
function addSkuJudge(data, barcode) {
    var item = $('#grid').datagrid('getRows');
    if (item.length == 0) {
        addSku(data, barcode);
    } else {
        var i = 0;
        for (i = 0; i < item.length; i++) {
            if (data.id == item[i].id) {
                var qty = parseFloat(item[i].qty);
                var price = parseFloat(item[i].price);
                var discount = parseFloat(item[i].discount);
                var amount = parseFloat(item[i].amount);
                $('#grid').datagrid('updateRow', {
                    index: i,
                    row: {
                        qty: qty + 1,
                        amount: ((qty + 1) * price * discount / 10)
                    }
                });
                return;
            }
        }
        if (i == item.length) {
            addSku(data, barcode);
        }
    }

}
//添加商品
function addSku(data, barcode) {
    var relPrice = data.untPrice * cvipDiscount / 10;
    $('#grid').datagrid('appendRow', {
        id: data.id,
        isGift:0,
        skuId: data.id,
        productCode: data.productCode,
        code: data.code,
        barcode: barcode,
        productName: data.productName,
        skuName: data.name,
        price: data.untPrice,
        qty: 1,
        discount: cvipDiscount,
        relPrice:relPrice,
        amount: relPrice
    });
}
//计算总计，数量
function total() {
    var item = $('#grid').datagrid('getRows');
    var qty = 0;
    var amount = 0;
    var untAmount = 0;
    for (var i = 0; i < item.length; i++) {
        qty += parseFloat(item[i].qty);
        amount += parseFloat(item[i].amount);
        untAmount += ( parseFloat(item[i].qty) * parseFloat(item[i].price) );
    }
    $('#qty').val(twoDecimal(qty));
    $('#amount').val(twoDecimal(amount));
    $('#favourablePrice').val( twoDecimal(untAmount - amount));
}

//改数量
function changeQty(){
    var qty = parseFloat($('#cqty').val().trim());
    if(qty <= 0){
        alertLittle("请输入大于0的数");
        return;
    }
    var item = $('#grid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择数据");
        return;
    }else{
        $.post(contextPath + '/pos/scanBarcode', {barcode: item.barcode,qty:qty}, function (data) {
            if (data.status) {
                var price = parseFloat(item.price);
                var discount =  parseFloat(item.discount);
                var index = $('#grid').datagrid('getRowIndex',item);
                $('#grid').datagrid('updateRow',{
                    index: index,
                    row: {
                        qty: qty,
                        amount: parseFloat(qty * discount * price / 10)
                    }
                });
                $('#cqty').val("");
                total();
            } else {
                alertLittle(data.msg);
            }
        });
    }
}

//改折扣
function changeDiscount(){
    var discount = parseFloat($('#cdiscount').val().trim());
    if(discount < 0){
        alertLittle("请输入大于0的数");
        return;
    }
    var item = $('#grid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择数据");
        return;
    }else{
        var price = parseFloat(item.price);
        var qty = parseFloat(item.qty);
        var index = $('#grid').datagrid('getRowIndex',item);
        $('#grid').datagrid('updateRow',{
            index: index,
            row: {
                discount: discount,
                relPrice: discount * price / 10,
                amount: parseFloat(qty * discount * price / 10)
            }
        });
    }
    $('#cdiscount').val("");
    total();
}
//会员信息
function scanVipCode(){
    var vipCode =$('#cvipcode').val().trim();
    $.post(contextPath + 'vipInfo/getList',{code:vipCode},function(data){
        var list = data.rows;
        if(list.length > 0){
            $('#vipInfo').val(list[0].name + ' / ' + list[0].vipTypeName + ' / ' + list[0].vipDiscount);
            cvipDiscount = list[0].vipDiscount;
            //根据会员折扣重新计算价格
            resolveVipDiscount();
            $('#cvipcode').val("");
        }else{
            alertLittle("没有此会员");
            return;
        }
    });
}
//扫描会员后，重新计算已描的商品折扣
function resolveVipDiscount(){
    var item = $('#grid').datagrid('getRows');
    for (var i = 0; i < item.length; i++) {
        var qty = parseFloat(item[i].qty);
        var price = parseFloat(item[i].price);
        var discount = cvipDiscount;
        var relPrice = price * discount / 10;
        $('#grid').datagrid('updateRow', {
            index: i,
            row: {
                discount:discount,
                relPrice: relPrice,
                amount: qty * relPrice
            }
        });
    }
    total();
}
//改金额
function changeAmount(){
    var amount = parseFloat($('#camount').val().trim());
    if(amount < 0){
        alertLittle("请输入大于0的数");
        return;
    }
    var item = $('#grid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择数据");
        return;
    }else{
        var price = parseFloat(item.price);
        var qty = parseFloat(item.qty);
        var index = $('#grid').datagrid('getRowIndex',item);
        $('#grid').datagrid('updateRow',{
            index: index,
            row: {
                discount: (amount / qty /price * 10),
                relPrice: amount / qty,
                amount: amount
            }
        });
    }
    $('#camount').val("");
    total();
}

//收银窗口打开
function openCash(){
    var item = $('#grid').datagrid('getRows');
    if(item.length == 0){
        alertLittle("请先扫描商品条码");
        return;
    }
    $('#receivable').val($('#amount').val());
    $('#change').val(-$('#amount').val());
    $('#cashDialog').modal('show');
}

//计算找零
function calculateChange(curKey){
    if (curKey == 13) {
        //收银
        cash();
    }else{
        var receivable = parseFloat($('#receivable').val().trim());
        var sum = 0.00;
        var list = $("#payDiv input[name='pay']").serializeArray();
        for(var i = 0;i < list.length;i++){
            var num = $('#' + list[i].value).val()||0;
            sum += parseFloat(num);
        }
        $('#change').val((sum - receivable).toFixed(2));
    }
}

//收银
function cash(){
    //找零 判断支付金额是否大于应收金额
    var change = parseFloat($('#change').val());
    if(change < 0){
        alertLittle("支付金额不能小于应收金额");
        return;
    }
    var item = $('#grid').datagrid('getRows');
    //支付方式
    var list = $("#payDiv input[name='pay']").serializeArray();
    //收银时间
    var saleDate = getNowFormatDate();
    //生成小票号
    var flowNo = "SY" + saleDate.replace(/\:/g,"").replace(/\-/g,"").replace(/\s/g,"");
    //组织数据包
    var data  = new Array();
    for(var i = 0;i < item.length;i++){
        var ob = new Object();
        ob.flowNo = flowNo;
        ob.saleDate = saleDate;
        ob.skuId = item[i].skuId;
        ob.employeeCode = item[i].employeeCode;
        ob.barcode = item[i].barcode;
        ob.isGift = item[i].isGift;
        ob.productCode = item[i].productCode;
        ob.productName = item[i].productName;
        ob.skuCode = item[i].code;
        ob.skuName = item[i].skuName;
        ob.price = item[i].price;
        ob.qty = item[i].qty;
        ob.discount = item[i].discount;
        ob.relPrice = item[i].relPrice;
        ob.amount = item[i].amount;
        ob.change = change;
        //会员
        //------------------------
        var d = new Array();
        for(var k = 0;k < list.length;k++){
            var op = new Object();
            op.code = list[k].value;
            op.payAmount = $('#' + list[k].value).val()||0;
            d[k] = op;
        }
        ob.paymentList = d;
        data[i] = ob;
    }
    //关闭收银窗口
    $('#cashDialog').modal('hide');
    //提交收银信息
    $.ajax({
        type: 'POST',
        url: contextPath + 'pos/save',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(map){
            //打印小票
            print(data[0].flowNo,"收银",'pos/print/getList');
        },
        error: function(map){

        }
    });
}

//历史查询
function showHistory(){
    window.location.href = contextPath + 'pos/shopSalesDetail';
}

//库存查询
function showInventory(){
    window.location.href = contextPath + 'inventory';
}

//打印小票
function print(flowNo,type,url){
    if(print_receipts == "1"){
        $.post(contextPath + url,{flowNo:flowNo},function(data){
            autoPrintData(data,type);
            clean();
        });
    }else{
        clean();
    }

}
//根据系统参数判断 是否打印小票
function autoPrintData(data,type){
    // type = 1 代表小票模板 2 代表标签模板
    $.post(contextPath + 'printTemplate/getData', {type: 1}, function (msg) {
        printF(msg.printTemplate.data,data,type);
    });
}
//组织小票打印内容 并打印
function printF(style,data,type){
    var listp = data.listP;
    var listd = data.listD;
    var listL = data.listL;
    style = style.replace(/LODOP.PRINT_INITA([\s\S]*?);/, '')
        .replace(/\[类型\]/g, type)
        .replace(/\[门店名称\]/g, "POS*MART")
        .replace(/\[交易号\]/g, listd[0].flowNo)
        .replace(/\[交易日期\]/g, listd[0].saleDate)
        .replace(/\[收银员\]/g, listd[0].createUser)
        .replace(/\[总计\]/g, getRealReceive(listL))
        .replace(/\[件数\]/g, getRealQty(listL))
        .replace(/\[实收金额\]/g, getRealPay(listp))
        .replace(/\[找零金额\]/g, listd[0].fundAmount.toFixed(2))
        .replace(/\[电话\]/g, "021-888888")
        .replace(/\[地址\]/g, "中国上海");

    //商品明细
    var trReg = /<tr id=order-item(( sort=location)|( sort=item))?>[\s\S]*?<\/tr>/gi;
    var orderItems = '';
    var trMatch;
    if (trMatch = trReg.exec(style)) {
        var trResult = trMatch[0];
        var items = listL;
        for (var j = 0; j < items.length; j++) {
            var itemData = items[j];
            var trResult = trMatch[0];
            //截取商品名称
            trResult = trResult.replace(/\[商品名称\]/g, itemData.productName);
            trResult = trResult.replace(/\[品名\]/g, itemData.skuName);//规格名称
            trResult = trResult.replace(/\[数量\]/g, itemData.qty.toFixed(2));
            trResult = trResult.replace(/\[单价\]/g, itemData.realPrice.toFixed(2));//实际单价
            trResult = trResult.replace(/\[金额\]/g, itemData.realAmount.toFixed(2));

            trResult = trResult.replace(/\[规格代码\]/g, itemData.skuCode);
            trResult = trResult.replace(/\[折扣\]/g, itemData.saleDiscount);//销售折扣
            trResult = trResult.replace(/\[标准单价\]/g, itemData.untPrice);
            orderItems += trResult;
        }
        style = style.replace(trReg, orderItems);
    }
    //支付明细
    var trReg2 = /<tr id=order-pay(( sort=location)|( sort=item2))?>[\s\S]*?<\/tr>/gi;
    var orderItems2 = '';
    var trMatch2 = null;
    if (trMatch2 = trReg2.exec(style)) {
        var trResult2 = trMatch2[0];
        for (var j = 0; j < listp.length; j++) {
            trResult2 = trResult2.replace("id=order-pay", "");
            trResult2 = trResult2.replace(/\[支付方式\]/g, listp[j].payName);
            trResult2 = trResult2.replace(/\[支付金额\]/g, listp[j].amount.toFixed(2));
            orderItems2 += trResult2;
        }
        style = style.replace(trReg2, orderItems2);
    }
    style = style.replace(/[\r\n]/g, " ");
    style = style.replace(/ADD_PRINT_HTML/g, "ADD_PRINT_HTM");
    //打印
    var LODOP = getLodop();
    LODOP.PRINT_INIT("小票打印");
    eval(style);
    LODOP.PRINT();
}

//实际支付总金额
function getRealPay(listp){
    var amount = 0.0;
    for(var i = 0;i < listp.length;i++){
        amount += parseFloat(listp[i].amount);
    }
    return amount.toFixed(2);
}
//实际应收总金额
function getRealReceive(listL){
    var realAmount = 0.0;
    for(var i = 0;i < listL.length;i++){
        realAmount += parseFloat(listL[i].realAmount);
    }
    return realAmount.toFixed(2);
}

//总数量
function getRealQty(listL){
    var qty = 0.0;
    for(var i = 0;i < listL.length;i++){
        qty += parseFloat(listL[i].qty);
    }
    return qty.toFixed(2);
}
//获取调用时的系统时间
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}

//esc
function escBtn(){
    location.reload(false);
}

//显示预定窗口
function showBookDialog(){
    var item = $('#grid').datagrid('getRows');
    if(item.length == 0){

    }else{
        showBookPayDialog();
    }
}

//显示 预订支付窗口
function showBookPayDialog(){
    $('#bookPayDialog').modal('show');
}

//确定预订
function saveBook(){
    var item = $('#grid').datagrid('getRows');
    //收银时间
    var saleDate = getNowFormatDate();
    //生成小票号
    var flowNo = 'YD'+ saleDate.replace(/\:/g,"").replace(/\-/g,"").replace(/\s/g,"");
    //组织数据包
    var data  = new Array();
    for(var i = 0;i < item.length;i++){
        var ob = new Object();
        ob.flowNo = flowNo;
        ob.saleDate = saleDate;
        ob.skuId = item[i].skuId;
        ob.employeeCode = item[i].employeeCode;
        ob.barcode = item[i].barcode;
        ob.isGift = item[i].isGift;
        ob.productCode = item[i].productCode;
        ob.productName = item[i].productName;
        ob.skuCode = item[i].code;
        ob.skuName = item[i].skuName;
        ob.price = item[i].price;
        ob.qty = item[i].qty;
        ob.discount = item[i].discount;
        ob.relPrice = item[i].relPrice;
        ob.amount = item[i].amount;
        ob.change = 0;
        //会员
        //------------------------
        var d = new Array();
        for(var k = 0;k < 1;k++){
            var op = new Object();
            op.code = "book_payments";
            op.payAmount = $('#bookPrice').val().trim();
            d[k] = op;
        }
        ob.paymentList = d;
        data[i] = ob;
    }
    //关闭预订支付窗口
    $('#bookPayDialog').modal('hide');
    //提交收银信息
    $.ajax({
        type: 'POST',
        url: contextPath + 'pos/saveBook',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(map){
            //打印小票
            print(data[0].flowNo,"预订","pos/print/getBookList");
        }
    });
}
//挂单
function areCanceled(){
    var items = $('#grid').datagrid('getRows');
    var vipInfo = $('#vipInfo').val();
    if(items.length == 0){
        alertLittle("请先扫描商品条码");
        return;
    }
    var time = getNowFormatDate();
    var obg = {};
    obg.items = items;
    obg.vipInfo = vipInfo;
    obg.time = time;
    $.ajax({
        type:'post',
        url:contextPath + '/pos/areCanceled',
        data:JSON.stringify(obg),
        dataType:'json',
        contentType: 'application/json',
        success:function(data){
            alert('提示',data.msg,2000);
            clean();
        }
    });
}
//取单列表
function showASingleList(){
    var item = $('#grid').datagrid('getRows');
    if(item.length > 0){
        alertLittle("请先结算完成，或者撤单");
        return;
    }
    $('#aSingleListDialog').modal('show');
    setTimeout("$('#aSingleGrid').datagrid({url: contextPath + '/pos/aSingle'})", 500);
}

//取单 取出一张单据明细
function confirmASingle(){
    var item = $('#aSingleGrid').datagrid('getSelected');
    if(item == null){
        alertLittle("请选择数据");
        return;
    }
    $.post(contextPath + '/pos/aSingleMx',{time:item.time},function(data){
        $("#grid").datagrid('loadData', { total: data.total, rows:data.rows });
        var vipInfo = data.vipInfo;
        $("#vipInfo").val(vipInfo);
        if(vipInfo != null && vipInfo != ""){
            var vipInfoArray = vipInfo.split('/');
            cvipDiscount = parseFloat(vipInfoArray[2]);
        }
        $("#aSingleListDialog").modal('hide');
        //计算
        total();
    });
}

//开钱箱
function openCashBox(){
    var LODOP = getLodop();
    LODOP.SEND_PRINT_RAWDATA(String.fromCharCode(27,112,1,128,128));
}

//清空数据
function clean(){
    $('#grid').datagrid('loadData', {total: 0, rows: []});
    $('#barcode').val("");
    $('#cvipcode').val("");
    $('#cqty').val("");
    $('#cdiscount').val("");
    $('#camount').val("");
    $('#productName').val("");
    $('#subtotal').val("");

    $('#vipInfo').val("");
    $('#favourablePrice').val("");
    $('#qty').val("");
    $('#amount').val("");

    $('#change').val("");
    $('#receivable').val("");
    //清空支付方式
    var list = $("#payDiv input[name='pay']").serializeArray();
    for(var k = 0;k < list.length;k++){
        $('#' + list[k].value).val("");
    }
    //会员折扣
    cvipDiscount = 10;
}

$(window).resize(function () {
    $('#grid').datagrid('resize');
});