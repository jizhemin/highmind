/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    SpotController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月20日 上午10:11:49
 *
 *    Revision:
 *
 *    2019年4月20日 上午10:11:49
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highmind_Tms.entity.Spot;

/**
 * @ClassName SpotController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月20日 上午10:11:49
 * @version 1.0.0
 */
@RestController
public class SpotController extends BaseController<Spot>{

    @Override
    @RequestMapping(value="/spot",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(Spot t) {
        // TODO Auto-generated method stub
        return super.addResult(spotService, t);
    }

    @Override
    @RequestMapping(value="/spot",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getAll(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getAllResult(spotService,domainid);
    }

    @Override
    @RequestMapping(value="/spot/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id,HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getOneResult(spotService, id,domainid);
    }

    @Override
    @RequestMapping(value="/spot",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(Spot t) {
        // TODO Auto-generated method stub
        return super.updateResult(spotService, t);
    }

    @Override
    @RequestMapping(value="/spot/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(spotService, id);
    }
    
}
