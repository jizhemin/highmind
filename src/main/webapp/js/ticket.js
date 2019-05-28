
var table;
var domainid = 2;

function setTicketTable(){
	table = $('#ticketTable').DataTable({
		
		ajax: {
			url:'../tickets',
			dataSrc: 'data',	
			headers: {
				"domainid":domainid,
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
            	data: "ticketcode" ,
            	title: "票类编码"
            },{
            	data: "ticketname" ,
            	title: "票类名称"
            },{ 
            	data: "ticketclass" ,
            	title: "类型",
            },{
            	data: "ticketprice",
            	title: "单价"
            },{ 
            	data: "serial" ,
            	title: "顺序"
            },{
            	data: "enabled" ,
            	title: "是否启用",
            	"render" : function(data, type, row) {
            		var html = ""
            		if(row.enabled == true){
            			html = "<span class='label label-success'><i class='fa fa-check'></i></span>"
            		}else{
            			html = "<span class='label label-default'><i class='fa fa-close'></i></span>"
            		}
            		return html;
            	}
            },{
            	data: "isstock",
            	title: "库存启用",
            	"render" : function(data, type, row) {
            		var html = ""
            		if(row.isstock == 1){
            			html = "<span class='label label-success'><i class='fa fa-check'></i></span>"
            		}else{
            			html = "<span class='label label-default'><i class='fa fa-close'></i></span>"
            		}
            		return html;
            	}
            },{
            	title:"操作",
            	orderable:false,
            }
        ], 
        "columnDefs" : [{
        	"targets" : 8,
        	"data" : null,
        	"render" : function(data, type, row) {
        		let id_ = '"' + row.id + '"';
        		let row_ = JSON.stringify(row);
	        	let html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#ticketInfo' onclick='getTicket("+ row_ + ")' title='票类详细信息查看'><i class='lnr lnr-magnifier'></i></button>" 
	        		 + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#ticketEdit' onclick='editTicketInit("+ row_ + ")' title='票类信息编辑'><i class='lnr lnr-pencil'></i></button>"
	        		 + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delTicket("+ id_ + ")' title='删除票类'><i class='lnr lnr-trash'></i></button>"
        			 
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
	});
}

function serializeObject(obj){
	var item = {};
    var form = $(obj).serializeArray();
    $.each(form, function () {
        if (item[this.name] !== undefined) {
            if (!item[this.name].push) {
            	item[this.name] = [item[this.name]];
            }
            item[this.name].push(this.value || '');
        } else {
        	item[this.name] = this.value || '';
        }
    });
    var $radio = $('input[type=radio],input[type=checkbox]',$(obj));
    $.each($radio,function(){
    	if(this.name != ""){
    		if(!item.hasOwnProperty(this.name)){
    			item[this.name] = 0;
            }else{
            	item[this.name] = 1;
            }	
    	}
        
    });
    return item;
}

function createTicket(){
	
	let ticket = {};
	ticket = serializeObject("#ticketCreateForm")
	
	let tTime = ticketVm.ticAticketTime;
	let tDate = ticketVm.ticAticketDate;
	let day = ticketVm.getCheckedDays();
	
	ticket["domainid"] = 2;	
	
	if(ticket["isstock"] == 0){
		ticket["stock_id"] = ""
	}
	
	if(tTime != null){
		ticket["stime"] = tTime[0];	
		ticket["etime"] = tTime[1];	
	}
	if(tDate.length > 0){
		ticket["sdate"] = tDate[0];		
		ticket["edate"] = tDate[1];	
	}
	
	ticket["day"] = day;
	ticket["effectiveday"] = ticketVm.ticAeffectiveday;	
	
	console.log(ticket)

	$.ajax({
        type: "POST",
        url: "../tickets",
        data: ticket,	
        success: function (data) {
        	if(data.status == 1){
        		ticketVm.$notify.success({
        	          title: '添加成功',
        	          message: '票类信息添加成功!',
        		});
    			table.ajax.reload();
    			$('#ticketCreate').modal('hide');
    			$("#ticketCreate :input").each(function () {
    		        $(this).val("");
    			}); 

    		}else{
    			ticketVm.$notify.error({
      	          title: '添加失败',
      	          message: '票类信息添加失败!请重试',
      		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });	
}

function delTicket(ticketID){
	if(confirm("确认删除？") == true){
		$.ajax({
	        type: "delete",
	        url: "../tickets/"+ticketID,	        
			contentType:'json',			
	        success: function (data) {
	        	if(data.status == 1){
	        		ticketVm.$notify.success({
	        	          title: '删除成功',
	        	          message: '票类信息删除成功!',
	        		});
	    			table.ajax.reload();
	    		}else{
	    			ticketVm.$notify.error({
	        	          title: '删除失败',
	        	          message: '票类信息删除失败!请重试',
	        		});
	    		}
	        },
	        error: function (message) {
	            console.log(message);
	        }
	    });
	}
}

function getTicket(_ticket){

	console.log(_ticket.isstock)
	for (var val in _ticket) {
	    if(_ticket[val] == null || _ticket[val] === "" || _ticket[val] == undefined ){
	    	if(val != "ticketStock" ){
	    		_ticket[val] = "暂无"
	    	}	
	    }else{
	    	if(val == "enabled"){
	    		if(_ticket[val] == true){
	    			_ticket[val] = "是"
	    		}else{
	    			_ticket[val] = "否"
	    		}
	    	}
	    	if(val == "isstock" ){
	    		if(_ticket[val] == 1){
	    			_ticket[val] = "是"
	    		}else{
	    			_ticket[val] = "否"
	    		}
	    	}
	    }
	}
	
	console.log(_ticket)
	
	var str="";
	str = "<div class='profile-info'><h4 class='heading'>票类信息</h4><ul class='list-unstyled list-justify'><li>票类编码<span>"
		+ _ticket.ticketcode 
		+ "</span></li><li>票类名称 <span>" 
		+ _ticket.ticketname 
		+ "</span></li><li>所属区域<span>" 
		+ _ticket.area.areaname
		+ "</span></li><li>售票类型<span>" 
		+ _ticket.ticketclass 
		+ "</span></li><li>票种<span>" 
		+ _ticket.ticketType.typename
		+ "</span></li><li>库存设置 <span>" 
	if(_ticket.ticketStock != null){
		str += _ticket.ticketStock.stockname
	}else{
		str += "暂无"
	}
		
	str	+= "</span></li><li>单价<span>" 
		+ _ticket.ticketprice
		+ "</span></li></ul></div>" 
		
		+ "<div class='profile-info'><h4 class='heading'>基本信息设置</h4><ul class='list-unstyled list-justify'><li>验票时间<span>" 
		+ _ticket.stime + " - " + _ticket.etime
		+ "</span></li><li>使用日期 <span>" 
		+ _ticket.sdate + " - " + _ticket.edate
		+ "</span></li><li>可用星期<span>" 
		+ _ticket.day 
		+ "</span></li><li>有效天数 <span>" 
		+ _ticket.effectiveday
		+ "</span></li><li>节假日设置 <span>" 
		+ _ticket.holiday
		+ "</span></li><li>排序 <span>" 
		+ _ticket.serial
		+ "</span></li><li>是否设置库存 <span>" 
		+ _ticket.isstock
		+ "</span></li><li>是否启用 <span>" 
		+ _ticket.enabled
		+ "</span></li></ul></div>" 
		
	
	$("#ticketInfo_detail").html(str);	
}

function editTicketInit(_ticket){
	
	var ticketEdit = $("#ticketEditForm").serializeArray();
	
	console.log(_ticket);
	$.each(ticketEdit,function(i,f){ 
		$("#ticketEditForm input[name='" + f.name + "']").val(_ticket[f.name]);
	});
	
	$("#tickettype").val(_ticket.type_id);
	$("#ticketclass").val(_ticket.ticketclass);
	$("#ticketarea").val(_ticket.area_id);
	$("#holiday").val(_ticket.holiday);
	
	if(_ticket.isstock == 1){
		$("#ticketEditForm input[type=checkbox][name='isstock']").prop('checked', true);
		$('#ticketStocksEdit_div').show();
		$("#ticketstock").val(_ticket.ticketStock.id);
	}else{
		$("#ticketEditForm input[type=checkbox][name='isstock']").prop('checked', false);
		$('#ticketStocksEdit_div').hide();
		$("#ticketstock").val(0);
	}
	if(_ticket.enabled == true){
		$("#ticketEditForm input[type=checkbox][name='enabled']").prop('checked', true);
	}else{
		$("#ticketEditForm input[type=checkbox][name='enabled']").prop('checked', false);
	}
	
	ticketVm.editTimeInit(_ticket);

	
}


function editTicket(){
	let _ticket = {};
	_ticket = serializeObject("#ticketEditForm")
	
	let tTime = ticketVm.ticEticketTime;
	let tDate = ticketVm.ticEticketDate;
	let day = ticketVm.getCheckedDays();
	
	_ticket["domainid"] = 2;
	_ticket["id"] = ticketVm.ticketId;
	
	if(_ticket["isstock"] == 0){
		_ticket["stock_id"] = ""
	}
	
	if(tTime != null){
		_ticket["stime"] = tTime[0];	
		_ticket["etime"] = tTime[1];	
	}
	if(tDate != null){
		_ticket["sdate"] = tDate[0];		
		_ticket["edate"] = tDate[1];	
	}
	
	_ticket["day"] = day;

	
	console.log(_ticket)
	
	$.ajax({
        type: "put",
        url: "../tickets",
        data: _ticket,
        success: function (data) { 
        	if(data.status == 1){
        		ticketVm.$notify.success({
      	          title: '编辑成功',
      	          message: '票类信息编辑成功!',
        		});
    			alert("！");  
    			table.ajax.reload();
    			$('#ticketEdit').modal('hide');
    					
    		}else{
    			ticketVm.$notify.error({
        	          title: '编辑失败',
        	          message: '票类信息编辑失败!',
          		});
    		}
        },
        error: function (message) {
            console.log(message);
        }
    });
}

function checkIsNull(_checkList){
	
	var isReturn = false;//标识是否跳出方法	
	$.each(_checkList, function(i, v) {				
		if ($("input[name='" + v+ "']").val() == ""|| $.trim($("input[name='" + v+ "']").val()).length == 0) {	
			$("input[name='" + v+ "']").focus();
			isReturn = true;
			return false;
		}	
	});	
	if (isReturn) return false;
	
	return true;	
}

$(document).ready(function() {
	/*页面初始化*/
	navbar();
		
	/*数据初始化*/
	setTicketTable();
  
	/*操作*/
	$("#ticketCreateModalBtn").click(function(){
		ticketVm.createTimeInit()		
	})
	$("#createTicketBtn").click(function(){

		var checkList = ["ticketcode","ticketname","ticketprice"]
		if(checkIsNull(checkList) == true){
			if($("#ticketArea_id").val() != null ){
				if($("#ticketType_id").val() != null ){
					createTicket()				
				}else{
					ticketVm.$message.warning("请选择票种");
				}
			}else{
				ticketVm.$message.warning("请选择所属区域");
			}
		}else{
			ticketVm.$message.warning("请将带 ' * '标志的必填项填写完整！");
		}		
	})
	$("#editTicketBtn").click(function(){
		editTicket()		
	})
	
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}

const dayOptions = ["星期一","星期二","星期三","星期四","星期五","星期六","星期天"];

var ticketVm = new Vue({
	el:"#ticketVm",
	data(){
		return{
			domainid:2,
			ticketTypeList: [],
			areaList: [],
			ticketStocksList:[],

			/*创建时间设置*/
			ticAeffectiveday:1,
			ticAticketTime:["6:00", "17:00"],
			ticAticketDate:[],
		
			/*编辑时间设置*/
			ticEticketTime:["6:00", "17:00"],
			ticEticketDate:[],
			ticketId:"",
		
			ticketDays:dayOptions,			
			dayChecked:dayOptions,
			dayCheckAll:true,
			isIndeterminate: true
			
		}
	},
	created:function(){
		
		this.getPageInit("../tickettypes","ticketTypeList");
		this.getPageInit("../areas","areaList");
		this.getPageInit("../ticketstocks","ticketStocksList");  
		
	},
	methods:{
		getPageInit(_url,list){
			var _this = this;
			
			$.ajax({
		        type: "get",
		        url: _url,
		        headers: {'domainid': _this.domainid},
		        success: function (data) {
		        	if(data.status == 1){

		        		if(list == "ticketTypeList") _this.ticketTypeList = data.data
		        		else if(list == "areaList") _this.areaList = data.data
		        		else if(list == "ticketStocksList") _this.ticketStocksList = data.data
			        	else console.log(data.data);
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
		valdateChange(valdate){		
			return valDate	
		},
		dayCheckAllChange(val) {
	        this.dayChecked = val ? dayOptions : [];
	        this.isIndeterminate = false;
	        this.getCheckedDays()
	    },
	    dayCheckedChange(value) {
	        let checkedCount = value.length;
	        this.dayCheckAll = checkedCount === this.ticketDays.length;
	        this.isIndeterminate = checkedCount > 0 && checkedCount < this.ticketDays.length;
	        this.getCheckedDays()
	    },
	    getCheckedDays(){
	    	var dayStr = "";
	    	var days = this.dayChecked;
	    	
    		$.each(days,function(i){
	        	dayStr += days[i] + ","
	        })
	    	
	        return dayStr;
	    },
	    createTimeInit(){
	    	
	    	this.dayChecked = dayOptions;
	    	
	    	console.log(this.dayChecked)
	    	$("#ticketCreate").modal("show");
	    	
	    },	    
	    editTimeInit(_ticket){
	    	var day = _ticket.day.split(",");
	    	this.dayChecked = day;
	    	this.ticketId = _ticket.id;
	    	
	    	this.ticEticketTime = [];
	    	this.ticEticketDate = [];
	    	this.ticEticketTime.push(_ticket.stime,_ticket.etime);
	    	this.ticEticketDate.push(_ticket.sdate,_ticket.edate);
	    }


	}
})
	
	
	





	