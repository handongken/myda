/*if(!localStorage.user){
	location.href = "login.html";
}else{
	loading();
}*/

function loading(){
	if(!document.getElementById('loadingDiv')){
		var div = document.createElement("div");
		div.id = 'loadingDiv';
		div.innerHTML = '<div class="pop-body"><img src="images/loading.gif" alt="loading"/>页面加载中，请等待...</div>';
		document.body.appendChild(div);
	}
}

function delLoading(){
	var loadingMask = document.getElementById('loadingDiv');
	//alert(loadingMask.innerHTML);
    loadingMask.parentNode.removeChild(loadingMask);
}
