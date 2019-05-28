
var table;

var domainid = 2;

function setSpotTable(){
	table = $('#spotTable').DataTable({
		
		ajax: {
			url:'../spot',
			dataSrc: 'data',	
			headers: {
				"domainid":domainid
			}
		},
		columns: [
			{
				"data" : null, 
				"title" : "编号",
				"render" : function(data, type, full, meta){  
					return meta.row + 1 + meta.settings._iDisplayStart;  
				}
			},{
            	data: "spotname" ,
            	title: "景点名称"
            },{ 
            	data: "limited" ,
            	title: "是否限制人数",
            },{ 
            	data: "qty" ,
            	title: "人数上限"
            },{ 
            	data: "autoleave" ,
            	title: "是否自动出园",
            },{
            	data: "autoleavetime",
            	title: "自动出园时间"
            	
            },{
            	title:"操作",
            	orderable:false,
            }
        ], 
        "columnDefs" : [{
        	"targets" : 6,
        	"data" : null,
        	"render" : function(data, type, row) {
        		var id_ = '"' + row.id + '"';
        		var row_ = JSON.stringify(row);
	        	var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#spotInfo' onclick='getSpot("+ row_ + ")' title='景点详细信息查看'><i class='lnr lnr-magnifier'></i></button>" 
	        		 + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#spotEdit' onclick='editSpotInit("+ row_ + ")' title='景点信息编辑'><i class='lnr lnr-pencil'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delSpot("+ id_ + ")' title='删除景点'><i class='lnr lnr-trash'></i></button>"
        			 
	        	return html;
        	}
        },{
        	"targets" : 2,
        	"data" : "limited",
        	"render" : function(data, type, row) {
        		var html = ""
        		if(row.limited == true){
        			
        			html = "<span class='label label-success'><i class='fa fa-check'></i></span>"
        			
        		}else{
        			html = "<span class='label label-default'><i class='fa fa-close'></i></span>"
        		}
        		
        		return html;
        	}
        },{
        	"targets" : 4,
        	"data" : "autoleave",
        	"render" : function(data, type, row) {
        		var html = ""
        		if(row.autoleave == true){
        			
        			html = "<span class='label label-success'><i class='fa fa-check'></i></span>"
        			
        		}else{
        			html = "<span class='label label-default'><i class='fa fa-close'></i></span>"
        		}
        		
        		return html;
        	}
        }],
		
        "order": [[0, 'asc']],
		"iDisplayLength":10,
        "bAutoWidth" : true,
        "oLanguage": {         
        	"sProcessing" : "正在查询中，请稍后...",               
        	"sLengthMenu" : "显示 _MENU_ 条",               
        	"sZeroRecords" : "没有您要搜索的内容",               
        	"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",               
        	"sInfoEmpty" : "记录数为0",               
        	"sInfoFiltered" : "(全部记录数 _MAX_ 条)",               
        	"sInfoPostFix" : "",               
        	"sSearch" : "检索内容：",               
        	"sUrl" : "",               
        	"oPaginate": {                   
        		"sFirst" : "第一页",                   
        		"sPrevious" : "上一页",                   
        		"sNext" : "下一页",                    
        		"sLast" : "最后一页"               
        	}
        },
        "searching": true,
        "lengthChange": true,     
	})
}

