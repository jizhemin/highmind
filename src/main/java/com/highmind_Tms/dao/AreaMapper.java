package com.highmind_Tms.dao;


import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectArea(Map<String, Object> hashMap);

    Area selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}