package com.highmind_Tms.controller;

import com.alibaba.fastjson.JSONObject;

import com.highmind_Tms.entity.SelfMacheineTicket;
import com.highmind_Tms.entity.SelfMachine;
import com.highmind_Tms.service.SelfMachineService;
import com.highmind_Tms.service.SelfMachineTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SelfMachineController {
    @Autowired
    private SelfMachineTicketService SelfMachineTicketService;
    private SelfMachineService SelfMachineService;
    //添加自助机
    @RequestMapping(value="/addSelfMachine",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public Integer addSelfMachine(SelfMachine SelfMachine){
        return SelfMachineService.addSelfMachine(SelfMachine);
    }
    //通过选中获取Id来删除自助机
    @RequestMapping(value="/selfmaching/{id}",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public Integer delagency(@PathVariable("id") long id){
        return SelfMachineService.delSelfMachineById(id);
    }
    //通过选中获取Id来查找自助机的信息
    @ResponseBody
    @RequestMapping(value = "/SelfMachine/{id}",method = RequestMethod.GET)
    public SelfMachine getSelfMachineById(@PathVariable("id") long id){

        return SelfMachineService.selectSelfMachineById(id);

    }
    //修改选中的自助机信息
    @RequestMapping(value = "/editSelfMachine/{id}",method = RequestMethod.POST)

    public Integer editSelfMachine(SelfMachine SelfMachine)
    {
        return SelfMachineService.editSelfMachine(SelfMachine);



    }
    //自助机列表
    @ResponseBody
    @RequestMapping("/SelfMachinelist")
    public JSONObject SelfMachinelist(@RequestBody JSONObject jsonObject) {
        jsonObject = SelfMachineService.SelfMachinelist(jsonObject);
        return jsonObject;
    }
    //自助机添加票类
    @RequestMapping(value = "/SelfMachineaddticket/{id}",method = RequestMethod.POST)
    public Integer agencyaddemployee(@PathVariable("id")long id, SelfMacheineTicket SelfMacheineTicket){
        ;
       return SelfMachineTicketService.addTicket(id, SelfMacheineTicket);


    }
    //启用或禁用票种
    @RequestMapping(value="/update/{id}",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public Integer updatebyid(@PathVariable("id") long id){
        return SelfMachineTicketService.updateById(id);
    }

}
