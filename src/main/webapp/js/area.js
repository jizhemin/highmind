
let domainid = 2;
var spotsList = {};

var areaVm = new Vue({
	el:"#areaVm",
	data() {
		return {
			areaList: [],
			defaultProps: {
				children: 'spot',
				label: 'areaname'
			},
			areaSpotsList:[],
			_area:{}
		};
    },
    created:function(){
    	var _data = this;
    	
    	$.ajax({
	        type: "get",
	        url: "../areas",
	        headers: {'domainid': domainid},
	        success: function (data) {
	        	if(data.status == 1){		        		
	        		_data.areaList = data.data
	        	}
	        	else{
	        		console.log(data.error);
	        	}	
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
    },   
    methods:{
    	areaNodeClick(val){   
    		
    		this._area = val;
    		
    		if(val.spots.length > 0){
    			this.areaSpotsList = val.spots;
    		}else{
    			this.areaSpotsList = []
    		}
    		
    	},
    	areaDelete(){
    		var _this = this;
    		
    		if(this.isArea()){
    			if(confirm("确认删除该区域？") == true){
	    			$.ajax({
	        			type: "delete",
	        	        url: "../areas/"+ this._area.id,
	        	        headers: {'domainid': domainid},
	        	        success:function(data){
	        	        	if(data.status == 1){
	        	        		areaVm.areaRefresh();
	        	        		areaVm.$notify.success({
	  	  	        	          title: '删除成功',
	  	  	        	          message: '区域信息删除成功!',
	        	        		});
	        	        	}else{
	        	        		areaVm.$notify.error({
	  	  	        	          title: '删除失败',
	  	  	        	          message: '区域信息删除失败!',
	        	        		});
	        	        	} 	
	        	        },
	        	        error: function (message) {
	        	            console.log(message);
	        	        }  
	        		})  
	    		}	
    		}
    		
    	},
    	areaEditInit(){
    		var _this = this;
    		var areaEdit = $("#areaEditForm").serializeArray();
    		if(this.isArea()){  			
    			$.each(areaEdit,function(i,f){ 
        			if(f.name == "arearemark"){
        				$("#areaEditForm textarea[name='" + f.name + "']").val(_this._area[f.name]);
        			}else{
        				$("#areaEditForm input[name='" + f.name + "']").val(_this._area[f.name]);
        			}
        		});
    			$("#areaEdit").modal('show');	
    		}
    	},
    	getAreaSpots(){
    		var _this = this;
    		var str="";
    		
    		if(this.isArea()){  			
    			
    			$("#areaSpots_Init").html("");
        		$("input[name='areaSpotsId']").val(_this._area.id);
        		
        		$.each(spotsList,function(i,v){	
        			str += "<label class='fancy-checkbox' ><input type='checkbox' name='areaSpots' value="+ v.id +"><span>"
        			+ v.spotname
        			+ "</span></label>"			
        		});	
        		
        		$("#areaSpots_Init").html(str);
        		
        		console.log(_this.areaSpotsList)
        		$.each(spotsList,function(i,v){	
        			$.each(_this.areaSpotsList,function(j,w){
        				if(w.id == v.id){
        					$("input[type=checkbox][name='areaSpots'][value='"+w.id+"']").prop('checked', true);
        				}
        			})			
        		});
        		
    			$("#areaSpotsEdit").modal('show');	
    		}
    	},
    	areaRefresh(){
    		var _this = this;
    		
    		$.ajax({
    	        type: "get",
    	        url: "../areas",
    	        headers: {'domainid': domainid},
    	        success: function (data) {
    	        	if(data.status == 1){		        		
    	        		_this.areaList = data.data    	        		
    	        	}
    	        	else{
    	        		console.log(data.error);
    	        	}	
    	        },
    	        error: function (message) {
    	            console.log(message);
    	        }
    	    });
    	},
    	isArea(){ 
    		var _this = this;
    		if($.isEmptyObject(_this._area)){
    			alert("请选择区域")
    			return false
    		}else{
    			return true
    		}
    	}
    }
})

function createArea(){
	
	var areaCreate = $("#areaCreateForm").serializeArray();
	var area = {}
	area["domainid"] = domainid;	
	
	$.each(areaCreate,function(i,f){
		area[f.name] = f.value
	});

	$.ajax({
        type: "POST",
        url: "../areas",
        data: area,	
        success: function (data) {
        	if(data.status == 1){ 
    			areaVm.$notify.success({
        	          title: '添加成功',
        	          message: '区域信息添加成功!',
        		});
    			successStep("#areaCreate");
    			$("#areaCreateForm :input").each(function () {
    		        $(this).val("");
    			}); 
    			$("#areaCreateForm textarea[name='arearemarkArea']").val("")
    		}else{
    			areaVm.$notify.error({
        	          title: '添加失败',
        	          message: '区域信息添加失败!',
        		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });	
	
}

function editArea(){
	
	var areaEdit = $("#areaEditForm").serializeArray();
	var _area = {}
	_area["domainid"] = domainid;	
	
	$.each(areaEdit,function(i,f){
		_area[f.name] = f.value
	});

	$.ajax({
        type: "put",
        url: "../areas",
        data: _area,	
        success: function (data) {
        	if(data.status == 1){ 
    			areaVm.$notify.success({
        	          title: '编辑成功',
        	          message: '区域信息编辑成功!',
        		});
    			successStep("#areaEdit");
    		}else{
    			areaVm.$notify.error({
        	          title: '编辑失败',
        	          message: '区域信息编辑失败!',
        		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });	
}
/*
function nameSplit(str){
	var strf;
	strf = str.split("_");
	return strf[0]
}
*/
function successStep(doc){
	areaVm.areaRefresh();
	$(doc).modal('hide');	
}

function areaSpotsInit(){
	$.ajax({
		type: "get",
        url: "../spot",
        headers:{"domainid":domainid},
        success:function(data){
        	if(data.status == 1){
        		spotsList = data.data;
        	}else{
        		console.log(data.error);
        	} 	
        },
        error: function (message) {
            console.log(message);
        }  
	})
}

function editAreaSpots(){
	
	var areaSpots = [];
	var areaSpotsChecked = $("input[name='areaSpots']:checked");
		
	$.each(areaSpotsChecked,function(i,v){	
		areaSpots.push({
			"domainid":domainid,
			"area_id":$("input[name='areaSpotsId']").val(),
			"spot_id":this.value
		})			
	});

	$.ajax({
		type: "post",
        url: "../areaspot",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(areaSpots),
        success:function(data){
        	if(data.status == 1){
        		areaVm.$notify.success({
        	          title: '景点分配成功',
        	          message: '区域景点分配成功!',
        		});
        		areaVm.areaRefresh();
        		$('#areaSpotsEdit').modal('hide');
        	}else{
        		areaVm.$notify.error({
		  	          title: '景点分配失败',
		  	          message: '区域景点分配失败!',
		  		});
        	} 	
        },
        error: function (message) {
            console.log(message);
        }  
	})
	
}

$(document).ready(function() {
	
	/*页面初始化*/
	navbar();
	areaSpotsInit();
		
	/*数据初始化*/

	/*操作*/
	$("#createAreaBtn").click(function(){
		createArea();
	})
	$("#editAreaBtn").click(function(){
		editArea();
	})
	$("#editAreaSpotsBtn").click(function(){
		editAreaSpots();
	})
	

});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}




