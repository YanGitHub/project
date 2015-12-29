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
        content: content,
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