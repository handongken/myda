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
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('11.0');
	}else if($("#type").val() == 11){
		$("#zbRatio").val('1.2');
		$("#periods").val('12');
		$("#rate").val('11.5');
	}else if($("#type").val() == 12){
		$("#zbRatio").val('1.7');
		$("#periods").val('24');
		$("#rate").val('12.0');
	}else if($("#type").val() == 13){
		$("#zbRatio").val('2.5');
		$("#periods").val('36');
		$("#rate").val('13.0');
	}else if($("#type").val() == 14){
		$("#zbRatio").val('1.7');
		$("#periods").val('24');
		$("#rate").val('12.5');
	}else if($("#type").val() == 15){
		$("#zbRatio").val('1.7');
		$("#periods").val('24');
		$("#rate").val('13.0');
	}else if($("#type").val() == 16){
		$("#zbRatio").val('2.5');
		$("#periods").val('36');
		$("#rate").val('13.5');
	}else if($("#type").val() == 17){
		$("#zbRatio").val('2.5');
		$("#periods").val('36');
		$("#rate").val('14.0');
	}
}
var moneySum = 0;//计算值 
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

$('#rate').keyup(function(){ //年化收益
	$('#interestAll').val((($('#money').val()*$('#rate').val())/100).toFixed(2)); //利息总额
	$('#interestMonth').val(($('#interestAll').val()/$('#periods').val()).toFixed(2)); //月付利息
});

