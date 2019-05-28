package com.highmind_Tms.dao;

import com.highmind_Tms.entity.EmployeeSet;
import java.util.List;
import java.util.Map;

public interface EmployeeSetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmployeeSet record);

    int insertSelective(EmployeeSet record);

    List<EmployeeSet> selectEmployeeSet(Map<String, Object> map);

    EmployeeSet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmployeeSet record);

    int updateByPrimaryKey(EmployeeSet record);
}