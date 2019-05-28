package com.highmind_Tms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.highmind_Tms.exception.AddException;
import com.highmind_Tms.service.*;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @ClassName BaseController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月4日 上午9:38:23
 * @version 1.0.0
 */
public abstract class BaseController<T> {
    /**
     * 基本系统快注入
     */
    
    
    /**
     * 票务系统基础注入
     */
    @Autowired
    TicketService ticketService;
    @Autowired
    AreaService areaService;
    @Autowired
    AreaSpotService areaSpotService;
    @Autowired
    SpotService spotService;
    @Autowired
    TicketTypeService ticketTypeService;
    @Autowired
    TicketEmployeeService ticketEmployeeService;
    @Autowired
    TicketStockService ticketStockService;
    @Autowired
    EmployeeSetService employeeSetService;
    public String add(T t) throws AddException {
        return null;
    }   
    public String getAll(HttpServletRequest request) {
        return null;
    }    
    public String getOne(@PathVariable("id") Long id,HttpServletRequest request) {
        return null;
    }    
    public String update(T t) {
        return null;
    }
    
    public String delete(@PathVariable("id")Long id) {
        return null;
    }
    // 过滤
    SimplePropertyPreFilter successFilter = new SimplePropertyPreFilter(Result.class, "status","data");
    SimplePropertyPreFilter errorFilter = new SimplePropertyPreFilter(Result.class, "status","error");
    
    public String addResult(BaseService<T> baseService,T t) {
        // TODO Auto-generated method stub
        int id =baseService.add(t);
        if(id>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.INSERT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
        
    }
    public String getOneResult(BaseService<T> baseService,Long id,String domainid) {
        Map<String,Object> hashMap=new HashMap<String,Object>();
        hashMap.put("id",id);
        hashMap.put("domainid", domainid);
        T selectById = baseService.selectById(hashMap);
        if(selectById!=null) {
            return JSONObject.toJSONString(Result.success(selectById),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.NOT_FIND_DATA),errorFilter,SerializerFeature.WriteMapNullValue);
        }

    }
    public String getAllResult(BaseService<T> baseService,String domainid) {
        Map<String,Object> hashMap=new HashMap<String,Object>();
        hashMap.put("domainid", domainid);
        List<T> selectAll = baseService.selectAll(hashMap);
        if(!selectAll.isEmpty()) {
            return JSONObject.toJSONString(Result.success(selectAll),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.NOT_FIND_DATA),errorFilter,SerializerFeature.WriteMapNullValue);
        }

    }
    public String updateResult(BaseService<T> baseService,T t) {
        int id =baseService.update(t);
        if(id>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.UPDATE_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }
    public String deleteResult(BaseService<T> baseService,Long id) {
        int result=baseService.del(id);
        if(result>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.DELETE_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
        
    }
    /**
     * 查询的列表进行处理并返回字符串
     * @Description
     * @param selectAll
     * @param b 传是 
     * @return
     */
    public String getString(List<T> selectAll, boolean b) {
        if (b) {
            return JSONObject.toJSONString(Result.success(selectAll),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        } else {
            return JSONObject.toJSONString(Result.error(CodeMsg.NOT_FIND_DATA),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }
}
