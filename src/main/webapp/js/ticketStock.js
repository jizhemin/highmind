var table;
var domainid = 2;

function setTicketStockTable(){
	table = $('#ticketStockTable').DataTable({
		
		ajax: {
			url:'../ticketstocks',
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
            	data: "stockname" ,
            	title: "库存名称"
            },{
            	data: "daystock" ,
            	title: "日库存"
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
	        	var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#ticketStockInfo' onclick='getTicketStock("+ row_ + ")' title='票仓详细信息查看'><i class='lnr lnr-magnifier'></i></button>" 
	        		 + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#ticketStockEdit' onclick='editTicketStockInit("+ row_ + ")' title='票仓信息编辑'><i class='lnr lnr-pencil'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delTicketStock("+ id_ + ")' title='删除票仓'><i class='lnr lnr-trash'></i></button>"
        			 
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
	var domainid = 2;
	
	let dataParam = $("#ticketStockCreateForm").serializeArray();
	dataParam.push({"name":"domainid","value":domainid});  
	console.log(dataParam);
	$.ajax({
        type: "POST",
        url: "../ticketstocks",
        data: dataParam,
        success: function (data) {
        	if(data.status == 1){ 
        		ticketStockVm.$notify.success({
	      	          title: '添加成功',
	      	          message: '票仓信息添加成功!',
	      		});
    			table.ajax.reload();
    			$('#ticketStockCreate').modal('hide');
    			$("#ticketStockCreate :input").each(function () {
    		        $(this).val("");
    			}); 
    			 $("textarea[name='remark']").val("")
    		}else{    			
    			ticketStockVm.$notify.error({
	      	          title: '添加失败',
	      	          message: '票仓信息添加失败!请重试',
	      		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function getTicketStock(_ticketStock){
	
	var str="";
	str = "<div class='profile-info'><h4 class='heading'>库存信息</h4><ul class='list-unstyled list-justify'><li>库存名称 <span>" 
		+ _ticketStock.stockname 
		+ "</span></li><li>日库存 <span>" 
	if(_ticketStock.daystock == "" || _ticketStock.daystock == null ){
		str += ""
	}else{
		str += _ticketStock.daystock
	}
	str += "</span></li><li>备注 <span>"
	if(_ticketStock.remark == "" || _ticketStock.remark == null ){
		str += ""
	}else{
		str += _ticketStock.remark
	}
	str	+= "</span></li></ul></div>" 
	
	
	
	$("#ticketStockInfo_detail").html(str);	
}
function delTicketStock(ticketStockID){
	if(confirm("确认删除？") == true){
		$.ajax({
	        type: "delete",
	        url: "../ticketstocks/"+ticketStockID,	        
			contentType:'json',			
	        success: function (data) {
	        	if(data.status == 1){
	    			ticketStockVm.$notify.success({
		      	          title: '删除成功',
		      	          message: '票仓信息删除成功!',
		      		});
	    			table.ajax.reload();
	    		}else{
	    			ticketStockVm.$notify.error({
		      	          title: '删除失败',
		      	          message: '票仓信息删除失败!请重试',
		      		});
	    		}
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
	}
}

function editTicketStockInit(_ticketStock){
	$("#idEdit").val(_ticketStock.id);
	$("#stocknameEdit").val(_ticketStock.stockname);
	$("#daystockEdit").val(_ticketStock.daystock);
	$("#markEdit").val(_ticketStock.remark);	
}

function editTicketStock(){
	
	let dataParam = $("#ticketStockEditForm").serializeArray();
	dataParam.push({"name":"domainid","value":domainid});
	
	$.ajax({
        type: "put",
        url: "../ticketstocks",
        data: dataParam,
        success: function (data) { 
        	if(data.status == 1){
    			ticketStockVm.$notify.success({
	      	          title: '编辑成功',
	      	          message: '票仓信息编辑成功!',
	      		});
    			table.ajax.reload();
    			$('#ticketStockEdit').modal('hide');
    					
    		}else{
    			ticketStockVm.$notify.error({
	      	          title: '编辑失败',
	      	          message: '票仓信息编辑失败!请重试',
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
	setTicketStockTable();
  
	/*操作*/
	$("#createTicketStockBtn").click(function(){
		createSpot();
	})
	$("#editTicketStockBtn").click(function(){
		editTicketStock();
	})
	
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}

var ticketStockVm = new Vue({
	
	el:"#ticketStockVm",
	
})







