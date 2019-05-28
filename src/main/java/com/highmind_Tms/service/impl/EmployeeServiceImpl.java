package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.EmployeeMapper;
import com.highmind_Tms.entity.Employee;
import com.highmind_Tms.service.EmployeeService;



/**
 * @ClassName EmployeeServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 61430
 * @Date 2019年4月3日 下午1:02:37
 * @version 1.0.0
 */
@Service
public  class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeMapper employeeMapper;

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public Employee selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectAll(java.util.Map)
     */
    @Override
    public List<Employee> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(Employee record) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(Employee record) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.EmployeeService#selectSeller(java.util.Map)
     */
    @Override
    public List<Employee> selectSeller(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return employeeMapper.selectSellers(map);
    }
    
}
