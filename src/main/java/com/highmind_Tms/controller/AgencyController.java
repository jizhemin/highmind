package com.highmind_Tms.controller;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.entity.*;
import com.highmind_Tms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgencyController {
    @Autowired
    private AgencyTicketService agencyTicketService;
    @Autowired
    private AgencyEmployeeService agencyEmployeeService;
    @Autowired
    private AgencyService agencyService;
    //添加旅行社

    @RequestMapping(value="/addagency",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject addagency(Agency agency){
        JSONObject jsonObject=new JSONObject();
        int status=agencyService.addAgency(agency);
        jsonObject.put("status",status);
       return jsonObject;

    }
    //通过选中获取Id来删除旅行社
    @RequestMapping(value="/delagency/{id}",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject delagency(@PathVariable("id") long id){
        int status = agencyService.delAgencyById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;
    }
    //通过选中获取Id来删除旅行社售票员
    @RequestMapping(value="/delagencyemployee/{id}",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject delagencyemployee(@PathVariable("id") long id){
        int status = agencyEmployeeService.delAgencyEmployeeById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;
    }
    //通过选中获取Id来删除旅行社票类
    @RequestMapping(value="/delagencyticket/{id}",method=RequestMethod.POST)
    public JSONObject delagencyticket(@PathVariable("id") long id){
        int status =  agencyTicketService.delAgencyTicketById(id);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;
    }
    //通过选中获取Id来查找旅行社的信息
    @ResponseBody
    @RequestMapping(value = "/agency/{id}",method = RequestMethod.GET)
    public Agency getAgencyById(@PathVariable("id") long id){

        return agencyService.selectAgencyById(id);

    }
    //修改选中的旅行社信息
    @RequestMapping(value = "/editagency",method = RequestMethod.POST)

    public JSONObject editagency(Agency agency)
    {
        int status = agencyService.editAgency(agency);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;

    }
    //旅行社列表
    @ResponseBody
    @RequestMapping("/agencylist")
    public JSONObject agencylist() {
        JSONObject jsonObject = agencyService.agencyList();
        System.out.print(jsonObject);
        return jsonObject;
    }
    //旅行社售票员列表
    @ResponseBody
    @RequestMapping("/agencyemployeelist/{agency_id}")
    public JSONObject agencyemployeelist(@PathVariable("agency_id") long agency_id) {
        JSONObject jsonObject = agencyEmployeeService.agencyemployeelist(agency_id);

        return jsonObject;
    }
    //旅行社票类列表
    @ResponseBody
    @RequestMapping("/agencyticketlist/{agency_id}")
    public JSONObject agencyticketlist(@PathVariable("agency_id") long agencyid) {
        JSONObject jsonObject = agencyTicketService.agencyticketlist(agencyid);

        return jsonObject;
    }
    //添加售票员
    @RequestMapping(value = "/agencyaddemployee/{agency_id}",method = RequestMethod.POST)
    public JSONObject agencyaddemployee(@PathVariable("agency_id")long id, AgencyEmployee agencyEmployee){

        int status = agencyEmployeeService.addEmployee(id,agencyEmployee);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;

    }

    //添加票种
    @RequestMapping(value = "/agencyaddticket/{agencyid}",method = RequestMethod.POST)
    public JSONObject agencyaddemployee(@PathVariable("agencyid")long id, AgencyTicket agencyTicket){
       ;
        int status = agencyTicketService.addTicket(id, agencyTicket);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;

    }
    //编辑票类
    @RequestMapping(value="/updateAgencyTicket",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public JSONObject updateticketbyid(AgencyTicket agencyTicket){
        int status = agencyTicketService.updateById(agencyTicket);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;
    }

    //编辑售票员
    @RequestMapping(value="/updateAgencyEmployee",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public JSONObject updateemployeebyid(AgencyEmployee agencyEmployee){
        int status = agencyEmployeeService.updateById(agencyEmployee);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",status);
        return jsonObject;
    }


}
