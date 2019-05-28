/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    AreaController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月20日 上午10:11:03
 *
 *    Revision:
 *
 *    2019年4月20日 上午10:11:03
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.highmind_Tms.entity.Area;


/**
 * @ClassName AreaController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月20日 上午10:11:03
 * @version 1.0.0
 */
@RestController
public class AreaController extends BaseController<Area>{

    @Override
    @RequestMapping(value="/areas",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(Area t) {
        // TODO Auto-generated method stub
        return super.addResult(areaService, t);
    }

    @Override
    @RequestMapping(value="/areas",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getAll(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getAllResult(areaService, domainid);
    }

    @Override
    @RequestMapping(value="/areas/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id,HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getOneResult(areaService, id, domainid);
    }

    @Override
    @RequestMapping(value="/areas",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(Area t) {
        // TODO Auto-generated method stub
        return super.updateResult(areaService, t);
    }

    @Override
    @RequestMapping(value="/areas/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(areaService, id);
    }
    
}