function createSpot(){
	var spot = {};

	domainid = 1;
	
	spot["domainid"] = domainid;	
	spot["spotname"] = $("input[name='spotnameArea']").val();
	spot["spotremark"] = $("textarea[name='spotremarkArea']").val();
	
	if($("input[name='isLimited']").prop('checked')) {
		spot["limited"] = "1";
		spot["qty"] = $("input[name='qtyArea']").val();
	}else{
		spot["limited"] = "0";
	}
	if($("input[name='isAutoleave']").prop('checked')) {
		spot["autoleave"] = "1";
		spot["autoleavetime"]= $("input[name='autoleavetimeArea']").val();
	}else{
		spot["autoleave"] = "0";
	}
		
	console.log(spot);
	
	$.ajax({
        type: "POST",
        url: "../spot",
        data: spot,	
        success: function (data) {
        	if(data.status == 1){
    			spotVm.$notify.success({
	      	          title: '添加成功',
	      	          message: '景点信息添加成功!',
	      		});
    			table.ajax.reload();
    			$('#spotCreate').modal('hide');
    			$("#spotCreate :input").each(function () {
    		        $(this).val("");
    			}); 
    			 $("textarea[name='spotremarkArea']").val("")
    		}else{
    			spotVm.$notify.error({
	      	          title: '添加失败',
	      	          message: '景点信息添加失败!',
	      		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });
}

function getSpot(_spot){
	
	var str="";
	str = "<div class='profile-info'><h4 class='heading'>景点信息</h4><ul class='list-unstyled list-justify'><li>景区名称 <span>" 
		+ _spot.spotname 
		+ "</span></li><li>备注 <span>" 
	if(_spot.spotremark == "" || _spot.spotremark == null ){
		str += "暂无"
	}else{
		str += _spot.spotremark
	}
	
	str += "</span></li></ul></div>" 
		+ "<div class='profile-info'><h4 class='heading'>设置信息</h4><ul class='list-unstyled list-justify'><li>是否限制人数 <span>" 
		
	if( _spot.limited == true){
		str += "是"
			+ "</span></li><li>人数上限 <span>" 
			+ _spot.qty
	}else{
		str += "否";
	}	
	
	str += "</span></li><li>是否自动出园 <span>" 
	
	if( _spot.autoleave == true){
		str += "是"
			+ "</span></li><li>自动出园时间 <span>" 
			+ _spot.autoleavetime
	}else{
		str += "否";
	}
	
	str	+= "</span></li></ul></div>" 
	
	
	
	$("#spotInfo_detail").html(str);	
}

function delSpot(spotID){
	if(confirm("确认删除该景点？") == true){
		$.ajax({
	        type: "delete",
	        url: "../spot/"+spotID,	        
			contentType:'json',			
	        success: function (data) {
	        	if(data.status == 1){
	    			spotVm.$notify.success({
		      	          title: '删除成功',
		      	          message: '景点信息删除成功!',
		      		});
	    			table.ajax.reload();
	    		}else{
	    			spotVm.$notify.error({
		      	          title: '删除失败',
		      	          message: '景点信息删除失败!',
		      		});
	    		}
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
	}
}

function editSpotInit(_spot){
	
	$("input[name='spotIdEdit']").val(_spot.id);
	$("input[name='spotnameEdit']").val(_spot.spotname);
	$("textarea[name='spotremarkEdit']").val(_spot.spotremark);
	
	if(_spot.limited == true) {		
		$("input[name='isLimitedEdit']").prop('checked',true);
		$("input[name='qtyEdit']").val(_spot.qty);		
		$("#qtyEdit_div").show();	
	}else{
		$("input[name='isLimitedEdit']").prop('checked',false);
		$("input[name='qtyEdit']").val(_spot.qty);		
		$("#qtyEdit_div").hide();
	}
	
	if(_spot.autoleave == true) {		
		$("input[name='isAutoleaveEdit']").prop('checked',true);
		$("input[name='autoleavetimeEdit']").val(_spot.autoleavetime);		
		$("#autoleavetimeEdit_div").show();	
	}else{
		$("input[name='isAutoleaveEdit']").prop('checked',false);
		$("input[name='autoleavetimeEdit']").val(_spot.autoleavetime);		
		$("#autoleavetimeEdit_div").hide();	
	}
}

function editSpot(){
	var _spot = {}
	
	_spot["id"] = $("input[name='spotIdEdit']").val();
	_spot["spotname"] = $("input[name='spotnameEdit']").val();
	_spot["spotremark"] = $("textarea[name='spotremarkEdit']").val();

	if($("input[name='isLimitedEdit']").prop('checked')) {
		_spot["limited"] = "1";
		_spot["qty"] = $("input[name='qtyEdit']").val();
	}else{
		_spot["limited"] = "0";
	}
	
	if($("input[name='isAutoleaveEdit']").prop('checked')) {
		_spot["autoleave"] = "1";
		_spot["autoleavetime"] = $("input[name='autoleavetimeEdit']").val();
	}else{
		_spot["autoleave"] = "0";
	}
	
	$.ajax({
        type: "post",
        url: "../spot",
        data: _spot,
        success: function (data) { 
        	if(data.status == 1){
    			spotVm.$notify.success({
	      	          title: '编辑成功',
	      	          message: '景点信息编辑成功!',
	      		});
    			table.ajax.reload();
    			$('#spotEdit').modal('hide');
    					
    		}else{
    			spotVm.$notify.error({
	      	          title: '编辑成功',
	      	          message: '景点信息编辑失败!',
	      		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });
}


$(document).ready(function() {
	/*页面初始化*/
	navbar();
		
	/*数据初始化*/
	setSpotTable();
  
	/*操作*/
	$("#createSpotBtn").click(function(){
		createSpot();
	})
	$("#editSpotBtn").click(function(){
		editSpot();
	})
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}

var spotVm = new Vue({
	
	el:"#spotVm",
	
})





