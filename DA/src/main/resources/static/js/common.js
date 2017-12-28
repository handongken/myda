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
	if(statementDateS ==''){alert('请输入开始日期');return false;}
	var statementDateE = document.getElementById('statementDateE').value;
	if(statementDateE ==''){alert('请输入结束日期');return false;}
	loading();
	var success = function(data){
		alert(data);
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(sessionStorage.user);
	ajaxDownExcel(downUrl,{"userId":user.userId,"sid":user.sid,"did":user.did,"fid":user.fid,"yid":user.yid,"typeId":user.typeId,"startTime":statementDateS,"endTime":statementDateE},success,faild); 
} 
function downDataExcel(downUrl){//数据导出表格 续投 赎回 提前赎回 用户
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
function bindListener(obj,contract){ //数据删除单行table
	var thisLi = obj.parentNode.parentNode;
	thisLi.parentNode.removeChild(thisLi);
	var success = function(data){
		alert(data);
	};
	var faild = function(error){
		if(error.code == 1){
			alert("删除成功！");
			window.location.reload();
		}else{
			alert('删除不成功！');
		}
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
	var user = JSON.parse(sessionStorage.user);
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
var numReg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
var phoneReg = /^1[1|2|3|4|5|6|7|8|9][0-9]\d{4,8}$/;
var idCardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;