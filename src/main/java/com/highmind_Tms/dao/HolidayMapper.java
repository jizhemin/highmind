package com.highmind_Tms.dao;

import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.Holiday;

public interface HolidayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Holiday record);

    int insertSelective(Holiday record);
    
    List<Holiday> selectHolidays(Map<String,Object> map);
    
    Holiday selectByPrimaryKey(Map<String,Object> map);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
    /**
     * 根据日期删除节假日
     * @Description
     * @param dates
     * @return
     */
    int deleteByDate(String date);
}