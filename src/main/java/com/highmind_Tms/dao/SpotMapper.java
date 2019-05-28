package com.highmind_Tms.dao;


import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.Spot;

public interface SpotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Spot record);

    int insertSelective(Spot record);

    List<Spot> selectSpot(Map<String, Object> hashMap);

    Spot selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);
}