Date.prototype.Format = function (fmt) { //格式年月日
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "h+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
} 
$('#startDate').blur(function(){ //计算到期日
	 //账单日是初始出借日期的day
	$('#statementDate').val($('#startDate').val().substring($('#startDate').val().length,$('#startDate').val().length-2));
	var nowVal = $("#startDate").val().replace(/-/g,"-");
	var nowDate,time= ''; 
	if($(periods).val() == '3'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+3);//设置月份 +3 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
		 time = nowDate.Format("yyyy-MM-dd");
		 $("#endDate").val(time);
	}else if($(periods).val() == '6'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+6);//设置月份 +6 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
	     var time = nowDate.Format("yyyy-MM-dd");  
	     $("#endDate").val(time);
	}else if($(periods).val() == '12'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+12);//设置月份 +12 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
	     var time = nowDate.Format("yyyy-MM-dd");  
	     $("#endDate").val(time);
	}else if($(periods).val() == '18'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+18);//设置月份 +18 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
	     var time = nowDate.Format("yyyy-MM-dd");  
	     $("#endDate").val(time);
	}else if($(periods).val() == '24'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+24);//设置月份 +24 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
	     var time = nowDate.Format("yyyy-MM-dd");  
	     $("#endDate").val(time);
	}else if($(periods).val() == '36'){
		 nowDate = new Date(nowVal);//获取当前时间  
		 nowDate.setMonth(nowDate.getMonth()+36);//设置月份 +36 月  
		 nowDate.setDate(nowDate.getDate()-1);//设置天数 -1 天  
	     var time = nowDate.Format("yyyy-MM-dd");  
	     $("#endDate").val(time);
	}
	//计算到期天数
	var newDate,newDay,endDate,diff,diff_newD = '';
	var endDate = $("#endDate").val().replace(/-/g,"-");
	newDate = new Date();
	newDay = newDate.getFullYear() + '-' + (newDate.getMonth()+1) + '-' + newDate.getDate();
	endDate = new Date(endDate);
	newDate = new Date(newDay);
	if(endDate <= newDate){$('#surplusDate').val('0');}
	else{
		diff = endDate.valueOf() - newDate.valueOf();
		diff_newD = parseInt(diff/(1000*60*60*24));
		$('#surplusDate').val('还有'+diff_newD+'天到期');
	}
});
var fileData = []; //获取图片上传路径
function upload(){  //上传
	if(document.getElementById("file").value!=''){
		loading();
		var user = JSON.parse(sessionStorage.user);
		var contract = document.getElementById('contract').value;
		var i;
		var fileInput = document.getElementById('file');
		var fileS = fileInput.files;
		var formData = new FormData();
		formData.append('userId',user.userId);
		formData.append('contract',contract);
		for (i = 0; i < fileS.length ; i++) {
			var fileI = fileS[i];
			if (!fileI.type.match('image.*')) {
			    continue;
			}
			formData.append('file', fileI);
		}
		//formData.append('file',$('#file')[0].files[0]);
		$.ajax({
			url: '/uploadBatchFiles',
			type: "POST",
			data: formData,
			async: false,    
	        cache: false, 
			contentType: false,
			processData: false,
			success: function (data) {
				fileData = data;
				if (fileData[0].message == 'success') {
					document.getElementById("file").value = '';
					var divHTML ='<div class="tz-gallery">';
					for(var j = 0;j<fileData.length;j++){
						divHTML += '<div class="img_box"><input type="hidden" name="certs[]" value="'+fileData[j].fileUrl+'"><div class="thumbnail"><a href="'+fileData[j].fileUrl+'"><img src="'+fileData[j].fileUrl+'" alt="图"></a></div><i class="closeBtn" onClick="removeCert(\''+j+'\',this)"></i></div>';
					}
					divHTML +='</div>';
					var imgsDiv = document.getElementById("imgs");
					imgsDiv.innerHTML = imgsDiv.innerHTML + divHTML;
					delLoading();
					imgHover();
				}else{
					alert('请选择正确图片！');
				}
			},
			error: function () {
				alert("上传失败！请输入合同号！");
				delLoading();
			},
			dataType: 'json'
		});
	}else{
		alert('请选择文件');
	}
}
function removeCert(j,obj){ //删除节点图片
	var user = JSON.parse(sessionStorage.user);
	var contract = fileData[j].contract;
	var filePath = fileData[j].filePath;
	var fileName = fileData[j].fileName;
	$.ajax({
		url: '/deleteFile',
		type: "POST",
		data: {'userId':user.userId,'filePath':filePath,'fileName':fileName,'contract':contract},
		async: false,    
        cache: false, 
		success: function (data) {
			obj.parentNode.parentNode.removeChild(obj.parentNode);
		},
		error: function () {
			alert("删除失败！");
		},
		dataType: 'json'
	});
} 
var imgHover = function(){ //图片放大
	baguetteBox.run('.tz-gallery');
}

