
var table;
var domainid = 1;

function setEmployeeSetTable(){
	table = $('#employeeSetTable').DataTable({
		
		ajax: {
			url:'../employeeSets',
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
            	data: "id" ,
            	title: "id"
            },{ 
            	data: "ifprintprice" ,
            	title: "是否打印票面价格"
            },{
            	data: "teamprint" ,
            	title: "团队打印方式"
            },{ 
            	data: "personprint" ,
            	title: "散客打印方式",
            },{
            	title:"操作",
            	orderable:false,
            }
        ], 
        "columnDefs" : [{
        	"targets" : 10,
        	"data" : null,
        	"render" : function(data, type, row) {
        		var id_ = '"' + row.id + '"';
        		var row_ = JSON.stringify(row);
	        	var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#employeeInfo' onclick='getEmployee("+ row_ + ")'><i class='lnr lnr-magnifier'></i></button>" 
	        		 + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#employeeEdit' onclick='editEmployeeInit("+ row_ + ")'><i class='lnr lnr-pencil'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#employeeRulesEdit' onclick='getEmployeeRules("+ row_ + ")'><i class='lnr lnr-bookmark'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delEmployee("+ id_ + ")'><i class='lnr lnr-trash'></i></button>"
        			 
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


$(document).ready(function() {
	/*页面初始化*/
	navbar();
		
	/*数据初始化*/
	/*setEmployeeSetTable();*/
  
	/*操作*/
	
	
	
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}








