/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.exception
 *
 *    Filename:    GlobalExceptionHandler.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月24日 下午3:31:16
 *
 *    Revision:
 *
 *    2019年4月24日 下午3:31:16
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.exception;


import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import cn.hutool.core.date.DateUtil;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO 全局异常捕捉
 * @author 61430
 * @Date 2019年4月24日 下午3:31:16
 * @version 1.0.0
 */
@ControllerAdvice()
public class GlobalExceptionHandler {
    // 构造一个log4j日志对象
    Logger logger = Logger.getLogger("logFile");
    // 添加异常
    SimplePropertyPreFilter errorFilter = new SimplePropertyPreFilter(Result.class, "status","error");
    @ExceptionHandler(value =AddException.class)
    @ResponseBody
    public String addException(Exception e) {
        Date d = new Date();
        String format = DateUtil.format(d, "yyyy-MM-dd HH:mm:ss");
        logger.error("____________________________________________");
        logger.error("添加异常："+format);
        logger.error(e);
        logger.error("____________________________________________");
        System.out.println("异常");
        e.printStackTrace(); 
        return JSONObject.toJSONString(Result.error(CodeMsg.INSERT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);  
    }
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public String exception1(Exception e) {
        Date d = new Date();
        String format = DateUtil.format(d, "yyyy-MM-dd HH:mm:ss");
        logger.error("____________________________________________");
        logger.error("数据库异常："+format);
        logger.error(e);
        logger.error("____________________________________________");
        System.out.println("异常");
        e.printStackTrace(); 
        return JSONObject.toJSONString(Result.error(CodeMsg.CASCADE_DELETE),errorFilter,SerializerFeature.WriteMapNullValue);  
    }
    @ExceptionHandler(value = SQLServerException.class)
    @ResponseBody
    public String exception2(Exception e) {
        System.out.println("数据库异常");
        e.printStackTrace(); 
        return JSONObject.toJSONString(Result.error(CodeMsg.CASCADE_DELETE),errorFilter,SerializerFeature.WriteMapNullValue);  
    }
}
