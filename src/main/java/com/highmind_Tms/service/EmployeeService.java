package com.highmind_Tms.service;

import java.util.List;
import java.util.Map;

import com.highmind_Tms.entity.Employee;


/**
 * @ClassName EmployeeService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 61430
 * @Date 2019年4月3日 上午11:29:34
 * @version 1.0.0
 */
public interface EmployeeService extends BaseService<Employee>{
   /**
           * 查询售票员员
    * @Description
    * @param map
    * @return
    */
   List<Employee> selectSeller(Map<String,Object> map);
}
