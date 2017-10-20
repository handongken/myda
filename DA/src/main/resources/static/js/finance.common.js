loaddataList();
function emptySeach(){ //清空搜索框
	document.getElementById('statementDateS').value = '';
	document.getElementById('statementDateE').value = '';
	loaddataList();
}
function seachLoad(){ //搜索条件
	var statementDateS = document.getElementById('statementDateS').value;
	var statementDateE = document.getElementById('statementDateE').value;
	if(statementDateS == '' || statementDateE == ''){alert('搜索日期不能为空');return false;}
	loaddataList();
}
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
		document.getElementById('sumMoney').innerHTML = TSum;
	};
	var faild = function(error){
		alert(error);
	};
	//取user 
	var user = JSON.parse(localStorage.user);
	ajaxPost('/selectCertificateByCondition',{'startTime':statementDateS,'endTime':statementDateE},success,faild); 
}
