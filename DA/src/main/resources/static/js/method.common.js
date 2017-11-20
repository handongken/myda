function loaddataList(){//读取财务表格
	document.getElementById('tableHtml').innerHTML ='<table id="tableBox" class="tableBox" width="100%"><thead><tr></tr></thead></table>';
	var TSum = 0;
	var statementDateS = document.getElementById('statementDateS').value;
	var statementDateE = document.getElementById('statementDateE').value;
	var success = function(data){
		createTh(data);
		for(i=0;i<data.length;i++){
			TSum += data[i].interestMonth;
		}
		document.getElementById('sumMoney').innerHTML = TSum.toFixed(2);
	};
	var faild = function(error){
		alert(error);
	}; 
	//取user 
	var user = JSON.parse(localStorage.user);
	ajaxPost('/selectCertificateByCondition',{'startTime':statementDateS,'endTime':statementDateE},false,success,faild);
}
function loaddataStatus1(){//读取表格
	document.getElementById('tableHtml').innerHTML = '<table id="tableBox" class="tableBox" width="100%"><thead><tr></tr></thead></table>';
	var success = function(data){
		createTh(data);
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(localStorage.user);
	var status = 1;
	ajaxPost('/selectByCondition',{"userId":user.userId,"sid":user.sid,"did":user.did,"fid":user.fid,"yid":user.yid,"typeId":user.typeId,"status":status},false,success,faild); 
}

function loaddataStatus2(){//读取表格
	document.getElementById('tableHtml').innerHTML = '<table id="tableBox" class="tableBox" width="100%"><thead><tr></tr></thead></table>';
	var success = function(data){
		createTh(data);
	};
	var faild = function(error){
		alert(error);
	};
	//取user
	var user = JSON.parse(localStorage.user);
	var status = 2;
	ajaxPost('/selectByCondition',{"userId":user.userId,"sid":user.sid,"did":user.did,"fid":user.fid,"yid":user.yid,"typeId":user.typeId,"status":status},false,success,faild); 
}