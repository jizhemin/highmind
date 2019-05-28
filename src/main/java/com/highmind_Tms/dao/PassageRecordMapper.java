package com.highmind_Tms.dao;

import com.highmind_Tms.entity.PassageRecord;
import java.util.List;
import java.util.Map;

public interface PassageRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PassageRecord record);

    int insertSelective(PassageRecord record);

    List<PassageRecord> selectPassageRecord(Map<String, Object> map);

    PassageRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PassageRecord record);

    int updateByPrimaryKey(PassageRecord record);
}