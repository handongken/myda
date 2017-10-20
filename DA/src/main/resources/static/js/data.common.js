queryData();
var numReg = /^(0|[1-9][0-9]*)$/;
var phoneReg = /^1[1|2|3|4|5|6|7|8|9][0-9]\d{4,8}$/;
var moneySum = '';//计算值
function selectOnchange(){ //选择产品 自动生成
	if($("#type")[0].selectedIndex == 0){
		$("#zbRatio").val('');
		$("#periods").val('');
		$("#rate").val('');
	}else if($("#type").val() == 0){
		$("#zbRatio").val('0.25');
		$("#periods").val('3');
		$("#rate").val('6.0');
	}else if($("#type").val() == 1){
		$("#zbRatio").val('0.6');
		$("#periods").val('6');
		$("#rate").val('8.0');
	}else if($("#type").val() == 2){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('10.0');
	}else if($("#type").val() == 3){
		$("#zbRatio").val('0.25');
		$("#periods").val('3');
		$("#rate").val('6.5');
	}else if($("#type").val() == 4){
		$("#zbRatio").val('0.6');
		$("#periods").val('6');
		$("#rate").val('8.5');
	}else if($("#type").val() == 5){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('10.5');
	}else if($("#type").val() == 6){
		$("#zbRatio").val('0.25');
		$("#periods").val('3');
		$("#rate").val('7.0');
	}else if($("#type").val() == 7){
		$("#zbRatio").val('0.6');
		$("#periods").val('6');
		$("#rate").val('9.0');
	}else if($("#type").val() == 8){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('11.0');
	}else if($("#type").val() == 9){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('10.5');
	}else if($("#type").val() == 10){
		$("#zbRatio").val('1.6');
		$("#periods").val('18');
		$("#rate").val('11.0');
	}else if($("#type").val() == 11){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('11.0');
	}else if($("#type").val() == 12){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('11.5');
	}else if($("#type").val() == 13){
		$("#zbRatio").val('1.6');
		$("#periods").val('18');
		$("#rate").val('11.5');
	}else if($("#type").val() == 14){
		$("#zbRatio").val('1.6');
		$("#periods").val('18');
		$("#rate").val('12.0');
	}else if($("#type").val() == 15){
		$("#zbRatio").val('2.2');
		$("#periods").val('24');
		$("#rate").val('12.0');
	}else if($("#type").val() == 16){
		$("#zbRatio").val('3');
		$("#periods").val('36');
		$("#rate").val('13.0');
	}else if($("#type").val() == 17){
		$("#zbRatio").val('2.2');
		$("#periods").val('24');
		$("#rate").val('12.5');
	}else if($("#type").val() == 18){
		$("#zbRatio").val('2.2');
		$("#periods").val('24');
		$("#rate").val('13.0');
	}else if($("#type").val() == 19){
		$("#zbRatio").val('3');
		$("#periods").val('36');
		$("#rate").val('13.5');
	}else if($("#type").val() == 20){
		$("#zbRatio").val('3');
		$("#periods").val('36');
		$("#rate").val('14.0');
	}
}
$('#money').keyup(function(){ //出借金额
	moneySum = $(this).val();
    if($('#zbRatio').val() == ''){//计算绩效业绩 = 折标系数*出借金额
        return false;
    }else if($('#rate').val() == ''){//计算总额 = 年化收益*出借金额
        return false;
    }else{
        if(moneySum == ''){
            return false;
        }else{
            $('#jxAchievement').val(($('#zbRatio').val()*moneySum).toFixed(2));
            $('#interestAll').val((($('#rate').val()*moneySum)/100).toFixed(2));
        }
    }
    if($('#interestAll').val() == ''){
		 return false;
	 }else{
		 $('#interestMonth').val(($('#interestAll').val()/$('#periods').val()).toFixed(2));
	 }
});
$('#statementDate').keyup(function(){
	if($('#startDate').val() == ''){
		return false;
	}else{
		$('#statementDate').val($('#startDate').val().substring($('#startDate').val().length,$('#startDate').val().length-2));
	}
});
var todayDate = new Date();
var str = "" + todayDate.getFullYear() + "-";
	str += (todayDate.getMonth()+1) + "-";
	str += todayDate.getDate();
