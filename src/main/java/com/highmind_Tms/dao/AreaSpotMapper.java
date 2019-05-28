package com.highmind_Tms.dao;


import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.AreaSpot;

public interface AreaSpotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AreaSpot record);

    int insertSelective(AreaSpot record);

    List<AreaSpot> selectAreaSpot(Map<String, Object> hashMap);

    AreaSpot selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AreaSpot record);

    int updateByPrimaryKey(AreaSpot record);
    /**
     * 
     * @Description 根据区域id删除景点
     * @param aid 区域id
     * @return
     */
    int deleteByAid(Long aId);
}