/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.service.impl
 *
 *    Filename:    HolidayServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月15日 下午1:58:11
 *
 *    Revision:
 *
 *    2019年5月15日 下午1:58:11
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.highmind_Tms.dao.HolidayMapper;
import com.highmind_Tms.entity.Holiday;
import com.highmind_Tms.service.HolidayService;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @ClassName HolidayServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年5月15日 下午1:58:11
 * @version 1.0.0
 */
@Service
public class HolidayServiceImpl implements HolidayService,ApplicationContextAware{
    @Autowired
    HolidayMapper holidayMapper;
    ApplicationContext applicationContext;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public Holiday selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return holidayMapper.selectByPrimaryKey(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectAll(java.util.Map)
     */
    @Override
    public List<Holiday> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return holidayMapper.selectHolidays(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(Holiday record) {
        // TODO Auto-generated method stub
        return holidayMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(Holiday record) {
        // TODO Auto-generated method stub
        return holidayMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return holidayMapper.deleteByPrimaryKey(id);
    }
    /**
     *返回值 0 失败
     *返回值 1 成功
     *返回值2 Excle格式不正确
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public int readAndInsertHoliday(File file,Long domainid) {
        InputStream input;
        Workbook wb = null;
        
        try {
            input = new FileInputStream(file);
            
            wb = Workbook.getWorkbook(input);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // Excel的页签数量
        int sheet_size = wb.getNumberOfSheets();
        System.out.println("共"+sheet_size+"个页签");
        
        List<Holiday> holidays = new ArrayList<Holiday>();
        Holiday holiday=null;
        for (int index = 0; index < sheet_size; index++) {
            
            // 每个页签创建一个Sheet对象
            Sheet sheet = wb.getSheet(index);
            System.out.println(sheet.getRows());
            // 行
            for (int i = 2; i< sheet.getRows(); i++) {
               
                holiday=new Holiday();
                holiday.setDomainid(domainid);
                //列
                System.out.println("当前行"+i);
                for (int j = 0; j < 2; j++) {
                    System.out.println("当前列"+j);
                    Cell cell = sheet.getCell(j, i);
                    String cellinfo;
                    if(j==0) {
                        cellinfo = cell.getContents();
                        holiday.setHolidayname(cellinfo);
                    }else if(j==1&&cell.getType() == CellType. DATE) {
                        DateCell nc  = (DateCell)cell;
                        holiday.setHolidaydate( DateUtil.offset(nc.getDate(), DateField.HOUR_OF_DAY, -8));
                    }
                }
                holidays.add(holiday);
             }
            for(Holiday s : holidays) {
                System.out.println("foreach-日期:" + s);
            }
            Map<String, Object> map=new HashMap<String, Object>(16);
            map.put("domainid", domainid);
            List<Holiday> oldHolidays=selectAll(map);
            List<Holiday> newHolidays=new ArrayList<Holiday>();
            for (Holiday holidayTemp : holidays) {
                System.out.println(holidayTemp.toString());
                if(oldHolidays.contains(holidayTemp)) {
                   System.out.println("1111");
                   continue;
                }
                newHolidays.add(holidayTemp);
            }
            HolidayService holidayService=applicationContext.getBean(HolidayService.class);
            try {
                return holidayService.addHoliday(newHolidays);
            } catch (Exception e) {
                return 0;
                // TODO: handle exception
            }
            
        }
        
        return 0;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.HolidayService#insertHoliday(java.util.List)
     */
    @Override
    public int addHoliday(List<Holiday> holidays) {
        // TODO Auto-generated method stub
        int result;
        for (Holiday holiday : holidays) {
            result=holidayMapper.insertSelective(holiday);
            //失败返回
            if(result==0) {   
                return 0;   
            }
        }  
        return 1;
    }

    /* (非 Javadoc)
     * Description:
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.applicationContext=applicationContext;
        
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.HolidayService#delHoliday(java.lang.String[])
     */
    @Override
    public int delHoliday(String[] dates) {
        // TODO Auto-generated method stub
        int result;
        for (String date : dates) {
            result=holidayMapper.deleteByDate(date);
            //失败返回
            if(result==0) {   
                return 0;   
            }
        }
        return 1;
    }

    /* (非 Javadoc)
     * Description:过滤掉已添加的holiday
     * @see com.highmind_Tms.service.HolidayService#addFilterHoliday(java.util.List)
     */
    @Override
    public int addFilterHoliday(List<Holiday> t) {
        // TODO Auto-generated method stub
        Map<String, Object> map=new HashMap<String, Object>(16);
        if(t.isEmpty()) {
            return 0;
        }
        map.put("domainid", t.get(0).getDomainid());
        List<Holiday> oldHolidays=selectAll(map);
        List<Holiday> newHolidays=new ArrayList<Holiday>();
        for (Holiday holidayTemp :t) {
            System.out.println(holidayTemp.toString());
            if(oldHolidays.contains(holidayTemp)) {
               System.out.println("1111");
               continue;
            }
            newHolidays.add(holidayTemp);
        }
        return  addHoliday(newHolidays);
    }
}
