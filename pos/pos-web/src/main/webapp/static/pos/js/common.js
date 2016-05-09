function warning(title,content,second){
    $.confirm({
        title: title,
        content: content,
        autoClose: 'cancel|'+second
    });
}

function alert(title,content,second){
    $.alert({
        title: title,
        content: content,
        autoClose: 'confirm|'+second,
        confirm: function(){

        }
    });
}

function alertLittle(content){
    $.confirm({
        title: false,
        content: '友情提示：'+content,
        cancelButton: false,
        confirmButton: false
    });
}

function twoDecimal(value, rowData, rowIndex) {
    if (!value || isNaN(value)) {
        return 0;
    } else {
        return parseFloat(value).toFixed(2);
    }
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

//流水号
function getSerialNo(code){
    var saleDate = getNowFormatDate();
    var no = saleDate.replace(/\:/g,"").replace(/\-/g,"").replace(/\s/g,"");
    return code + no;
}
/**
 * 如是null "null" 返回 ""
 * @param value
 */
function ifNull(value){
    if(value == null || value == "null"){
        return "";
    }else{
        return value;
    }
}
function serializeNo(parmString){
    var parmArray = parmString.split("&");
    var parmStringNew="";
    $.each(parmArray,function(index,data){
        var li_pos = data.indexOf("=");
        if(li_pos >0){
            var name = data.substring(0,li_pos);
            var value = escape(decodeURIComponent(data.substr(li_pos+1)));
            var parm = name+"="+value;
            parmStringNew = parmStringNew=="" ? parm : parmStringNew + '&' + parm;
        }
    });
    return parmStringNew;
}

//表单经过续列化后 转成 Json对象
function conveterParamsToJson(paramsAndValues) {
    var jsonObj = {};
    var param = paramsAndValues.split("&");
    for ( var i = 0; param != null && i < param.length; i++) {
        var para = param[i].split("=");
        jsonObj[para[0]] = para[1];
    }
    return jsonObj;
}

$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$.fn.tree.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().tree.options;
    var idField, textField, parentField;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        textField = opt.textField || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
