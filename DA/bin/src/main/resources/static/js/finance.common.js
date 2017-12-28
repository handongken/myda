loaddataList();
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
	ajaxPost('/selectCertificateByCondition',{'startTime':statementDateS,'endTime':statementDateE},success,faild); 
}
