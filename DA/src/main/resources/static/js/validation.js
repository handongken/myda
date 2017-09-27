if(contract == ""){alert("请输入正确合同编号");return false;}
var moneyReg = /([0-9]+[,]?)+$/;
if(money == "" || !moneyReg.test(money)){alert("请输入正确出借金额(数字)");return false;}
if(jxAchievement == "" || !moneyReg.test(jxAchievement)){alert("请输入正确绩效业绩(数字)");return false;}
if(lcManager == ""){alert("请输入正确理财经理");return false;}