/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.service
 *
 *    Filename:    HolidayService.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月15日 下午1:57:45
 *
 *    Revision:
 *
 *    2019年5月15日 下午1:57:45
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service;

import java.io.File;
import java.util.List;

import com.highmind_Tms.entity.Holiday;

/**
 * @ClassName HolidayService
 * @Description TODO
 * @author 61430
 * @Date 2019年5月15日 下午1:57:45
 * @version 1.0.0
 */
public interface HolidayService extends BaseService<Holiday>{
    /**
     * 读取holiday
     * @Description
     * @param file
     * @return
     */
    int readAndInsertHoliday(File file,Long domainid);
    /**
     * 批量插入假日
     * @Description
     * @param holidays
     * @return
     */
    int addHoliday(List<Holiday> holidays);
    /**
     * 批量根据日期删除
     * @Description
     * @param datas
     * @return
     */
    int delHoliday(String[] datas);
    /**
     * @Description
     * @param t
     * @return
     */
    int addFilterHoliday(List<Holiday> t);

}
