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
                {field: 'productCode',hidden:true},
                {field: 'code',hidden:true},//skuCode
                {field: 'barcode', title: '条码', width: 150, fitColumns: true},
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
    for (var i = 0; i < item.length; i++) {
        qty += parseFloat(item[i].qty);
        amount += parseFloat(item[i].amount);
    }
    $('#qty').val(twoDecimal(qty));
    $('#amount').val(twoDecimal(amount));
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
        }else{
            $('#cvipcode').val("");
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
    var flowNo = saleDate.replace(/\:/g,"").replace(/\-/g,"").replace(/\s/g,"");
    //组织数据包
    var data  = new Array();
    for(var i = 0;i < item.length;i++){
        var ob = new Object();
        ob.flowNo = flowNo;
        ob.saleDate = saleDate;
        ob.skuId = item[i].skuId;
        ob.barcode = item[i].barcode;
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
    //提交收银信息
    $.ajax({
        type: 'POST',
        url: contextPath + 'pos/save',
        contentType:"application/json",
        data: JSON.stringify(data),
        success: function(map){
            $('#cashDialog').modal('hide');
            //打印小票
            print(data);
        },
        error: function(map){

        }
    });
//console.log(JSON.stringify(data));
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
function print(item){
    if(print_receipts == "1"){
        $.post(contextPath + 'pos/print/getList',{flowNo:item[0].flowNo},function(data){
            printData(item,data);
            clean();
        });
    }else{
        clean();
    }

}
//组织小票打印内容
function printData(item,data){
    var listp = data.listP;
    var listd = data.listD;
    var listL = data.listL;
    var table = "<table>";
    table += "<tr style='font-size: 16px'><td colspan='4' style='text-align: center'>POS*MART</td></tr>";
    table += "<tr><td colspan='4'>=====================</td></tr>";

    table += "<tr style='font-size: 10px'><td colspan='2'>交易号</td><td colspan='2' style='text-align: right'>"+ listd[0].flowNo +"</td></tr>";
    table += "<tr style='font-size: 10px'><td colspan='2'>收银员</td><td colspan='2' style='text-align: right'>"+ listd[0].createUser +"</td></tr>";
    table += "<tr style='font-size: 10px'><td colspan='2'>交易日期</td><td colspan='2' style='text-align: right'>"+ listd[0].saleDate +"</td></tr>";

    table += "<tr><td colspan='4'>=====================</td></tr>";

    table += "<tr style='font-size: 10px'><td>品名</td><td style='text-align: right'>单价</td><td style='text-align: right'>数量</td><td style='text-align: right'>金额</td></tr>";

    for(var i = 0;i < listL.length;i++){
        table += "<tr style='font-size: 10px'><td>" + listL[i].skuName + "</td>" +
            "<td style='text-align: right'>" + listL[i].realPrice + "</td>" +
            "<td style='text-align: right'>" + listL[i].qty + "</td>" +
            "<td style='text-align: right'>" + listL[i].realAmount + "</td></tr>";
        //商品名称
        table += "<tr style='font-size: 10px'><td colspan='4'>" + listL[i].productName + "</td></tr>";
    }
    table += "<tr><td colspan='4'>=====================</td></tr>";
    table += "<tr style='font-size: 10px'><td colspan='2'>支付方式</td><td colspan='2' style='text-align: right'>支付金额</td></tr>";
    for(var z = 0;z < listp.length;z++){
        table += "<tr style='font-size: 10px'><td colspan='2'>"+ listp[z].payName +"</td><td colspan='2' style='text-align: right'>"+ listp[z].amount.toFixed(2) +"</td></tr>";
    }

    table += "<tr><td colspan='4'>=====================</td></tr>";

    table += "<tr style='font-size: 14px'><td colspan='2'>总计</td><td colspan='2' style='text-align: right'>"+ getRealReceive(listL) +"</td></tr>";
    table += "<tr style='font-size: 14px'><td colspan='2'>件数</td><td colspan='2' style='text-align: right'>"+ getRealQty(listL) +"</td></tr>";
    table += "<tr style='font-size: 10px'><td colspan='2'>实收金额</td><td colspan='2' style='text-align: right'>"+ getRealPay(listp) +"</td></tr>";
    table += "<tr style='font-size: 10px'><td colspan='2'>找零金额</td><td colspan='2' style='text-align: right'>"+ listd[0].fundAmount.toFixed(2) +"</td></tr>";

    table += "<tr><td colspan='4'>=====================</td></tr>";
    table += "<tr><td colspan='4' style='text-align: center;font-size: 16px'>欢迎再次光临</td></tr>";
    table += "</table>";

    $('#pTable').html(table);

    var LODOP = getLodop();
    LODOP.PRINT_INIT("POS小票打印");
    LODOP.ADD_PRINT_BARCODE(5,5,45,45,'QRCode',listd[0].flowNo);
    LODOP.ADD_PRINT_TABLE(0,2,$('#pTable').height(),530,table);
    LODOP.SET_PRINT_PAGESIZE(3,530,20,"");
//    LODOP.PREVIEW();
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
    $('#integral').val("");
    $('#qty').val("");
    $('#amount').val("");

    $('#change').val("");
    $('#receivable').val("");
    //清空支付方式
    var list = $("#payDiv input[name='pay']").serializeArray();
    for(var k = 0;k < list.length;k++){
        $('#' + list[k].value).val("");
    }
}

$(window).resize(function () {
    $('#grid').datagrid('resize');
});