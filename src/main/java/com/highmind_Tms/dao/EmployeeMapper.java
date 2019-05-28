package com.highmind_Tms.dao;

import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.Employee;


public interface EmployeeMapper {

    /**
     *
     * @param map 参数为domainid
     * @return
     */
    List<Employee> selectSellers(Map<String,Object> map);
}