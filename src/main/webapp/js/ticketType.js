
var table;
var domainid = 1;

function setTicketTypeTable(){
	table = $('#ticketTypeTable').DataTable({
		
		ajax: {
			url:'../tickettypes',
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
            	data: "typename" ,
            	title: "票种名称"
            },{ 
            	data: "equipvoice" ,
            	title: "语音段"
            },{ 
            	data: "remark" ,
            	title: "备注"
            },{
            	title:"操作",
            	orderable:false,
            }
        ], 
        "columnDefs" : [{
        	"targets" : 4,
        	"data" : null,
        	"render" : function(data, type, row) {
        		var id_ = '"' + row.id + '"';
        		var row_ = JSON.stringify(row);
	        	var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#ticketTypeInfo' onclick='getTicketType("+ row_ + ")' title='票种详细信息查看'><i class='lnr lnr-magnifier'></i></button>" 
	        		 + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#ticketTypeEdit' onclick='editTicketTypeInit("+ row_ + ")' title='票种信息编辑'><i class='lnr lnr-pencil'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delTicketType("+ id_ + ")' title='删除票种'><i class='lnr lnr-trash'></i></button>"
        			 
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
function createTicketType(){
	var domainid = 2;
	
	let dataParam = $("#ticketTypeCreateForm").serializeArray();
	dataParam.push({"name":"domainid","value":domainid});  
	$.ajax({
        type: "POST",
        url: "../tickettypes",
        data: dataParam,
        success: function (data) {
        	if(data.status == 1){
    			ticketTypeVm.$notify.success({
	      	          title: '添加成功',
	      	          message: '票种信息添加成功!',
	      		});
    			table.ajax.reload();
    			$('#ticketTypeCreate').modal('hide');
    			$("#ticketTypeCreate :input").each(function () {
    		        $(this).val("");
    			}); 
    			$("textarea[name='remark']").val("")
    		}else{
    			ticketTypeVm.$notify.error({
	      	          title: '添加失败',
	      	          message: '票种信息添加失败!请重试',
	      		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function getTicketType(_ticketType){
	
	var str="";
	str = "<div class='profile-info'><h4 class='heading'>票种信息</h4><ul class='list-unstyled list-justify'><li>票种名称 <span>" 
		+ _ticketType.typename 
		+ "</span></li><li>语音段 <span>" 
	if(_ticketType.equipvoice == "" || _ticketType.equipvoice == null ){
		str += ""
	}else{
		str += _ticketType.equipvoice
	}
	str += "</span></li><li>备注 <span>"
	if(_ticketType.remark == "" || _ticketType.remark == null ){
		str += ""
	}else{
		str += _ticketType.remark
	}
	str	+= "</span></li></ul></div>" 
	
	
	
	$("#ticketTypeInfo_detail").html(str);	
}
function delTicketType(ticketTypeID){
	if(confirm("确认删除？") == true){
		$.ajax({
	        type: "delete",
	        url: "../tickettypes/"+ticketTypeID,	        
			contentType:'json',			
	        success: function (data) {
	        	if(data.status == 1){
	    			ticketTypeVm.$notify.success({
		      	          title: '删除成功',
		      	          message: '票种信息删除成功!',
		      		});
	    			table.ajax.reload();
	    		}else{
	    			ticketTypeVm.$notify.error({
		      	          title: '删除失败',
		      	          message: '票种信息删除失败!请重试',
		      		});
	    		}
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
	}
}

function editTicketTypeInit(_ticketType){
	$("#idEdit").val(_ticketType.id);
	$("#typenameEdit").val(_ticketType.typename);
	$("#equipvoiceEdit").val(_ticketType.equipvoice);
	$("#markEdit").val(_ticketType.remark);
	
}


function editTicketType(){
	
	let dataParam = $("#ticketTypeEditForm").serializeArray();
	dataParam.push({"name":"domainid","value":domainid});
	
	$.ajax({
        type: "put",
        url: "../tickettypes",
        data: dataParam,
        success: function (data) { 
        	if(data.status == 1){
    			ticketTypeVm.$notify.success({
	      	          title: '编辑成功',
	      	          message: '票种信息编辑成功!',
	      		});
    			table.ajax.reload();
    			$('#ticketTypeEdit').modal('hide');
    					
    		}else{
    			ticketTypeVm.$notify.error({
	      	          title: '编辑失败',
	      	          message: '票种信息编辑失败!请重试',
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
	setTicketTypeTable();
  
	/*操作*/
	$("#createTicketTypeBtn").click(function(){
		createTicketType();
	})
	$("#editTicketTypeBtn").click(function(){
		editTicketType();
	})
	
	
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}

var ticketTypeVm = new Vue({
	
	el:"#ticketTypeVm",
	
})







