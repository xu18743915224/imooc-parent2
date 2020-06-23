//"window.location.href"、"location.href"是本页面跳转
//"parent.location.href"是上一层页面跳转
//"top.location.href"是最外层的页面跳转
$(function () {
    noTokenToLogin();
});
function noTokenToLogin() {
    $.ajax({
        url: "/noTokenToLogin",
        type: 'post',
        data: {},
        success: function (text) {
            if (text.code == 408) {
                window.top.location.href = user_timeOutPage;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    });
}


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~字典共用方法
//dictDataAction.getDictData("form1,formPanel",aa10_getByAaa100);  使用方法可以逗号隔开多个form赋值
//dictDataAction.getAA10INfoDict("wsz017","WSZ017",aa10_getByAaa100Json);
var aa10Array=[];
var index=0;
var dictDataAction = {
    getDictData:function(formStr,url){
        if(formStr!=''){
            var formStrArr=formStr.split(",");
            for(var i=0;i<formStrArr.length;i++){
                var form = new mini.Form(formStrArr[i]);
                var fields = form.getFields();
                for ( var j = 0; j<fields.length; j++) {
                    var c = fields[j];
                    if(c.el.className.indexOf("mini-combobox")>-1){
                        c.setUrl(url+c.codeType);
                    }
                }
            }
        }
    },
    getAA10INfoDict: function (id,code,url) {
        index++;
        $.ajax({
            url: url + code,
            cache: false,
            success: function (text) {
                var o = mini.decode(text);
                $.each(text, function (index, val) {
                    aa10Array.push(id+val.dictCode);
                    aa10Array.push(val.dictName);
                });
            }
        });
    },
    getAA10INfoDictInfo:function (code,value) {
        var  index = $.inArray(code+value,aa10Array);
        if(index>=0){
            return  aa10Array[index+1];
        }else {
            return "";
        }
    }

}
