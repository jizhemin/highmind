package com.highmind_Tms.dao;

import com.highmind_Tms.entity.Passage;
import java.util.List;
import java.util.Map;

public interface PassageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Passage record);

    int insertSelective(Passage record);

    List<Passage> selectPassage(Map<String,Object> map);

    Passage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Passage record);

    int updateByPrimaryKey(Passage record);
}