$('#endDate').keyup(function(){$(this).val(str)});
function saveData(){ //保存
	var type = document.getElementById("type").value; //产品名称
	if(type == ""){alert("请选择产品名称");return false;}
	var zbRatio = document.getElementById('zbRatio').value; //折标系数
	if(zbRatio == ""){alert("折标系数不能为空");return false;}
	var money = document.getElementById('money').value;//出借金额
	if(money == "" || !numReg.test(money)){alert("请输入正确出借金额(不能输入空格,只能输入数字)");return false;}
	var jxAchievement = document.getElementById('jxAchievement').value;//绩效业绩 = 出借金额 * 折标系数
	if(jxAchievement == ""){alert("请输入正确绩效业绩(不能输入空格,只能输入数字)");return false;}
	var lcId = document.getElementById('lcId').value; //客户编号
	if(lcId == "" || !numReg.test(lcId)){alert("请输入正确客户编号(不能输入空格,只能输入数字)");return false;}
	var lcManager = document.getElementById('lcManager').value; //客户经理
	if(lcManager == ""){alert("请输入正确客户经理");return false;}
	var tManager = document.getElementById('tManager').value; //团队经理
	if(tManager == ""){alert("请输入正确团队经理");return false;}
	var yyb = document.getElementById('yyb').value;//营业部名称
	if(yyb == ""){alert("请输入正确营业部名称");return false;}
	var yybManager = document.getElementById('yybManager').value;//营业部经理
	if(yybManager == ""){alert("请输入正确营业部经理");return false;}
	var fgs = document.getElementById('fgs').value;//分公司名称
	if(fgs == ""){alert("请输入正确分公司名称");return false;}
	var fgsManager = document.getElementById('fgsManager').value;//分公司经理
	if(fgsManager == ""){alert("请输入正确分公司经理");return false;}
	var dq = document.getElementById('dq').value;//大区名称
	if(dq == ""){alert("请输入正确大区名称");return false;}
	var dqManager = document.getElementById('dqManager').value;//大区经理
	if(dqManager == ""){alert("请输入正确大区经理");return false;}
	var syb = document.getElementById('syb').value;//事业部名称
	if(syb == ""){alert("请输入正确事业部名称");return false;}
	var sybManager = document.getElementById('sybManager').value;//事业部经理
	if(sybManager == ""){alert("请输入正确事业部经理");return false;}
	var periods = document.getElementById('periods').value;//期数
	if(periods == ""){alert("请输入正确期数");return false;}
	var rate = document.getElementById('rate').value;//年化收益
	if(rate == ""){alert("请输入正确年化收益");return false;}
	var interestAll = document.getElementById('interestAll').value;//利息总额
	if(interestAll == ""){alert("请输入正确利息总额");return false;}
	var interestMonth = document.getElementById('interestMonth').value;//月付利息
	if(interestMonth == ""){alert("请输入正确月付利息");return false;}
	var paymentDate = document.getElementById('paymentDate').value;//划扣日期
	if(paymentDate == ""){alert("请输入正确划扣日期");return false;}
	var endDate = document.getElementById('endDate').value;//到期日
	if(endDate == ""){alert("请输入正确到期日");return false;}
	var statementDate = document.getElementById('statementDate').value;//账单日
	if(statementDate == ""){alert("请输入正确账单日");return false;}
	var startDate = document.getElementById('startDate').value;//初始出借日期
	if(startDate == ""){alert("请输入正确初始出借日期");return false;}
	var surplusDate = document.getElementById('surplusDate').value;//即将到期天数
	if(surplusDate == ""){alert("请输入正确即将到期天数");return false;}
	var status = document.getElementById('status').value;//状态
	if(status == ""){alert("请选择状态");return false;}
	var posNo = document.getElementById('posNo').value;//pos机端口号
	if(posNo == ""){alert("请输入正确pos机端口号");return false;}
	var tenderName = document.getElementById('tenderName').value;//出借人
	if(tenderName == ""){alert("请输入正确出借人");return false;}
	var tel = document.getElementById('tel').value;//联系方式
	if(tel == "" || !phoneReg.test(tel)){alert("请输入正确联系方式");return false;}
	var idType = document.getElementById('idType').value;//证件类型
	if(idType == ""){alert("请选择证件类型");return false;}
	var idNo = document.getElementById('idNo').value;//身份证号
	if(idNo == ""){alert("请输入正确身份证号");return false;}
	var borrowAddress = document.getElementById('borrowAddress').value;//出借人地址
	if(borrowAddress == ""){alert("请输入正确出借人地址");return false;}
	var contactName = document.getElementById('contactName').value;//紧急联系人
	if(contactName == ""){alert("请输入正确紧急联系人");return false;}
	var contactTel = document.getElementById('contactTel').value;//紧急联系电话
	if(contactTel == "" || !phoneReg.test(contactTel)){alert("请输入正确紧急联系电话");return false;}
	var contactRelationship = document.getElementById('contactRelationship').value;//紧与出借人
	if(contactRelationship == ""){alert("请输入正确紧与出借人");return false;}
	var continueFlg = document.getElementById('continueFlg').value;//非续投/续投
	if(continueFlg == ""){alert("请选择正确是否续投");return false;}
	var spreadType = document.getElementById('spreadType').value;//推广渠道
	if(spreadType == ""){alert("请输入正确推广渠道");return false;}
	var bank = document.getElementById('bank').value;//汇入银行
	if(bank == ""){alert("请输入正确汇入银行");return false;}
	var branch = document.getElementById('branch').value;//银行支行名称
	if(branch == ""){alert("请输入正确银行支行名称");return false;}
	var cardName = document.getElementById('cardName').value;//开户人姓名
	if(cardName == ""){alert("请输入正确开户人姓名");return false;}
	var cardNo = document.getElementById('cardNo').value;//账号
	if(cardNo == ""){alert("请输入正确账号");return false;}
	var cardProvince = document.getElementById('cardProvince').value;//开卡省
	if(cardProvince == ""){alert("请输入正确开卡省");return false;}
	var cardCity = document.getElementById('cardCity').value;//开卡市
	if(cardCity == ""){alert("请输入正确开卡市");return false;}
	var cardLine = document.getElementById('cardLine').value;//银行行号
	if(cardLine == ""){alert("请输入正确银行行号");return false;}
	var inBank = document.getElementById('inBank').value;//回款银行
	if(inBank == ""){alert("请输入正确回款银行");return false;}
	var inBranch = document.getElementById('inBranch').value;//银行支行名称
	if(inBranch == ""){alert("请输入正确银行支行名称");return false;}
	var inCardName = document.getElementById('inCardName').value;//开户人姓名
	if(inCardName == ""){alert("请输入正确开户人姓名");return false;}
	var inCardNo = document.getElementById('inCardNo').value;//账号
	if(inCardNo == ""){alert("请输入正确账号");return false;}
	var inCardProvince = document.getElementById('inCardProvince').value;//开卡省
	if(inCardProvince == ""){alert("请输入正确开卡省");return false;}
	var inCardCity = document.getElementById('inCardCity').value;//开卡市
	if(inCardCity == ""){alert("请输入正确开卡市");return false;}
	var insUser = document.getElementById('insUser').value;//插入更新者
	var insDate = document.getElementById('insDate').value;//插入更时间
	var updUser = document.getElementById('updUser').value;//更新者
	var updDate = document.getElementById('updDate').value;//更新时间
	var success = function(data){
		if(data.code == 1){
			alert('提交成功！');
			window.location.href = "dataList.html";
		}else{
			alert('请重新输入正确代码！');
		}
	};
	var faild = function(error){
		alert(error);
	};
	var user = JSON.parse(localStorage.user);
	var contract = $_GET['contract'];
	ajaxPost('/updateByPrimaryKey',{"name":user.name,"type":type,"zbRatio":zbRatio,"money":money,"jxAchievement":jxAchievement,
									"lcId":lcId,"lcManager":lcManager,"tmanager":tManager,"yyb":yyb,"yybManager":yybManager,
									"fgs":fgs,"fgsManager":fgsManager,"dq":dq,"dqManager":dqManager,
									"syb":syb,"sybManager":sybManager,"periods":periods,"rate":rate,
									 "interestAll":interestAll,"interestMonth":interestMonth,"paymentDate":paymentDate,
									 "endDate":endDate,"statementDate":statementDate,"startDate":startDate,
									 "surplusDate":surplusDate,"status":status,"posNo":posNo,"tenderName":tenderName,"tel":tel,"idType":idType,"idNo":idNo,
									 "borrowAddress":borrowAddress,"contactName":contactName,"contactTel":contactTel,
									 "contactRelationship":contactRelationship,"continueFlg":continueFlg,"spreadType":spreadType,"bank":bank,
									 "branch":branch,"cardName":cardName,"cardNo":cardNo,"cardProvince":cardProvince,"cardCity":cardCity,
									 "cardLine":cardLine,"inBank":inBank,"inBranch":inBranch,"inCardName":inCardName,"inCardNo":inCardNo,
									 "inCardProvince":inCardProvince,"inCardCity":inCardCity,"insUser":insUser,"insDate":insDate,/*"managerStatus":managerStatus,"managerNo":managerNo,"remark":remark,*/
									 "updUser":updUser,"updDate":updDate,"contract":contract},success,faild);
}
function queryData(){ //查询
	var user = JSON.parse(localStorage.user);
	var success = function(data){
		if(contract !=''){
			document.getElementById("contract").value = data[0].contract; //合同编号
			document.getElementById("type").value = data[0].type;
			document.getElementById('money').value = data[0].money; //出借金额
			document.getElementById('zbRatio').value = data[0].zbRatio; //折标系数
			document.getElementById('jxAchievement').value = data[0].jxAchievement; //绩效业绩 = 出借金额 * 折标系数
			document.getElementById('lcId').value = data[0].lcId; //客户编号
			document.getElementById('lcManager').value = data[0].lcManager; //客户经理
			document.getElementById('tManager').value = data[0].tmanager; //团队经理
			document.getElementById('yyb').value = data[0].yyb;//营业部名称
			document.getElementById('yybManager').value = data[0].yybManager;//营业部经理
			document.getElementById('fgs').value = data[0].fgs;//分公司名称
			document.getElementById('fgsManager').value = data[0].fgsManager;//分公司经理
			document.getElementById('dq').value = data[0].dq;//大区名称
			document.getElementById('dqManager').value = data[0].dqManager;//大区经理
			document.getElementById('syb').value = data[0].syb;//事业部名称
			document.getElementById('sybManager').value = data[0].sybManager;//事业部经理
			document.getElementById('periods').value = data[0].periods;//期数
			document.getElementById('rate').value = data[0].rate;//年化收益
			document.getElementById('interestAll').value = data[0].interestAll;//利息总额
			document.getElementById('interestMonth').value = data[0].interestMonth;//月付利息
			document.getElementById('paymentDate').value = data[0].paymentDate;//划扣日期
			document.getElementById('endDate').value = data[0].endDate;//到期日
			document.getElementById('statementDate').value = data[0].statementDate;//账单日
			document.getElementById('startDate').value = data[0].startDate;//初始出借日期
			document.getElementById('surplusDate').value = data[0].surplusDate;//即将到期天数
			document.getElementById('status').value = data[0].status;//状态
			document.getElementById('posNo').value = data[0].posNo;//pos机端口号
			document.getElementById('tenderName').value = data[0].tenderName;//出借人
			document.getElementById('tel').value = data[0].tel;//联系方式
			document.getElementById('idType').value = data[0].idType;
			document.getElementById('idNo').value = data[0].idNo;//身份证号
			document.getElementById('borrowAddress').value = data[0].borrowAddress;//出借人地址
			document.getElementById('contactName').value = data[0].contactName;//紧急联系人
			document.getElementById('contactTel').value = data[0].contactTel;//紧急联系电话
			document.getElementById('contactRelationship').value = data[0].contactRelationship;//紧急联系关系
			document.getElementById('continueFlg').value = data[0].continueFlg;//非续投/续投
			document.getElementById('spreadType').value = data[0].spreadType;//推广渠道
			document.getElementById('bank').value = data[0].bank;//汇入银行
			document.getElementById('branch').value = data[0].branch;//银行支行名称
			document.getElementById('cardName').value = data[0].cardName;//开户人姓名
			document.getElementById('cardNo').value = data[0].cardNo;//账号
			document.getElementById('cardProvince').value = data[0].cardProvince;//开卡省
			document.getElementById('cardCity').value = data[0].cardCity;//开卡市
			document.getElementById('cardLine').value = data[0].cardLine;//银行行号
			document.getElementById('inBank').value = data[0].inBank;//回款银行
			document.getElementById('inBranch').value = data[0].inBranch;//银行支行名称
			document.getElementById('inCardName').value = data[0].inCardName;//开户人姓名
			document.getElementById('inCardNo').value = data[0].inCardNo;//账号
			document.getElementById('inCardProvince').value = data[0].inCardProvince;//开卡省
			document.getElementById('inCardCity').value = data[0].inCardCity;//开卡市
			/*$("#inCardProvince").empty(); //开卡省
			 $("#inCardProvince").append("<option value=0 selected>请选择开卡省</option>"); 
			for(var i = 0;i<data.length;i++){
				var item = data[i];
				 $("#inCardProvince").append("<option  value=" + item.inCardProvince + ">" + item.inCardProvince + "</option>");
			}*/
			document.getElementById('insUser').value = data[0].insUser;//插入更新者
			document.getElementById('insDate').value = data[0].insDate;//插入更时间
			document.getElementById('updUser').value = data[0].updUser;//更新者
			document.getElementById('updDate').value = data[0].updDate;//更新时间
			document.getElementById('managerNo').value = user.name;//审批者
			document.getElementById('managerStatus').value = data[0].managerStatus;//审批状态
			document.getElementById('remark').value = data[0].remark;//备注
		}
	};
	var faild = function(error){
		alert(error);
	};
	var contract = $_GET['contract'];
	if(contract == ""){
		$("#contract").attr("disabled",false);
		$("#addBtn").css("display","block");
		$("#editBtn").css("display","none");
		$(".addHidden").css("display","none");
	}else{
		$("#addBtn").css("display","none");
		$("#editBtn").css("display","block");
		$(".addHidden").css("display","block");
	}
	ajaxPost('/selectByCondition',{"contract":contract},success,faild);
}
function addData(){ //新增
	var contract = document.getElementById('contract').value;//合同编号 
	if(contract == ""|| !numReg.test(contract)){alert("请输入正确合同编号(不能输入空格,只能输入数字)");return false;}
	var type = document.getElementById("type").value; //产品名称
	if(type == ""){alert("请选择正确产品名称");return false;}
	var money = document.getElementById('money').value;//出借金额
	if(money == "" || !numReg.test(money)){alert("请输入正确出借金额((不能输入空格,只能输入数字))");return false;}
	var zbRatio = document.getElementById('zbRatio').value; //折标系数
	if(zbRatio == ""){alert("请输入正确折标系数");return false;}
	var jxAchievement = document.getElementById('jxAchievement').value;//绩效业绩 = 出借金额 * 折标系数
	if(jxAchievement == ""){alert("请输入正确绩效业绩((不能输入空格,只能输入数字))");return false;}
	var lcId = document.getElementById('lcId').value; //客户编号
	if(lcId == "" || !numReg.test(lcId)){alert("请输入正确客户编号(不能输入空格,只能输入数字)");return false;}
	var lcManager = document.getElementById('lcManager').value; //客户经理
	if(lcManager == ""){alert("请输入正确客户经理");return false;}
	var tManager = document.getElementById('tManager').value; //团队经理
	if(tManager == ""){alert("请输入正确团队经理");return false;}
	var yyb = document.getElementById('yyb').value;//营业部名称
	if(yyb == ""){alert("请输入正确营业部名称");return false;}
	var yybManager = document.getElementById('yybManager').value;//营业部经理
	if(yybManager == ""){alert("请输入正确营业部经理");return false;}
	var fgs = document.getElementById('fgs').value;//分公司名称
	if(fgs == ""){alert("请输入正确分公司名称");return false;}
	var fgsManager = document.getElementById('fgsManager').value;//分公司经理
	if(fgsManager == ""){alert("请输入正确分公司经理");return false;}
	var dq = document.getElementById('dq').value;//大区名称
	if(dq == ""){alert("请输入正确大区名称");return false;}
	var dqManager = document.getElementById('dqManager').value;//大区经理
	if(dqManager == ""){alert("请输入正确大区经理");return false;}
	var syb = document.getElementById('syb').value;//事业部名称
	if(syb == ""){alert("请输入正确事业部名称");return false;}
	var sybManager = document.getElementById('sybManager').value;//事业部经理
	if(sybManager == ""){alert("请输入正确事业部经理");return false;}
	var periods = document.getElementById('periods').value;//期数
	if(periods == ""){alert("请输入正确期数");return false;}
	var rate = document.getElementById('rate').value;//年化收益
	if(rate == ""){alert("请输入正确年化收益");return false;}
	var interestAll = document.getElementById('interestAll').value;//利息总额
	if(interestAll == ""){alert("请输入正确利息总额");return false;}
	var interestMonth = document.getElementById('interestMonth').value;//月付利息
	if(interestMonth == ""){alert("请输入正确月付利息");return false;}
	var paymentDate = document.getElementById('paymentDate').value;//划扣日期
	if(paymentDate == ""){alert("请输入正确划扣日期");return false;}
	var endDate = document.getElementById('endDate').value;//到期日
	if(endDate == ""){alert("请输入正确到期日");return false;}
	var statementDate = document.getElementById('statementDate').value;//账单日
	if(statementDate == ""){alert("请输入正确账单日");return false;}
	var startDate = document.getElementById('startDate').value;//初始出借日期
	if(startDate == ""){alert("请输入正确初始出借日期");return false;}
	var surplusDate = document.getElementById('surplusDate').value;//即将到期天数
	if(surplusDate == ""){alert("请输入正确即将到期天数");return false;}
	var status = document.getElementById('status').value;//状态
	if(status == ""){alert("请选择正确状态");return false;}
	var posNo = document.getElementById('posNo').value;//pos机端口号
	if(posNo == ""){alert("请输入正确pos机端口号");return false;}
	var tenderName = document.getElementById('tenderName').value;//出借人
	if(tenderName == ""){alert("请输入正确出借人");return false;}
	var tel = document.getElementById('tel').value;//联系方式
	if(tel == "" || !phoneReg.test(tel)){alert("请输入正确联系方式");return false;}
	var idType = document.getElementById('idType').value;//证件类型
	if(idType == ""){alert("请选择正确证件类型");return false;}
	var idNo = document.getElementById('idNo').value;//身份证号
	if(idNo == ""){alert("请输入正确身份证号");return false;}
	var borrowAddress = document.getElementById('borrowAddress').value;//出借人地址
	if(borrowAddress == ""){alert("请输入正确出借人地址");return false;}
	var contactName = document.getElementById('contactName').value;//紧急联系人
	if(contactName == ""){alert("请输入正确紧急联系人");return false;}
	var contactTel = document.getElementById('contactTel').value;//紧急联系电话
	if(contactTel == "" || !phoneReg.test(contactTel)){alert("请输入正确紧急联系电话");return false;}
	var contactRelationship = document.getElementById('contactRelationship').value;//紧与出借人
	if(contactRelationship == ""){alert("请输入正确紧与出借人");return false;}
	var continueFlg = document.getElementById('continueFlg').value;//非续投/续投
	if(continueFlg == ""){alert("请选择正确是否续投");return false;}
	var spreadType = document.getElementById('spreadType').value;//推广渠道
	if(spreadType == ""){alert("请输入正确推广渠道");return false;}
	var bank = document.getElementById('bank').value;//汇入银行
	if(bank == ""){alert("请输入正确汇入银行");return false;}
	var branch = document.getElementById('branch').value;//银行支行名称
	if(branch == ""){alert("请输入正确银行支行名称");return false;}
	var cardName = document.getElementById('cardName').value;//开户人姓名
	if(cardName == ""){alert("请输入正确开户人姓名");return false;}
	var cardNo = document.getElementById('cardNo').value;//账号
	if(cardNo == ""){alert("请输入正确账号");return false;}
	var cardProvince = document.getElementById('cardProvince').value;//开卡省
	if(cardProvince == ""){alert("请输入正确开卡省");return false;}
	var cardCity = document.getElementById('cardCity').value;//开卡市
	if(cardCity == ""){alert("请输入正确开卡市");return false;}
	var cardLine = document.getElementById('cardLine').value;//银行行号
	if(cardLine == ""){alert("请输入正确银行行号");return false;}
	var inBank = document.getElementById('inBank').value;//回款银行
	if(inBank == ""){alert("请输入正确回款银行");return false;}
	var inBranch = document.getElementById('inBranch').value;//银行支行名称
	if(inBranch == ""){alert("请输入正确银行支行名称");return false;}
	var inCardName = document.getElementById('inCardName').value;//开户人姓名
	if(inCardName == ""){alert("请输入正确开户人姓名");return false;}
	var inCardNo = document.getElementById('inCardNo').value;//账号
	if(inCardNo == ""){alert("请输入正确账号");return false;}
	var inCardProvince = document.getElementById('inCardProvince').value;//开卡省
	if(inCardProvince == ""){alert("请输入正确开卡省");return false;}
	var inCardCity = document.getElementById('inCardCity').value;//开卡市
	if(inCardCity == ""){alert("请输入正确开卡市");return false;}
	var success = function(data){
		if(data.code == 1){
			alert('添加成功！');
			window.location.href = "dataList.html";
		}else{
			alert('请确定添加正确代码，并且合同号不能重复。');
		}
	};
	var faild = function(error){
		alert(error);
	};
	var user = JSON.parse(localStorage.user);
	ajaxPost('/insertSelective',{"contract":contract,"name": user.name,"type":type,"money":money,"zbRatio":zbRatio,"jxAchievement":jxAchievement,
								 "lcId":lcId,"lcManager":lcManager,
								 "tmanager":tManager,"yyb":yyb,"yybManager":yybManager,"fgs":fgs,"fgsManager":fgsManager,"dq":dq,
								 "dqManager":dqManager,"syb":syb,"sybManager":sybManager,"periods":periods,"rate":rate,
								 "interestAll":interestAll,"interestMonth":interestMonth,"paymentDate":paymentDate,"endDate":endDate,
								 "statementDate":statementDate,"startDate":startDate,"surplusDate":surplusDate,"status":status,
								 "posNo":posNo,"tenderName":tenderName,"tel":tel,"idType":idType,"idNo":idNo,
								 "borrowAddress":borrowAddress,"contactName":contactName,"contactTel":contactTel,
								 "contactRelationship":contactRelationship,"continueFlg":continueFlg,"spreadType":spreadType,"bank":bank,
								 "branch":branch,"cardName":cardName,"cardNo":cardNo,"cardProvince":cardProvince,"cardCity":cardCity,
								 "cardLine":cardLine,"inBank":inBank,"inBranch":inBranch,"inCardName":inCardName,"inCardNo":inCardNo,
								 "inCardProvince":inCardProvince,"inCardCity":inCardCity
								 },success,faild);
}
function VerifierData(){//审批提交
	var managerNo = document.getElementById('managerNo').value;//审批者
	var managerStatus = document.getElementById('managerStatus').value;//审批状态
	if(managerStatus == ""){alert("请选择正确审批状态");return false;}
	var remark = document.getElementById('remark').value;//备注
	if(remark == ""){alert("请填写正确备注");return false;}
	var success = function(data){
		if(data.code == 1){
			alert('提交成功！');
			window.location.href = "dataList.html";
		}else{
			alert('请重新输入正确代码！');
		}
	};
	var faild = function(error){
		alert(error);
	};
	var user = JSON.parse(localStorage.user);
	var contract = $_GET['contract'];
	ajaxPost('/approve',{"contract":contract,"managerNo":user.name,"managerStatus":managerStatus,"remark":remark},success,faild);
} 
