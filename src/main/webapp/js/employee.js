
var domainid = 2;
var ticketsList = {};

var employeeVm = new Vue({
	el:"#employeeVm",
	data() {
		return {
			employeeList: [],
			defaultProps: {
				children: 'children',
				label: 'name'
			},
			emSetsList:[],
			emTicketsList:[],
			_employee:{}
		};
    },
    created:function(){
    	var _this = this;
    	
    	$.ajax({
	        type: "get",
	        url: "../selectSellers",
	        headers: {'domainid': domainid},
	        success: function (data) {
	        	if(data.status == 1){		        		
	        		_this.employeeList = data.data
	        		console.log(_this.employeeList)
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
    	employeeNodeClick(val){   

    		this._employee = val;
    		this.emSetsList = [];
    		this.emTicketsList = []

    		if(val.tickets.length > 0){
    			this.emTicketsList = val.tickets;
    		}
    		
    		if(val.employeeSet != null){
    			this.emSetsList.push(val.employeeSet);	
    			$("#printSetsBtn").css("display","none")
    		}else{
    			$("#printSetsBtn").css("display","inline-block")
    		}
    		
    	},
    	formatIfprintprice: function (row, column, cellValue, index) {
			return row.ifprintprice == true ? '是' : row.ifprintprice == false ? '否' : '未知';
		},
		formatIsstock: function (row, column, cellValue, index) {
			return row.isstock == 1 ? '是' : row.isstock == 0 ? '否' : '未知';
		},
		formatEnabled: function (row, column, cellValue, index) {
			return row.enabled == true ? '是' : row.enabled == false ? '否' : '未知';
		},
    	creatEmSetsInit(){
    		if(this.isEmployee()){  	    			
    			$('#emSetsCreate').modal('show');	
    		}	  		
    	},
    	creatEmSets(){
    		var _this = this;
    		var emSets={}
    		
    		emSets["domainid"]=2;
    		emSets["employee_id"]=this._employee.id;
    		emSets["ifprintprice"]=$('#emSetsForm input:radio[name="ifprintprice"]:checked').val();
    		emSets["teamprint"]=$('#emSetsForm input:radio[name="teamprint"]:checked').val();
    		emSets["personprint"]=$('#emSetsForm input:radio[name="personprint"]:checked').val();
    		
    		$.ajax({
    			type: "post",
    	        url: "../employeesets",
    	        data: emSets,
    	        success:function(data){
    	        	if(data.status == 1){
    	        		$('#emSetsCreate').modal('hide');
    	        		employeeVm.employeeRefresh();
    	        		_this.$notify.success({
  	        	          title: '添加成功',
  	        	          message: '售票员打印设置添加成功!',
    	        		});
    	        	}else{
    	        		_this.$notify.error({
    	        	          title: '添加失败',
    	        	          message: '售票员打印设置添加失败!',
      	        		});
    	        	} 	
    	        },
    	        error: function (message) {
    	            console.log(message);
    	        }  
    		})
    	},
    	emSetEditInit(index,row){
    		
    		if(row.ifprintprice == true){
    			$("#emSetsEditForm input[name='ifprintprice'][value='1']").attr("checked",true); 
    		}else{
    			$("#emSetsEditForm input[name='ifprintprice'][value='0']").attr("checked",true); 
    		}
			
			$("#emSetsEditForm input[name='teamprint'][value='"+ row.teamprint +"']").attr("checked",true); 
			$("#emSetsEditForm input[name='personprint'][value='"+ row.personprint +"']").attr("checked",true); 
    			
    	},
    	editEmSets(){
    		var _this = this;
    		var _emSets={}
    		
    		_emSets["domainid"]=domainid;
    		_emSets["id"]= this.emSetsList[0].id;
    		_emSets["employee_id"]=this._employee.id;
    		_emSets["ifprintprice"]=$('#emSetsEditForm input:radio[name="ifprintprice"]:checked').val();
    		_emSets["teamprint"]=$('#emSetsEditForm input:radio[name="teamprint"]:checked').val();
    		_emSets["personprint"]=$('#emSetsEditForm input:radio[name="personprint"]:checked').val();
    		
    		$.ajax({
    			type: "put",
    	        url: "../employeesets",
    	        data: _emSets,
    	        success:function(data){
    	        	if(data.status == 1){
    	        		$('#emSetsEdit').modal('hide');
    	        		employeeVm.employeeRefresh();    	        		
    	        		_this.$notify.success({
    	        	          title: '修改成功',
    	        	          message: '售票员打印设置修改成功!',
    	        	    });
    	        	}else{
    	        		_this.$notify.error({
	  	        	          title: '修改失败',
	  	        	          message: '售票员打印设置修改失败!',
    	        		});
    	        	} 	
    	        },
    	        error: function (message) {
    	        	 _this.$message.warning(message);
    	        }  
    		})
    	},
    	getEmTickets(){
    		
    		var _this = this;
    		var str="";
    		
    		if(this.isEmployee()){  			
    			
    			$("#emTickets_Init").html("");
        		$("input[name='emTicketsId']").val(_this._employee.id);
        		
        		$.each(ticketsList,function(i,v){	
        			str += "<label class='fancy-checkbox' ><input type='checkbox' name='emTickets' value="+ v.id +"><span>"
        			+ v.ticketname
        			+ "</span></label>"			
        		});	
        		
        		$("#emTickets_Init").html(str);
        		       		
        		$.each(ticketsList,function(i,v){	
        			$.each(_this.emTicketsList,function(j,w){
        				if(w.id == v.id){
        					$("input[type=checkbox][name='emTickets'][value='"+w.id+"']").prop('checked', true);
        				}
        			})			
        		});
        		
    			$("#emTicketsEdit").modal('show');	
    		}
    	},
    	editEmTickets(){
    		
    		var _this = this;
    		var emTickets = [];
    		var emTicketsChecked = $("input[name='emTickets']:checked");
    			
    		if(emTicketsChecked.length > 0 ){
    			$.each(emTicketsChecked,function(i,v){	
        			emTickets.push({
        				"domainid":domainid,
        				"employee_id":$("input[name='emTicketsId']").val(),
        				"ticket_id":this.value
        			})			
        		});
    		}else{
    			emTickets.push({
    				"domainid":domainid,
    				"employee_id":$("input[name='emTicketsId']").val(),
    				"ticket_id":0
    			})
    		}
    		
    		
    		$.ajax({
    			type: "post",
    	        url: "../ticketemployees",
    	        contentType: "application/json;charset=UTF-8",
    	        data: JSON.stringify(emTickets),
    	        success:function(data){
    	        	if(data.status == 1){
    	        		_this.$notify.success({
	  	        	          title: '分配成功',
	  	        	          message: '售票员票类分配成功!',
    	        		});
    	        		_this.employeeRefresh();
    	        		$('#emTicketsEdit').modal('hide');
    	        	}else{
    	        		_this.$notify.error({
	  	        	          title: '分配失败',
	  	        	          message: '售票员票类分配失败!',
    	        		});
    	        	} 	
    	        },
    	        error: function (message) {
    	            console.log(message);
    	        }  
    		})
    		
    	},
    	employeeRefresh(){
    		var _this = this;
    		_this.emSetsList = [];
    		_this.emTicketsList = []
    		
    		$.ajax({
    	        type: "get",
    	        url: "../selectSellers",
    	        headers: {'domainid': 2},
    	        success: function (data) {
    	        	if(data.status == 1){		
    	        		_this.employeeList = data.data	
    	        		
    	        		$.each(_this.employeeList, function(i,v){
    	        			if(v.id == _this._employee.id){
    	        				if(v.tickets.length > 0){
    	        	    			_this.emTicketsList = v.tickets;
    	        	    		}
    	        				console.log(_this.emTicketsList)
    	        	    		if(v.employeeSet != null){
    	        	    			_this.emSetsList.push(v.employeeSet);		
    	        	    			$("#printSetsBtn").css("display","none")
    	        	    		}else{
    	        	    			$("#printSetsBtn").css("display","inline-block")
    	        	    		}
    	        			}
    	        		})
    	        		
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
    	isEmployee(){ 
    		var _this = this;
    		if($.isEmptyObject(_this._employee)){
    			
    			this.$message.warning("请选择售票员");
    			return false
    		}else{
    			return true
    		}
    	}
    }
})

function emTicketsInit(){
	$.ajax({
		type: "get",
        url: "../tickets",
        headers:{"domainid":domainid},
        success:function(data){
        	if(data.status == 1){
        		ticketsList = data.data;
        	}else{
        		console.log(data.error);
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
	emTicketsInit();
		
	/*数据初始化*/
	
  
	/*操作*/
	$("#editEmTicketsBtn").click(function(){
		employeeVm.editEmTickets();
	})
	$("#createEmSetsBtn").click(function(){
		employeeVm.creatEmSets();
	})
	$("#editEmSetsBtn").click(function(){
		employeeVm.editEmSets();
	})
	
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}