function saveData(){ //保存
	var type = document.getElementById("type").value; //产品名称
	if(type == ""){alert("请选择产品名称");return false;}
	var zbRatio = document.getElementById('zbRatio').value; //折标系数
	if(zbRatio == ""){alert("折标系数不能为空");return false;}
	var money = document.getElementById('money').value;//出借金额
	if(money == ""){alert("请输入正确出借金额");return false;}
	var jxAchievement = document.getElementById('jxAchievement').value;//绩效业绩 = 出借金额 * 折标系数
	var lcId = document.getElementById('lcId').value; //客户编号
	if(lcId == ""){alert("请输入正确客户编号");return false;}
	var lcManager = document.getElementById('lcManager').value; //客户经理
	var tManager = document.getElementById('tManager').value; //团队经理
	var yid = document.getElementById('yid').value;//营业部名称
	var yybManager = document.getElementById('yybManager').value;//营业部经理
	var fid = document.getElementById('fid').value;//分公司名称
	var fgsManager = document.getElementById('fgsManager').value;//分公司经理
	var did = document.getElementById('did').value;//大区名称
	var dqManager = document.getElementById('dqManager').value;//大区经理
	var sid = document.getElementById('sid').value;//事业部名称
	var sybManager = document.getElementById('sybManager').value;//事业部经理
	var sybAssistantManager = document.getElementById('sybAssistantManager').value;//事业部副经理
	var dqAssistantManager = document.getElementById('dqAssistantManager').value;//大区副经理
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
	var cardAll = document.getElementById('cardAll').value;//开卡省
	if(cardAll == ""){alert("请输入正确开卡省市");return false;}
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
	var inCardAll = document.getElementById('inCardAll').value;//开卡省
	if(inCardAll == ""){alert("请输入正确开卡省市");return false;}
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
	var user = JSON.parse(sessionStorage.user);
	var contract = $_GET['contract'];
	ajaxPost('/updateByPrimaryKeySelective',{"userId":user.userId,"type":type,"zbRatio":zbRatio,"money":money,"jxAchievement":jxAchievement,
									"lcId":lcId,"lcManager":lcManager,"tmanager":tManager,"yyb":yid,"yybManager":yybManager,
									"fgs":fid,"fgsManager":fgsManager,"dq":did,"dqManager":dqManager,
									"syb":sid,"sybManager":sybManager,"sybAssistantManager":sybAssistantManager,"dqAssistantManager":dqAssistantManager,"periods":periods,"rate":rate,
									 "interestAll":interestAll,"interestMonth":interestMonth,"paymentDate":paymentDate,
									 "endDate":endDate,"statementDate":statementDate,"startDate":startDate,
									 "surplusDate":surplusDate,"status":status,"posNo":posNo,"tenderName":tenderName,"tel":tel,"idType":idType,"idNo":idNo,
									 "borrowAddress":borrowAddress,"contactName":contactName,"contactTel":contactTel,
									 "contactRelationship":contactRelationship,"continueFlg":continueFlg,"spreadType":spreadType,"bank":bank,
									 "branch":branch,"cardName":cardName,"cardNo":cardNo,"cardAddr":cardAll,
									 "cardLine":cardLine,"inBank":inBank,"inBranch":inBranch,"inCardName":inCardName,"inCardNo":inCardNo,
									 "inCardAddr":inCardAll,"insUser":insUser,"insDate":insDate,/*"managerStatus":managerStatus,"managerNo":managerNo,"remark":remark,*/
									 "updUser":updUser,"updDate":updDate,"contract":contract},false,success,faild);
}
function queryData(){ //查询
	var user = JSON.parse(sessionStorage.user);
	var contract = $_GET['contract'];
	var success = function(data){
		if(contract !=''){
			document.getElementById("contract").value = data.contract; //合同编号
			document.getElementById("type").value = data.type;
			document.getElementById('money').value = data.money; //出借金额
			document.getElementById('zbRatio').value = data.zbRatio; //折标系数
			document.getElementById('jxAchievement').value = data.jxAchievement; //绩效业绩 = 出借金额 * 折标系数
			document.getElementById('lcId').value = data.lcId; //客户编号
			document.getElementById('lcManager').value = data.lcManager; //客户经理
			document.getElementById('tManager').value = data.tmanager; //团队经理
			document.getElementById('sid').value = data.syb;//事业部名称
			if(document.getElementById('sid').value ==''){document.getElementById('sid').value = 0;}
			document.getElementById('sybManager').value = data.sybManager;//事业部经理
			queryDq();
			document.getElementById('did').value = data.dq;//大区名称
			if(document.getElementById('did').value ==''){document.getElementById('did').value = 0;}
			document.getElementById('dqManager').value = data.dqManager;//大区经理
			queryFid();
			document.getElementById('fid').value = data.fgs;//分公司名称
			if(document.getElementById('fid').value ==''){document.getElementById('fid').value = 0;}
			document.getElementById('fgsManager').value = data.fgsManager;//分公司经理
			queryYid();
			document.getElementById('yid').value = data.yyb;//营业部名称
			if(document.getElementById('yid').value ==''){document.getElementById('yid').value = 0;}
			/*queryTid();
			document.getElementById('tid').value = data.tid;//团队
			if(document.getElementById('tid').value ==''){document.getElementById('tid').value = 0;}*/
			document.getElementById('yybManager').value = data.yybManager;//营业部经理
			document.getElementById('sybAssistantManager').value = data.sybAssistantManager;//营业部经理
			document.getElementById('dqAssistantManager').value = data.dqAssistantManager;//营业部经理
			document.getElementById('periods').value = data.periods;//期数
			document.getElementById('rate').value = data.rate;//年化收益
			document.getElementById('interestAll').value = data.interestAll;//利息总额
			document.getElementById('interestMonth').value = data.interestMonth;//月付利息
			document.getElementById('paymentDate').value = data.paymentDate;//划扣日期
			document.getElementById('endDate').value = data.endDate;//到期日
			document.getElementById('statementDate').value = data.statementDate;//账单日
			document.getElementById('startDate').value = data.startDate;//初始出借日期
			document.getElementById('surplusDate').value = data.surplusDate;//即将到期天数
			document.getElementById('status').value = data.status;//状态
			document.getElementById('posNo').value = data.posNo;//pos机端口号
			document.getElementById('tenderName').value = data.tenderName;//出借人
			document.getElementById('tel').value = data.tel;//联系方式
			document.getElementById('idType').value = data.idType;
			document.getElementById('idNo').value = data.idNo;//身份证号
			document.getElementById('borrowAddress').value = data.borrowAddress;//出借人地址
			document.getElementById('contactName').value = data.contactName;//紧急联系人
			document.getElementById('contactTel').value = data.contactTel;//紧急联系电话
			document.getElementById('contactRelationship').value = data.contactRelationship;//紧急联系关系
			document.getElementById('continueFlg').value = data.continueFlg;//非续投/续投
			document.getElementById('spreadType').value = data.spreadType;//推广渠道
			document.getElementById('bank').value = data.bank;//汇入银行
			document.getElementById('branch').value = data.branch;//银行支行名称
			document.getElementById('cardName').value = data.cardName;//开户人姓名
			document.getElementById('cardNo').value = data.cardNo;//账号
			document.getElementById('cardAll').value = data.cardAddr;//开卡省
			document.getElementById('cardLine').value = data.cardLine;//银行行号
			document.getElementById('inBank').value = data.inBank;//回款银行
			document.getElementById('inBranch').value = data.inBranch;//银行支行名称
			document.getElementById('inCardName').value = data.inCardName;//开户人姓名
			document.getElementById('inCardNo').value = data.inCardNo;//账号
			document.getElementById('inCardAll').value = data.inCardAddr;//开卡省
			/*$("#inCardProvince").empty(); //开卡省
			 $("#inCardProvince").append("<option value=0 selected>请选择开卡省</option>"); 
			for(var i = 0;i<data.length;i++){
				var item = data[i];
				 $("#inCardProvince").append("<option  value=" + item.inCardProvince + ">" + item.inCardProvince + "</option>");
			}*/
			document.getElementById('insUser').value = data.insUser;//插入更新者
			document.getElementById('insDate').value = data.insDate;//插入更时间
			document.getElementById('updUser').value = data.updUser;//更新者
			document.getElementById('updDate').value = data.updDate;//更新时间
			document.getElementById('managerNo').value = user.name;//审批者
			document.getElementById('managerStatus').value = data.managerStatus;//审批状态
			document.getElementById('remark').value = data.remark;//备注
		}
	};
	var faild = function(error){
		alert(error);
	};
	/*if(contract == ""){
		$("#contract").attr("disabled",false);
		$("#addBtn").css("display","block");
		$("#editBtn").css("display","none");
		$(".addHidden").css("display","none");
	}else{
		$("#addBtn").css("display","none");
		$("#editBtn").css("display","block");
		$(".addHidden").css("display","block");
	}*/
	ajaxPost('/selectByContract',{"typeId":user.typeId,"contract":contract},false,success,faild);
}
function addData(){ //新增
	var contract = document.getElementById('contract').value;//合同编号 
	if(contract == ""){alert("请输入正确合同编号");return false;}
	var type = document.getElementById("type").value; //产品名称
	if(type == ""){alert("请选择正确产品名称");return false;}
	var money = document.getElementById('money').value;//出借金额
	if(money == ""){alert("请输入正确出借金额");return false;}
	var zbRatio = document.getElementById('zbRatio').value; //折标系数
	if(zbRatio == ""){alert("请输入正确折标系数");return false;}
	var jxAchievement = document.getElementById('jxAchievement').value;//绩效业绩 = 出借金额 * 折标系数
	if(jxAchievement == ""){alert("请输入正确绩效业绩");return false;}
	var lcId = document.getElementById('lcId').value; //客户编号
	if(lcId == ""){alert("请输入正确客户编号");return false;}
	var lcManager = document.getElementById('lcManager').value; //客户经理
	/*var tid = document.getElementById('tid').value;//团队名称*/
	var tManager = document.getElementById('tManager').value; //团队经理
	var yid = document.getElementById('yid').value;//营业部名称
	var yybManager = document.getElementById('yybManager').value;//营业部经理
	var fid = document.getElementById('fid').value;//分公司名称
	var fgsManager = document.getElementById('fgsManager').value;//分公司经理
	var did = document.getElementById('did').value;//大区名称
	var dqManager = document.getElementById('dqManager').value;//大区经理
	var sid = document.getElementById('sid').value;//事业部名称
	var sybManager = document.getElementById('sybManager').value;//事业部经理
	var sybAssistantManager = document.getElementById('sybAssistantManager').value;//事业部副经理
	var dqAssistantManager = document.getElementById('dqAssistantManager').value;//大区副经理
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
	if(endDate == ""){alert("到期日不能为空,请选择产品自动计算!");return false;}
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
	var cardAll = document.getElementById('cardAll').value;//开卡省
	if(cardAll == ""){alert("请输入正确开卡省市");return false;}
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
	var inCardAll = document.getElementById('inCardAll').value;//开卡省
	if(inCardAll == ""){alert("请输入正确开卡省市");return false;}
	/*
	var certs = document.getElementsByName("certs[]"); //文件
	var cert = [];
	for(var i=0;i<certs.length;i++){
		cert[i] = certs[i].value;
	}
	var file = JSON.stringify({cert:cert});*/
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
	var user = JSON.parse(sessionStorage.user);
	ajaxPost('/insertSelective',{"contract":contract,"userId": user.userId,"type":type,"money":money,"zbRatio":zbRatio,"jxAchievement":jxAchievement,
								 "lcId":lcId,"lcManager":lcManager,
								 "tmanager":tManager,"yyb":yid,"yybManager":yybManager,"fgs":fid,"fgsManager":fgsManager,"dq":did,
								 "dqManager":dqManager,"syb":sid,"sybManager":sybManager,"sybAssistantManager":sybAssistantManager,"dqAssistantManager":dqAssistantManager,"periods":periods,"rate":rate,
								 "interestAll":interestAll,"interestMonth":interestMonth,"paymentDate":paymentDate,"endDate":endDate,
								 "statementDate":statementDate,"startDate":startDate,"surplusDate":surplusDate,"status":status,
								 "posNo":posNo,"tenderName":tenderName,"tel":tel,"idType":idType,"idNo":idNo,
								 "borrowAddress":borrowAddress,"contactName":contactName,"contactTel":contactTel,
								 "contactRelationship":contactRelationship,"continueFlg":continueFlg,"spreadType":spreadType,"bank":bank,
								 "branch":branch,"cardName":cardName,"cardNo":cardNo,"cardAddr":cardAll,
								 "cardLine":cardLine,"inBank":inBank,"inBranch":inBranch,"inCardName":inCardName,"inCardNo":inCardNo,
								 "inCardAddr":inCardAll},true,success,faild);
}
function VerifierData(){//审批提交
	var type = document.getElementById("type").value; //产品名称
	if(type == ""){alert("请选择产品名称");return false;}
	var zbRatio = document.getElementById('zbRatio').value; //折标系数
	if(zbRatio == ""){alert("折标系数不能为空");return false;}
	var money = document.getElementById('money').value;//出借金额
	if(money == ""){alert("请输入正确出借金额");return false;}
	var jxAchievement = document.getElementById('jxAchievement').value;//绩效业绩 = 出借金额 * 折标系数
	var lcId = document.getElementById('lcId').value; //客户编号
	if(lcId == ""){alert("请输入正确客户编号");return false;}
	var lcManager = document.getElementById('lcManager').value; //客户经理
	var tManager = document.getElementById('tManager').value; //团队经理
	var yid = document.getElementById('yid').value;//营业部名称
	var yybManager = document.getElementById('yybManager').value;//营业部经理
	var fid = document.getElementById('fid').value;//分公司名称
	var fgsManager = document.getElementById('fgsManager').value;//分公司经理
	var did = document.getElementById('did').value;//大区名称
	var dqManager = document.getElementById('dqManager').value;//大区经理
	var sid = document.getElementById('sid').value;//事业部名称
	var sybManager = document.getElementById('sybManager').value;//事业部经理
	var sybAssistantManager = document.getElementById('sybAssistantManager').value;//事业部副经理
	var dqAssistantManager = document.getElementById('dqAssistantManager').value;//大区副经理
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
	var cardAll = document.getElementById('cardAll').value;//开卡省
	if(cardAll == ""){alert("请输入正确开卡省市");return false;}
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
	var inCardAll = document.getElementById('inCardAll').value;//开卡省
	if(inCardAll == ""){alert("请输入正确开卡省市");return false;}
	var insUser = document.getElementById('insUser').value;//插入更新者
	var insDate = document.getElementById('insDate').value;//插入更时间
	var updUser = document.getElementById('updUser').value;//更新者
	var updDate = document.getElementById('updDate').value;//更新时间
	
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
	var user = JSON.parse(sessionStorage.user);
	var contract = $_GET['contract'];
	ajaxPost('/approve',{"userId":user.userId,"contract":contract,"type":type,"zbRatio":zbRatio,"money":money,"jxAchievement":jxAchievement,
		"lcId":lcId,"lcManager":lcManager,"tmanager":tManager,"yyb":yid,"yybManager":yybManager,
		"fgs":fid,"fgsManager":fgsManager,"dq":did,"dqManager":dqManager,
		"syb":sid,"sybManager":sybManager,"sybAssistantManager":sybAssistantManager,"dqAssistantManager":dqAssistantManager,"periods":periods,"rate":rate,
		 "interestAll":interestAll,"interestMonth":interestMonth,"paymentDate":paymentDate,
		 "endDate":endDate,"statementDate":statementDate,"startDate":startDate,
		 "surplusDate":surplusDate,"status":status,"posNo":posNo,"tenderName":tenderName,"tel":tel,"idType":idType,"idNo":idNo,
		 "borrowAddress":borrowAddress,"contactName":contactName,"contactTel":contactTel,
		 "contactRelationship":contactRelationship,"continueFlg":continueFlg,"spreadType":spreadType,"bank":bank,
		 "branch":branch,"cardName":cardName,"cardNo":cardNo,"cardAddr":cardAll,
		 "cardLine":cardLine,"inBank":inBank,"inBranch":inBranch,"inCardName":inCardName,"inCardNo":inCardNo,
		 "inCardAddr":inCardAll,"insUser":insUser,"insDate":insDate,
		 "updUser":updUser,"updDate":updDate,"managerNo":user.name,"managerStatus":managerStatus,"remark":remark},false,success,faild);
} 
