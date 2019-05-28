/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    TicketStockController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月28日 上午11:34:19
 *
 *    Revision:
 *
 *    2019年4月28日 上午11:34:19
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highmind_Tms.entity.TicketStock;

/**
 * @ClassName TicketStockController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月28日 上午11:34:19
 * @version 1.0.0
 */
@RestController
public class TicketStockController extends BaseController<TicketStock>{

    @Override
    @RequestMapping(value="/ticketstocks",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(TicketStock t) {
        // TODO Auto-generated method stub
        return super.addResult(ticketStockService, t);
    }

    @Override
    @RequestMapping(value="/ticketstocks",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getAll(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getAllResult(ticketStockService, domainid);
    }

    @Override
    @RequestMapping(value="/ticketstocks/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getOneResult(ticketStockService, id, domainid);
    }

    @Override
    @RequestMapping(value="/ticketstocks",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(TicketStock t) {
        // TODO Auto-generated method stub
        return super.updateResult(ticketStockService, t);
    }

    @Override
    @RequestMapping(value="/ticketstocks/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(ticketStockService, id);
    }
    
}
