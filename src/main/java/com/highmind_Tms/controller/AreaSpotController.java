/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    AreaSpotController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月20日 上午10:11:26
 *
 *    Revision:
 *
 *    2019年4月20日 上午10:11:26
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.highmind_Tms.entity.AreaSpot;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;

/**
 * @ClassName AreaSpotController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月20日 上午10:11:26
 * @version 1.0.0
 */
@RestController
public class AreaSpotController extends BaseController<AreaSpot>{

    
    @RequestMapping(value="/areaspot",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(@RequestBody List<AreaSpot>  t) {
        // TODO Auto-generated method stub
        int id=areaSpotService.addAreaSpots(t);
        if(id>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.INSERT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }

//    @Override
//    @RequestMapping(value="/areaspot",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
//    public String getAll(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        String domainid=request.getHeader("domainid");
//        return super.getAllResult(areaSpotService,domainid);
//    }
//
//    @Override
//    @RequestMapping(value="/areaspot/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
//    public String getOne(Long id,HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        String domainid=request.getHeader("domainid");
//        return super.getOneResult(areaSpotService, id,domainid);
//    }

    @Override
    @RequestMapping(value="/areaspot",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(AreaSpot t) {
        // TODO Auto-generated method stub
        return super.updateResult(areaSpotService, t);
    }

    @Override
    @RequestMapping(value="/areaspot/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(areaSpotService, id);
    }
    
}
