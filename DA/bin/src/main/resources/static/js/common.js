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
function downExcelExcel(downUrl){//财务导出表格
	var statementDateS = document.getElementById('statementDateS').value;
	if(statementDateS ==''){alert('请输入搜索日期');return false;}
	loading();
	var success = function(data){
		alert(data);
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(sessionStorage.user);
	ajaxDownExcel(downUrl,{"userId":user.userId,"sid":user.sid,"did":user.did,"fid":user.fid,"yid":user.yid,"typeId":user.typeId,"startTime":statementDateS},success,faild); 
} 
function downDataExcel(downUrl){//数据导出表格 续投 赎回 提前赎回
	loading();
	var success = function(data){
		alert(data);
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(sessionStorage.user);
	ajaxDownExcel(downUrl,{"userId":user.userId,"sid":user.sid,"did":user.did,"fid":user.fid,"yid":user.yid,"typeId":user.typeId},success,faild); 
} 
function bindListener(obj,contract){ //删除单行table
	var thisLi = obj.parentNode.parentNode;
	thisLi.parentNode.removeChild(thisLi);
	var success = function(data){
		if(data.code == 1){
			alert("删除成功！");
			window.location.reload();
		}else{
			alert('删除不成功！');
		}
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(sessionStorage.user);
	ajaxPost('/deleteByPrimaryKey',{"contract":contract,"userId":user.userId},success,faild); 
}

function exit(){ //退出
	/*var success = function(data){ // data 是服务器返回的
	};
	var faild = function(error){ // error 是服务器返回的
		alert(error);
	};
	var user = JSON.parse(localStorage.user);
	ajaxPost('/login',{},success,faild); //像服务器发送	*/
	sessionStorage.clear(); //直接清空
	location.href = "loginPage.html";
}
$(function(){//侧边栏
	$('#nav').load('nav.html');
});
function showOverAll(){//展开收起
	$("#overAll").toggleClass('show');
}
function tabList(num,obj){ //tab选项
	//tabList
	var tabs = document.getElementById('tabList').getElementsByClassName('current')[0];
	if(tabs) tabs.className = '';
	obj.className = 'current';
	//tabBox
	var tabBoxOn = document.getElementById('tabBox').getElementsByClassName('tabShow')[0];
	if(tabBoxOn) tabBoxOn.className = 'tabHide';
	document.getElementById('tab'+num).className = 'tabHide tabShow';
}
function emptySeach(){ //清空搜索框 财务
	document.getElementById('statementDateS').value = '';
	document.getElementById('statementDateE').value = '';
	loaddataList();
}
function seachLoad(){ //搜索条件 财务
	var statementDateS = document.getElementById('statementDateS').value;
	var statementDateE = document.getElementById('statementDateE').value;
	if(statementDateS == '' || statementDateE == ''){alert('搜索日期不能为空');return false;}
	loading();
	loaddataList();
}