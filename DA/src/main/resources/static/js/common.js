var $_GET = (function() { //获取上一页数据
	var url = window.document.location.href.toString();
	var u = url.split("?");
	if(typeof(u[1]) == "string") {
		u = u[1].split("&");
		var get = {};
		for(var i in u) {
			var j = u[i].split("=");
			get[j[0]] = j[1];
		}
		return get;
	} else {
		return {};
	}
})();

function exit(){ //退出
	/*var success = function(data){ // data 是服务器返回的
		
	};
	var faild = function(error){ // error 是服务器返回的
		alert(error);
	};
	var user = JSON.parse(localStorage.user);
	ajaxPost('/login',{},success,faild); //像服务器发送
*/
	localStorage.clear(); //直接清空
	location.href = "loginPage.html";
}
