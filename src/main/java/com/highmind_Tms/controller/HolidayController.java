/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.controller
 *
 *    Filename:    HolidayController.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月15日 下午3:34:04
 *
 *    Revision:
 *
 *    2019年5月15日 下午3:34:04
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.highmind_Tms.entity.Holiday;
import com.highmind_Tms.exception.AddException;
import com.highmind_Tms.service.HolidayService;
import com.highmind_Tms.tool.CodeMsg;
import com.highmind_Tms.tool.Result;

/**
 * @ClassName HolidayController
 * @Description TODO
 * @author 61430
 * @Date 2019年5月15日 下午3:34:04
 * @version 1.0.0
 */
@RestController
public class HolidayController extends BaseController<Holiday>{
    @Autowired
    HolidayService holidayService;
    @RequestMapping(value="/parseExcel",produces = "text/json;charset=UTF-8")
    public String ExcelToDB(MultipartFile file,HttpServletRequest request) {
        String domainid=request.getHeader("domainid");
        Long domaindIdLong = 0L;
        if(domainid!=null) {
            domaindIdLong=Long.valueOf(domainid);
        }
        final int MEMORY_THRESHOLD = 1024*1024*3;
        final int MAX_FILE_SIZE = 1024*1024*40;
        final int MAX_REQUEST_SIZE = 1024*1024*50;
        int res = 0;
        
        //上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置内存临界值
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        //设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        //设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
        //设置最大请求值(包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        //中文处理
        upload.setHeaderEncoding("UTF-8");
        
        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("/") + File.separator + "upload";
        
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        } 

        String filePath = uploadPath + File.separator + file.getOriginalFilename();;  //文件路径
       
        File storeFile = new File(filePath);
        // 在控制台输出文件的上传路径
        System.out.println(filePath);                 
        // 保存文件到硬盘
        try {
            file.transferTo(storeFile);
            res = holidayService.readAndInsertHoliday(new File(filePath),domaindIdLong);
            System.out.println("执行结果:" + res);
            
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(res > 0) {
            return JSONObject.toJSONString(Result.success(res),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else
            return JSONObject.toJSONString(Result.error(CodeMsg.IMPORT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);               
        
    }
    
    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //模拟文件，myfile.txt为需要下载的文件  
        String filename="节假日.xls";
        String path = request.getSession().getServletContext().getRealPath("download\\"+filename); 
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }
        bis.close();
        out.close();  
    }

    @Override
    @RequestMapping(value="/holidays",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(Holiday t) throws AddException {
        // TODO Auto-generated method stub
        try{
            return super.addResult(holidayService, t);
        }catch (Exception e) {
            throw new AddException();
            // TODO: handle exception
        }
        
    }
    @Override
    @RequestMapping(value="/holidays",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getAll(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getAllResult(holidayService, domainid);
    }
    @Override
    @RequestMapping(value="/holidays/{id}",method=RequestMethod.GET,produces = "text/json;charset=UTF-8")
    public String getOne(@PathVariable("id")Long id, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String domainid=request.getHeader("domainid");
        return super.getOneResult(holidayService, id, domainid);
    }
    @Override
    @RequestMapping(value="/holidays",method=RequestMethod.PUT,produces = "text/json;charset=UTF-8")
    public String update(Holiday t) {
        // TODO Auto-generated method stub
        return super.updateResult(holidayService, t);
    }
    @Override
    @RequestMapping(value="/holidays/{id}",method=RequestMethod.DELETE,produces = "text/json;charset=UTF-8")
    public String delete(@PathVariable("id")Long id) {
        // TODO Auto-generated method stub
        return super.deleteResult(holidayService, id);
    }
    
    @RequestMapping(value="/andmanyholidays",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String add(@RequestBody List<Holiday> t) throws AddException {
        // TODO Auto-generated method stub
        try{
            int id=holidayService.addFilterHoliday(t);
            if(id>0) {
                return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
            }else {
                return JSONObject.toJSONString(Result.error(CodeMsg.INSERT_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
            }
        }catch (Exception e) {
            throw new AddException();
            // TODO: handle exception
        }
    }
    
    @RequestMapping(value="/delmanyholidays",method=RequestMethod.POST,produces = "text/json;charset=UTF-8")
    public String del(@RequestBody String[] dates) {
        // TODO Auto-generated method stub
        int id=holidayService.delHoliday(dates);
        if(id>0) {
            return JSONObject.toJSONString(Result.success(id),successFilter,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
        }else {
            return JSONObject.toJSONString(Result.error(CodeMsg.DELETE_ERROR),errorFilter,SerializerFeature.WriteMapNullValue);
        }
    }
}
