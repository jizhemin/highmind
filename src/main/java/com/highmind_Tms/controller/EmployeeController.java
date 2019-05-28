package com.highmind_Tms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.highmind_Tms.entity.Employee;
import com.highmind_Tms.service.EmployeeService;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;


/**
 * @ClassName EmployeeController
 * @Description TODO 雇员控制
 * @author 61430
 * @Date 2019年4月3日 上午11:40:04
 * @version 1.0.0
 */
@RestController
public class EmployeeController extends BaseController<Employee>{
    @Autowired 
    EmployeeService employeeService;
    /**
         * 查询售票员
     * @Description
     * @param request
     * @return
     */
    @RequestMapping(value="/selectSellers",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String selectSellers(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        Map<String,Object> map=new HashMap<String,Object>(16);
        map.put("domainid", domainid);
        List<Employee> selectSeller = employeeService.selectSeller(map);
        if(!selectSeller.isEmpty()) {
            return JSONObject.toJSONString(Result.success(selectSeller),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.NOT_FIND_DATA),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }

    
    @Override
    /**
     * 
     */
    @RequestMapping(value="/selectSellers/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        Map<String,Object> map=new HashMap<String,Object>(16);
        map.put("domainid", domainid);
        map.put("id", id);
        List<Employee> selectSeller = employeeService.selectSeller(map);
        if(!selectSeller.isEmpty()) {
            return JSONObject.toJSONString(Result.success(selectSeller),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.NOT_FIND_DATA),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }

}
