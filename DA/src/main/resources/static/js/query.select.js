var frameWorkList = [];
querySelect(); //事业部
$("#sid").change(function(){      
	document.getElementById('did').innerHTML = '<option value="">请选择</option>';
	document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0 || $(this).val() == '0001' || $(this).val() == '0002'){
		document.getElementById('did').innerHTML = '<option value="">请选择</option>';
		document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryDq();
	}
});
$("#did").change(function(){
	document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0){
		document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryFid();
	}
});
$("#fid").change(function(){
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0){
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryYid();
	}
});

function querySelect(){ //查事业部	
	var success = function(data){
		frameWorkList = data;	
		createSelect();
	};
	var faild = function(error){
 		alert(error);
 	};
 	var user = JSON.parse(sessionStorage.user);
 	ajaxPost('/selectOrganizationByCondition',{'typeId':user.typeId,'sid':user.sid,'did':user.did,'fid':user.fid,'yid':user.yid},false,success,faild);
}
function createSelect(){ //创建sid
	var sid = '<option value="">请选择</option>';
	for(var i=0;i<frameWorkList.length;i++){
		sid += '<option value="'+frameWorkList[i].did+'">'+ frameWorkList[i].dname +'[' + frameWorkList[i].did +']</option>';
	}
	document.getElementById('sid').innerHTML = sid;
}
//queryDq();
function queryDq(){//查大区
	var sidList = $("#sid").val();
     var success = function(data){
    	 frameWorkList = data;
		 createDq();
		};
		var faild = function(error){
	 		alert(error);
	 	};
     ajaxPost('/getDqListByCondition',{'D_ID':sidList},false,success,faild);
}
function createDq(){ //创建did
	var pid = '<option value="">请选择</option>';
	for(var i=0;i<frameWorkList.length;i++){
		pid += '<option value="'+frameWorkList[i].pid+'">'+ frameWorkList[i].pname +'[' + frameWorkList[i].pid +']</option>';
	}
	document.getElementById('did').innerHTML = pid;
}
//queryFid();
function queryFid(){//查分公司
	var didList = $("#did").val();
     var success = function(data){
    	 frameWorkList = data;
    	 createFgs();
		};
		var faild = function(error){
	 		alert(error);
	 	};
     ajaxPost('/getFgsListByCondition',{'P_ID':didList},false,success,faild);
}
function createFgs(){ //创建fid
	var fid = '<option value="">请选择</option>';
	for(var i=0;i<frameWorkList.length;i++){
		fid += '<option value="'+frameWorkList[i].fid+'">'+ frameWorkList[i].fname +'[' + frameWorkList[i].fid +']</option>';
	}
	document.getElementById('fid').innerHTML = fid;
}
//queryYid();
function queryYid(){//查营业部
	var fidList = $("#fid").val();
     var success = function(data){
    	 frameWorkList = data;
    	 createYyb();
		};
		var faild = function(error){
	 		alert(error);
	 	};
     ajaxPost('/getYybListByCondition',{'F_ID':fidList},false,success,faild);
}
function createYyb(){ //创建yid
	var yid = '<option value="">请选择</option>';
	for(var i=0;i<frameWorkList.length;i++){
		yid += '<option value="'+frameWorkList[i].yid+'">'+ frameWorkList[i].yname +'[' + frameWorkList[i].yid +']</option>';
	}
	document.getElementById('yid').innerHTML = yid;
}
//queryTid();
function queryTid(){//查团队
	var YidList = $("#yid").val();
     var success = function(data){
    	 frameWorkList = data;
    	 createTid();
		};
		var faild = function(error){
	 		alert(error);
	 	};
     ajaxPost('/getTdListByCondition',{'Y_ID':YidList},false,success,faild);
}
function createTid(){ //创建tid
	var tid = '<option value="">请选择</option>';
	for(var i=0;i<frameWorkList.length;i++){
		tid += '<option value="'+frameWorkList[i].tid+'">'+ frameWorkList[i].tname +'[' + frameWorkList[i].tid +']</option>';
	}
	document.getElementById('tid').innerHTML = tid;
}