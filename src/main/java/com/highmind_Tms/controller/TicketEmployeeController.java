/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    TicketEmployeeController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月9日 下午3:51:04
 *
 *    Revision:
 *
 *    2019年5月9日 下午3:51:04
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.highmind_Tms.entity.TicketEmployee;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;

/**
 * @ClassName TicketEmployeeController
 * @Description TODO
 * @author 61430
 * @Date 2019年5月9日 下午3:51:04
 * @version 1.0.0
 */
@RestController
public class TicketEmployeeController extends BaseController<TicketEmployee>{
           
    @RequestMapping(value="/ticketemployees",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(@RequestBody List<TicketEmployee> t) {
        // TODO Auto-generated method stub
        int id=ticketEmployeeService.addTicketEmployees(t);
        if(id>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.INSERT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }




    
    
}
