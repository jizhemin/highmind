
var table;
var employeetable;
var agencyemployeetable;
var domainid = 1;

function setAgencyTable(){
    table = $('#agencyTable').DataTable({

        ajax: {
            url:'../agencylist',
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
                data: "agencycode" ,
                title: "旅行社编码"
            },{
                data: "agencyname" ,
                title: "旅行社名称"
            },{
                data: "enabled" ,
                title: "是否启用",
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
                var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#agencyInfo' onclick='getAgency("+ row_ + ")' title='旅行社信息'><i class='lnr lnr-magnifier'></i></button>"
                    + "&nbsp;<button type='button' class='btn btn-success btn-xs' data-toggle='modal' data-target='#agencyEdit' onclick='editAgencyInit("+ row_ + ")' title='修改旅行社信息'><i class='lnr lnr-pencil'></i></button>"
                    + "&nbsp;<button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#employeeinfo' onclick='setagencyemployeeTable("+ row_ + ")' title='售票员'><i class='lnr lnr-bookmark'></i></button>"
                    + "&nbsp;<button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#ticketinfo' onclick='setagencyticketTable("+ row_ + ")' title='票类'><i class='lnr lnr-bookmark'></i></button>"
                    + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delAgency("+ id_ + ")' title='删除旅行社'><i class='lnr lnr-trash'></i></button>"

                return html;
            }
        },{
            "targets" : 3,
            "data" : "enabled",
            "render" : function(data, type, row) {
                var html = ""
                if(row.enabled == true){

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

function setemployeeTable(){
    employeetable = $('#employeeTable').DataTable({

        ajax: {
            url:'../employeesets',
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
                data: "name" ,
                title: "售票员姓名"
            },{
                title:"操作",
                orderable:false,
            }
        ],
        "columnDefs" : [{
            "targets" : 3,
            "data" : null,
            "render" : function(data, type, row) {
                var row_ = JSON.stringify(row);
                var html = "<button type='button' class='btn btn-danger btn-xs' onclick='addemployee("+ row_+")' title='添加售票员'><i class='lnr lnr-trash'></i></button>"

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
function setagencyemployeeTable(_agency){
	var agency_id =_agency.id;

	
	
	
    agencyemployeetable = $('#agencyemployeeTable').DataTable({
     	
        ajax: {
            
        	url:'../agencyemployeelist/'+agency_id,
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
                data: "name" ,
                title: "售票员姓名"
            },{
                title:"操作",
                orderable:false,
            }
        ],
        "columnDefs" : [{
            "targets" : 3,
            "data" : null,
            "render" : function(data, type, row) {
                var row_ = JSON.stringify(row);
                var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#' onclick='employeeEditInit("+ row_ + ")'title='启用或禁用'><i class='lnr lnr-magnifier'></i></button>"
                + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delemployee("+ id_ + ")' title='删除售票员'><i class='lnr lnr-trash'></i></button>"

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
function setticketTable(){
    var tickettable = $('#ticketTable').DataTable({

        ajax: {
            url:'../tickets',
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
                title: "票类ID"
            },{
                data: "ticketname" ,
                title: "票类名称"
            },{
                data: "ticketprice" ,
                title: "原始单价"
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
                var html = "<button type='button' class='btn btn-danger btn-xs' onclick='addticket("+ row_+")' title='添加'><i class='lnr lnr-trash'></i></button>"

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
function setagencyticketTable(_agency){
    var agency_id=_agency.id
	var agencytickettable = $('#agencyticketTable').DataTable({

        ajax: {
            url:'../agencyticketlist/'+agency_id,
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
                title: "票类ID"
            },{
                data: "ticketname" ,
                title: "票类名称"
            },{
                data: "srcprice" ,
                title: "原始单价"
            },{
                data: "price" ,
                title: "协议价"
            },{
                title:"操作",
                orderable:false,
            }
        ],
        "columnDefs" : [{
            "targets" : 5,
            "data" : null,
            "render" : function(data, type, row) {
                var id_ = '"' + row.id + '"';
                var row_ = JSON.stringify(row);
                var html = "<button type='button' class='btn btn-info btn-xs' data-toggle='modal' data-target='#agencyInfo' onclick='ticketEditInit("+ row_ + " )'title='编辑'><i class='lnr lnr-magnifier'></i></button>"
                    + "&nbsp;<button type='button' class='btn btn-danger btn-xs' onclick='delticket("+ id_ + ")' title='删除售票员'><i class='lnr lnr-trash'></i></button>"

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
function addagency(){
    var agency = {};

    domainid = 1;

    agency["domainid"] = domainid;
    agency["agencycode"] = $("input[name='agencycode']").val();
    agency["agencyname"] = $("input[name='agencyname']").val();
    agency["shotname"] = $("input[name='shotname']").val();
    agency["person"] = $("input[name='person']").val();
    agency["phone"] = $("input[name='phone']").val();
    agency["address"] = $("input[name='address']").val();

    if($("input[name='isenabled']").prop('checked')) {
        agency["enabled"] = "1";
    }else{
        agency["enabled"] = "0";
    }

    console.log(agency);

    $.ajax({
        type: "POST",
        url: "../addagency",
        data: agency,
        success: function (data) {
            if(data.status == 1){
                alert("添加成功！");
                table.ajax.reload();
                $('#addagency').modal("hide")
            }else{
                alert("添加失败！");
            }
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function addemployee(_employee){
    var agencyemployee = {};
    domainid = 1;

    agencyemployee["domainid"] = domainid;
    agencyemployee["employee_id"] = _employee.id;
    agencyemployee["name"] = _employee.name;
    if($("input[name='isenabled']").prop('checked')) {
        agencyemployee["enabled"] = "1";
    }else{
        agencyemployee["enabled"] = "0";
    }

    console.log(agencyemployee);

    $.ajax({
        type: "POST",
        url: "../addemployee",
        data: agencyemployee,
        success: function (data) {
            if(data.status == 1){
                alert("添加成功！");
                table.ajax.reload();
            }else{
                alert("添加失败！");
            }
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function addticket(_ticket){
    var agencyticket= {};
    domainid = 1;

    agencyticket["domainid"] = domainid;
    agencyticket["ticket_id"] = _ticket.id;
    agencyticket["srcprice"] = _ticket.ticketprice;
    agencyticket["price"] = $("input[name='price']").val();;

    if($("input[name='isenabled']").prop('checked')) {
        agencyticket["enabled"] = "1";
    }else{
        agencyticket["enabled"] = "0";
    }

    console.log(agencyemployee);

    $.ajax({
        type: "POST",
        url: "../agencyaddemployee",
        data: agencyemployee,
        success: function (data) {
            if(data.status == 1){
                alert("添加成功！");
                table.ajax.reload();
            }else{
                alert("添加失败！");
            }
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function getAgency(_agency){

    var str="";
    str = "<div class='profile-info'><h4 class='heading'>旅行社信息</h4><ul class='list-unstyled list-justify'><li>旅行社编号 <span>"
        + _agency.agencycode + "</span></li><li>旅行社名称<span>"
        +_agency.agencyname+ "</span></li><li>旅行社简称<span>"
        +_agency.shotname+ "</span></li><li>旅行社联系人<span>"
        +_agency.person+ "</span></li><li>旅行社电话<span>"
        +_agency.phone+ "</span></li><li>旅行社地址<span>"
        + _agency.address+ "</span></li></ul></div>"
        + "<div class='profile-info'><h4 class='heading'>设置信息</h4><ul class='list-unstyled list-justify'><li>是否启用此旅行社 <span>"

    if( _agency.enabled== true){
        str += "是"

    }else{
        str += "否";
    }



    str	+= "</span></li></ul></div>"



    $("#agencyInfo_detail").html(str);
}

function delAgency(id){
    if(confirm("确认删除？") == true){
        $.ajax({
            type: "POST",
            url: "../delagency/"+id,
            contentType:'json',
            success: function (data) {
                if(data.status == 1){
                    alert("删除成功！");
                    table.ajax.reload();
                }else{
                    alert("删除失败！");
                }
            },
            error: function (message) {
                console.log(message);
            }
        });
    }
}
function delemployee(id){
    if(confirm("确认删除？") == true){
        $.ajax({
            type: "POST",
            url: "../delagnecyemployee/"+id,
            contentType:'json',
            success: function (data) {
                if(data.status == 1){
                    alert("删除成功！");
                    table.ajax.reload();
                }else{
                    alert("删除失败！");
                }
            },
            error: function (message) {
                console.log(message);
            }
        });
    }
}
function delticket(id){
    if(confirm("确认删除？") == true){
        $.ajax({
            type: "POST",
            url: "../delagencyticket/"+id,
            contentType:'json',
            success: function (data) {
                if(data.status == 1){
                    alert("删除成功！");
                    table.ajax.reload();
                }else{
                    alert("删除失败！");
                }
            },
            error: function (message) {
                console.log(message);
            }
        });
    }
}
function editAgencyInit(_agency){

    $("input[name='agencycodeEdit']").val(_agency.agencycode);
    $("input[name='agencynameEdit']").val(_agency.agencyname);
    $("input[name='agencyshotnameEdit']").val(_agency.shotname);
    $("input[name='agencypersonEdit']").val(_agency.person);
    $("input[name='agencyphoneEdit']").val(_agency.phone);
    $("input[name='agencyaddressEdit']").val(_agency.address);

    if(_agency.enabled == true) {
        $("input[name='isEnabledEdit']").prop('checked',true);

    }else{
        $("input[name='isEnabledEdit']").prop('checked',false);

    }


}
function employeeEditInit(_agencyemployee){


    if(_agencyemployee.enabled == true) {
        $("input[name='isEnabledEdit']").prop('checked',true);

    }else{
        $("input[name='isEnabledEdit']").prop('checked',false);

    }


}
function ticketEditInit(_agnecyticket){

    $("input[name='priceEdit']").val(_agnecyticket.price);

    if(_agnecyticket.enabled == true) {
        $("input[name='isEnabledEdit']").prop('checked',true);

    }else{
        $("input[name='isEnabledEdit']").prop('checked',false);

    }


}

function editAgency(){
    var _agency = {}

    _agency["agencycode"] = $("input[name='agencycodeEdit']").val();
    _agency["agencyname"] = $("input[name='agencynameEdit']").val();
    _agency["shotname"] = $("textarea[name='agencyshotnameEdit']").val();
    _agency["person"] = $("input[name='agencypersonEdit']").val();
    _agency["phone"] = $("input[name='agencyphoneEdit']").val();
    _agency["address"] = $("input[name='agencyaddressEdit']").val();


    if($("input[name='isEnabledEdit']").prop('checked')) {
        _agency["enabled"] = "1";

    }else{
        _agency["enabled"] = "0";
    }


    $.ajax({
        type: "POST",
        url: "../editagency",
        data: _agency,
        success: function (data) {
            if(data.status == 1){
                alert("修改成功！");
                table.ajax.reload();
                $('#agencyEdit').modal('hide');

            }else{
                alert("修改失败！");
            }
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function editEmployee(){
    var _agencyemployee = {}


    if($("input[name='isEnabledEdit']").prop('checked')) {
        _agencyemployee["enabled"] = "1";

    }else{
        _agencyemployee["enabled"] = "0";
    }


    $.ajax({
        type: "POST",
        url: "../updateAgencyEmployee",
        data: _agencyemployee,
        success: function (data) {
            if(data.status == 1){
                alert("修改成功！");
                table.ajax.reload();
                $('#agencyEdit').modal('hide');

            }else{
                alert("修改失败！");
            }
        },
        error: function (message) {
            console.log(message);
        }
    });
}
function editTicket(){
    var _agencyticket = {}

    _agencyticket["price"] = $("input[name='priceEdit']").val();



    if($("input[name='isEnabledEdit']").prop('checked')) {
        _agencyticket["enabled"] = "1";

    }else{
        _agencyticket["enabled"] = "0";
    }


    $.ajax({
        type: "POST",
        url: "../updateAgencyTicket",
        data: _agencyticket,
        success: function (data) {
            if(data.status == 1){
                alert("修改成功！");
                table.ajax.reload();
                $('#agencyEdit').modal('hide');

            }else{
                alert("修改失败！");
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
    setAgencyTable();
    setemployeeTable();
    setagencyemployeeTable(_agency);
    setagencyticketTable(_agency);
    setticketTable();
	/*setAgencyTable();*/
  
	/*操作*/
    $("#createAgencyBtn").click(function(){
        addagency();
    })
    $("#createEmployeeBtn").click(function(){
        addemployee(_employee);
    })
    $("#createTicketBtn").click(function(){
        addticket(_ticket);
    })
    $("#editAgencyBtn").click(function(){
        editAgency();
    })
    $("#editEmployeeBtn").click(function(){
        editEmployee();
    })
    $("#editAgencyBtn").click(function(){
        editTicket();
    })
	
});

function navbar(){
	 $(".navHeader").load("../tms/navbar.html");
}








