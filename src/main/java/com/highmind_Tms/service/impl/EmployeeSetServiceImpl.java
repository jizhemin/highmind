/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.service.impl
 *
 *    Filename:    EmployeeSetServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月7日 下午3:13:06
 *
 *    Revision:
 *
 *    2019年5月7日 下午3:13:06
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.EmployeeSetMapper;
import com.highmind_Tms.entity.EmployeeSet;
import com.highmind_Tms.service.EmployeeSetService;

/**
 * @ClassName EmployeeSetServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年5月7日 下午3:13:06
 * @version 1.0.0
 */
@Service
public class EmployeeSetServiceImpl implements EmployeeSetService{
    @Autowired
    EmployeeSetMapper employeeSetMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public EmployeeSet selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<EmployeeSet> selectEmployeeSet = employeeSetMapper.selectEmployeeSet(map);
        return !selectEmployeeSet.isEmpty()?selectEmployeeSet.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectAll(java.util.Map)
     */
    @Override
    public List<EmployeeSet> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return employeeSetMapper.selectEmployeeSet(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(EmployeeSet record) {
        // TODO Auto-generated method stub
        return employeeSetMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(EmployeeSet record) {
        // TODO Auto-generated method stub
        return employeeSetMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return employeeSetMapper.deleteByPrimaryKey(id);
    }

}
