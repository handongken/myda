$("#sid").change(function(){      
	document.getElementById('did').innerHTML = '<option value="">请选择</option>';
	document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0 || $(this).val() == '0001' || $(this).val() == '0002'){
		document.getElementById('did').innerHTML = '<option value="">请选择</option>';
		document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryDq();
	}
});
$("#did").change(function(){
	document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0){
		document.getElementById('fid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryFid();
	}
});
$("#fid").change(function(){
	document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
	document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0){
		document.getElementById('yid').innerHTML = '<option value="">请选择</option>';
		document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryYid();
	}
});
$("#yid").change(function(){ //团队
	document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	if($(this).val() == 0){
		document.getElementById('tid').innerHTML = '<option value="">请选择</option>';
	}else{
		queryTid();
	}
});