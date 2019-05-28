/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.service.impl
 *
 *    Filename:    TicketEmployeeServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午9:26:52
 *
 *    Revision:
 *
 *    2019年4月17日 上午9:26:52
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.TicketEmployeeMapper;
import com.highmind_Tms.entity.TicketEmployee;
import com.highmind_Tms.service.TicketEmployeeService;




/**
 * @ClassName TicketEmployeeServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午9:26:52
 * @version 1.0.0
 */
@Service
public class TicketEmployeeServiceImpl implements TicketEmployeeService {

    @Autowired
    TicketEmployeeMapper ticketEmployeeMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public TicketEmployee selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<TicketEmployee> selectTicketEmployee = ticketEmployeeMapper.selectTicketEmployee(map);
        return !selectTicketEmployee.isEmpty()?selectTicketEmployee.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectAll()
     */
    @Override
    public List<TicketEmployee> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketEmployeeMapper.selectTicketEmployee(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(TicketEmployee record) {
        // TODO Auto-generated method stub
        return ticketEmployeeMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(TicketEmployee record) {
        // TODO Auto-generated method stub
        return ticketEmployeeMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return ticketEmployeeMapper.deleteByPrimaryKey(id);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.TicketEmployeeService#addTicketEmployees(java.util.List)
     */
    @Override
    public int addTicketEmployees(List<TicketEmployee> ticketEmployees) {
        // TODO Auto-generated method stub
        int time=0;
        for(TicketEmployee ticketEmployee:ticketEmployees) {
            if(time==0) {
                time++;
                ticketEmployeeMapper.deleteByEid(ticketEmployee.getEmployee_id());
                //为全删做准备
                if(ticketEmployee.getTicket_id()==0) {
                    return 1;
                }
            }
            int result=ticketEmployeeMapper.insertSelective(ticketEmployee);
            if(result==0) {
                return 0;
            }
        }
        return 1;
    }

}
