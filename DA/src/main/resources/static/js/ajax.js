var csUrl = ''; //http://127.0.0.1:8080/login?userId=1&password=1   http://192.168.1.129:8080
var ajaxPost = function(url,data,success,faild){
	$.ajax({
		url:url,
		type:"POST", //POST
		data:data,
		success:function(res){
			if(res.name != ""){
				success(res);
			}else{
				faild(res.message);
			}
		},
		error:function(res){
			alert('请求失败，请稍后再试');
		},
		dataType:"json"
	});
}; 
var ajaxDownExcel = function(url,data,success,faild){//表格下载
	$.ajax({
		url:url,
		type:"POST", //POST
		data:data,
		success:function(res){
			if(res == "success"){
           	 alert('成功下载,存入D盘!');
            }else{
				alert('下载不成功！');
			}
		},
		error:function(res){
			alert('请求失败，请稍后再试');
		},
		dataType:"text"
	});
};

