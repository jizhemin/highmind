
var nemuList = "";
var domainid = "";
var token = "";

function getBaseInfo(){
	
	$.ajax({
        type: "get",
        url: "../getInfo",
        headers: {'domainid': domainid},
        data:{"token": token},
        success: function (data) {
        	if(data.status == 1){	
        		$("#userName").html(data.data.name)
        		$("#userLoginId").html(data.data.loginId) 
        		sessionStorage.setItem("domainName",data.data.domain.domain_name);
        	}
        	else{
        		console.log(data.error);
        		alert("用户信息已失效，请重新登录！")
        		window.location.href = "login.html";		
        	}	
        },
        error: function (message) {
        	console.log(message);
        	alert("用户信息已失效，请重新登录！")
    		window.location.href = "login.html";		
        }
    });
}


function getSidebarList(){
	var str = "";
	$.ajax({
        type: "get",
        url: "../menurecursion",
        headers: {'domainid': domainid},
        data:{"token": token},
        success: function (data) {
        	if(data.status == 1){	
        		
        		$.each(data.data, function(i,v){  
        			
        			
        			var urlStr = v.url.split("*");        			
					if(v.children.length > 0){
						
						str += "<li><a href='#" 
							+ urlStr[0] 
							+ "' data-toggle='collapse' class='collapsed ' aria-expanded='false'><i class='lnr lnr-"
							+ urlStr[1]  
							+ "'></i><span>" 
							+ v.name 
							+ "</span><i class='icon-submenu lnr lnr-chevron-left'></i></a><div id='"
							+ urlStr[0] 
							+ "' class='collapse' aria-expanded='false' style='height: 0px;'><ul class='nav'>"

							$.each(v.children, function(j,m){
								
								str += "<li><a href='"
									+ m.url
									+ "' class=''>" 
									+ m.name
									+ "</a></li>"
							})	
						str +="</ul></div></li>"      				
        			}else{
        				str += "<li><a href='"
            				+ urlStr[0]
            				+ "' class=''><i class='lnr lnr-"
							+ urlStr[1]  
							+ "'></i><span>"
            				+ v.name
            				+ "</span></a></li>"
        			}	
        		})
        		$("#sideNavbar").html(str);
        	}
        	else{
        		console.log(data.error);    		
        	}	
        },
        error: function (message) {
        	console.log(message);        	
        }
    });
}


function editPassword(){
	
	var psw = $("input[name='psdEdit']").val();
	
	$.ajax({
        type: "get",
        url: "../getInfo",
        headers: {'domainid': domainid},
        data:{"token": token},
        success: function (data) {
        	
        	if(data.status == 1){
        		var _data = data.data;
           		if(psw == _data.password){
           			pswChange(_data.id);
           		}else{
           			alert("原密码输入错误！")
           		}       		
        	}
        	else{
        		alert("修改密码失败，请重试！")
        		console.log(data.error);       		
        	}	
        },
        error: function (message) {
        	alert("修改密码失败，请重试！")
        	console.log(message);
        }
    });	
}


function pswChange(userId){
	
	var _user = {}
	_user["id"] = userId;
	_user["password"] = $("input[name='psdNewEdit']").val();

	$.ajax({
        type: "put",
        url: "../employees",
        headers: {'domainid': domainid},
        data: _user,
        success: function (data) {
        	if(data.status == 1){		        		
        		alert("修改成功！");
        		$('#passwordEdit').modal('hide');
        		$("input[name='psdEdit']").val("");
        		$("input[name='psdNewEdit']").val("");
        	}
        	else{
        		alert("修改失败，请重试！")
        		console.log(data.error);
        	}	
        },
        error: function (message) {
        	alert("修改失败，请重试");
        	console.log(message);
        }
    });
}

function loginOut(){
	if(confirm("是否退出当前账号？") == true){
		sessionStorage.clear();
		window.location.href = "login.html";	
	}else{
		
	}
}


$().ready(function(){
	
	/*页面初始化*/
	getMenuHidden();
	
	/*if(sessionStorage.getItem("token") != null){
		
		domainid = sessionStorage.domainid;
		token = sessionStorage.token;
		
		页面初始化
		getMenuHidden();

		数据初始化
		getBaseInfo();
		getSidebarList();
		
		操作
		$("#editPassowordBtn").click(function(){
			editPassword();
		})
		
	}else{
		alert("用户信息已失效，请重新登录！")
		window.location.href = "login.html";		
	}*/
})
	
/*控制左侧菜单显示隐藏*/
function getMenuHidden(){
	$("#menubtn").click(function(){
		var _width = $(document.body).outerWidth(true);
		if(_width > 1024){
			if($("body").hasClass("layout-fullwidth")){
				$("body").removeClass("layout-fullwidth");
				$("#menubtn i").removeClass("lnr-arrow-right-circle").addClass("lnr-arrow-left-circle");
			}else{
				$("body").addClass("layout-fullwidth");
				$("#menubtn i").removeClass("lnr-arrow-left-circle").addClass("lnr-arrow-right-circle");
			}
		}else{
			if($("body").hasClass("layout-fullwidth offcanvas-active")){
				$("body").removeClass("layout-fullwidth offcanvas-active");
				$("#menubtn i").removeClass("lnr-arrow-right-circle").addClass("lnr-arrow-left-circle");
				
			}else{
				$("body").addClass("layout-fullwidth offcanvas-active");
				$("#menubtn i").removeClass("lnr-arrow-left-circle").addClass("lnr-arrow-right-circle");
			}
		}
	})
}

/*控制菜单按钮高亮*/
function getMenuActive(){
	
	
}












