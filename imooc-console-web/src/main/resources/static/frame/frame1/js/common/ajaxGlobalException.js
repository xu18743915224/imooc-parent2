//www.miniui.com/forum/forum.php?mod=viewthread&tid=26913

//查了一下官方API ，这个直接写boot.js文件也会出错，我是写在一个js文件里然后boot.js中document.write 这个JS文件就正常了


//1.boot.js添加引用下面js文件
//document.write('<script src="' + bootPATH + '../frame/frame1/js/common/ajaxGlobalException.js" type="text/javascript" ></sc' + 'ript>');
//2.添加下面代码
//-----------------------------------全局异常处理------------------------------------------------
$(document).ajaxError(function (evt, request, settings,ex) {
    alert(ex);
})