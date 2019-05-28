package com.highmind_Tms.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.highmind_Tms.entity.Ticket;


/**
 * @ClassName TicketController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月15日 上午6:40:27
 * @version 1.0.0
 */
@RestController
public class TicketController extends BaseController<Ticket>{


    @Override
    @RequestMapping(value="/tickets",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(Ticket t) {
        // TODO Auto-generated method stub
        return super.addResult(ticketService, t);
    }

    @Override
    @RequestMapping(value="/tickets",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getAll(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getAllResult(ticketService,domainid);
    }

    @Override
    @RequestMapping(value="/tickets/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id,HttpServletRequest request) {
        // TODO Auto-generated method stub
        System.out.println(id);
        String domainid=request.getHeader("domainid");
        return super.getOneResult(ticketService, id,domainid);
    }

    @Override
    @RequestMapping(value="/tickets",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(Ticket t) {
        // TODO Auto-generated method stub
        return super.updateResult(ticketService, t);
    }

    @Override
    @RequestMapping(value="/tickets/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(ticketService, id);
    }

}
