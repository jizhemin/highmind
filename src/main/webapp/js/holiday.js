
var domainid = 2;
var file;

var holidayVm = new Vue({
	
	el:"#holidayVm",
	data(){
		return {
			domainid:2,
			holiday: new Date(),
			dynamicTags: [],
			newDate: "",
			holidayList:[]
				
		}
	},
	created:function(){
		
		var _this = this
		var labelArray = []
		
		$.ajax({
	        type: "get",
	        url: "../holidays",
	        headers: {'domainid': _this.domainid},
	        success: function (data) {
	        	if(data.status == 1){		        		
	        		console.log(data.data)
	        		
	        		$.each(data.data,function(i,v){
						var year = v.holidaydate.substring(0, 4)
						
	        			if($.inArray(year,labelArray) < 0){
	        				labelArray.push(year);
	        				
	        				_this.holidayList.push({
	        					label:year,
	        					holidays:[{
	        						name:v.holidayname,
	        						dates:[v.holidaydate]
	        					}]
	        				})
	        				
	                    }else{
	                    	$.each(_this.holidayList,function(j,n){
	                    		if( year == n.label ){
	                    			var flag = 0;
		                    		$.each(n.holidays,function(k,m){
		                    			if(m.name == v.holidayname){
		                    				m.dates.push(v.holidaydate)	
		                    				flag = 1;
		                    				return false
		                    			}else{
		                    				flag = 0;
		                    			}
		                    			
		                    		})
		                    		if(flag == 0){
		                    			n.holidays.push({
			            					name:v.holidayname,
			        						dates:[v.holidaydate]
			            				})
		                    		}
		                    	}
	                    	})
	                    }
					})
					
					/*console.log(_this.holidayList);*/
					
					
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
		nodeclickChange(val){
			console.log(val)
		},
		handleClose(tag) {
			this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
		},
		dateSelect(date){
			var _this = this;
			var flag = 1;
			if(this.dynamicTags.length > 0){				
				$.each(_this.dynamicTags,function(i,v){
					if(v == date){
						flag = 0;
						return false;
					}	
				})
				if(flag == 1){
					_this.dynamicTags.push(date);					
				}else{
					_this.$message.warning("该日期已加入列表");
				}	
			}else{
				this.dynamicTags.push(date);
			}			
		},
		holidayCreateInit(){
			
			if(this.dynamicTags.length > 0){
				$("#holidayCreate").modal("show");
			}else{
				this.$message.warning("请先选择日期，再进行添加操作");
			}

		},
		createHoliday(){
			
			var holiday = [];
			var _this = this;
			
			
			$.each(this.dynamicTags, function(i,v){
				holiday.push({
					"domainid":_this.domainid,
					"holidayname":$("input[name='holidayname']").val(),
					"holidaydate":v,
					"remark":$("textarea[name='remark']").val()				
				})
				
				/*holiday["domainid"] = _this.domainid
				holiday["holidayname"] = $("input[name='holidayname']").val()
				holiday["holidaydate"] = v
				holiday["remark"] = $("textarea[name='remark']").val()*/
				
			})
			
			console.log(holiday)
			
			$.ajax({
		        type: "POST",
		        url: "../andmanyholidays",
		        contentType: "application/json;charset=UTF-8",
		        data: JSON.stringify(holiday),	
		        success: function (data) {
		        	if(data.status == 1){
		    			_this.$notify.success({
	  	        	          title: '添加成功',
	  	        	          message: '节假日添加成功!',
		    			});
		    			_this.dynamicTags = [];
		    			_this.holidayRefresh();
		    		}else{
		    			_this.$notify.error({
	  	        	          title: '添加失败',
	  	        	          message: '节假日添加失败! 请确认是否存在已添加日期 ',
		    			});
		    		}
		        },
		        error: function (message) {
		            console.log(message);
		        }
		    });
		},
		delHoliday(){
			
			var holiday = this.dynamicTags;
			var _this = this;
			
			if(this.dynamicTags.length > 0){
				if(confirm("确认删除当前选择日期？")){
					$.ajax({
				        type: "POST",
				        url: "../delmanyholidays",
				        contentType: "application/json;charset=UTF-8",
				        data: JSON.stringify(holiday),	
				        success: function (data) {
				        	if(data.status == 1){
				    			_this.$notify.success({
			  	        	          title: '删除成功',
			  	        	          message: '节假日删除成功!',
				    			});
				    			_this.dynamicTags = [];
				    			_this.holidayRefresh();
				    		}else{
				    			_this.$notify.error({
			  	        	          title: '删除失败',
			  	        	          message: '节假日删除失败! 请确认日期列表中是否存在未添加的日期 ',
				    			});
				    		}
				        },
				        error: function (message) {
				            console.log(message);
				        }
				    });
				}
			}else{
				this.$message.warning("请先选择日期，再进行删除操作");
			}
		},
		holidayRefresh(){
			var _this = this
			var labelArray = []
			this.holidayList = []
			
			$.ajax({
		        type: "get",
		        url: "../holidays",
		        headers: {'domainid': _this.domainid},
		        success: function (data) {
		        	if(data.status == 1){		        		
		        		console.log(data.data)
		        		
		        		$.each(data.data,function(i,v){
							var year = v.holidaydate.substring(0, 4)
							
		        			if($.inArray(year,labelArray) < 0){
		        				labelArray.push(year);
		        				
		        				_this.holidayList.push({
		        					label:year,
		        					holidays:[{
		        						name:v.holidayname,
		        						dates:[v.holidaydate]
		        					}]
		        				})	
		                    }else{
		                    	$.each(_this.holidayList,function(j,n){
		                    		if( year == n.label ){
		                    			var flag = 0;
			                    		$.each(n.holidays,function(k,m){
			                    			if(m.name == v.holidayname){
			                    				m.dates.push(v.holidaydate)	
			                    				flag = 1;
			                    				return false
			                    			}else{
			                    				flag = 0;
			                    			}
			                    			
			                    		})
			                    		if(flag == 0){
			                    			n.holidays.push({
				            					name:v.holidayname,
				        						dates:[v.holidaydate]
				            				})
			                    		}
			                    	}
		                    	})
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
		}
	}

})

function DownLoad(options) {
	var config = $.extend(true, { method: 'get' }, options);
	var $iframe = $('<iframe id="down-file-iframe" />');
	var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
	$form.attr('action', config.url);
	for (var key in config.data) {
		$form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
	}
	$iframe.append($form);
	$(document.body).append($iframe);
	$form[0].submit();
	$iframe.remove();
}

function holidayFileChoose(file){	
	$('#holidayFileVal').val(file.name);
	hfile = file;	
}

function holidayFileUpload(_hfile){
	
	console.log(_hfile)
	var filepath = _hfile.name;
	var filesize = _hfile.size;
	var extStart = filepath.lastIndexOf(".");
    var ext = filepath.substring(extStart, filepath.length).toUpperCase();
     
    if (ext != ".XLS" && ext != ".XLSX" ) {
        alert("文件仅支持 xls/xlsx 格式！请重新选择"); 
    }else{
    	if ( filesize > 10*1024*1024) {
            alert("文件不得大于10MB！请重新选择");
        }else{
        	var _file = new FormData();
        	_file.append("file", _hfile);
        	console.log(_file)
            $.ajax({
                url: '../parseExcel',
                type: 'post',
                headers: {'domainid': 2},
                data: _file,
                datatype:'JSON',
                fileElementId: 'fileContent',
                processData: false,
                contentType: false,
                cache: false,
                traditional: true,
                success: function (data) {               	
                	if(data.status == 1){
                		holidayVm.holidayRefresh();
                		holidayVm.$notify.success({
	  	        	          title: '导入成功',
	  	        	          message: '节假日信息导入成功!',
		    			});
                	}else{
                		holidayVm.$notify.error({
	  	        	          title: '导入失败',
	  	        	          message: '节假日信息导入失败!请重试',
		    			});
                	}          	
                },
                error: function (message) {
                    console.log(message);
                }  
            });
        }
    }
}

$(document).ready(function() {
	/*页面初始化*/
	navbar();
		
	/*数据初始化*/
  
	/*操作*/
	$("#holidayCreateBtn").click(function(){
		holidayVm.createHoliday()
		
	})
	$("#holidayDelBtn").click(function(){		
		holidayVm.delHoliday()		
	})
	$("#downloadBtn").click(function(){
		var	data ={};
		var url = "../download";
		DownLoad({ url:url,data:data});		
	})
	$("#uploadBtn").click(function(){
		holidayFileUpload(hfile);
	})
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}








