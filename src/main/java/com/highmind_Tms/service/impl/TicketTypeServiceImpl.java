/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.service.impl
 *
 *    Filename:    TicketTypeServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午9:27:16
 *
 *    Revision:
 *
 *    2019年4月17日 上午9:27:16
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.TicketTypeMapper;
import com.highmind_Tms.entity.TicketType;
import com.highmind_Tms.service.TicketTypeService;



/**
 * @ClassName TicketTypeServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午9:27:16
 * @version 1.0.0
 */
@Service
public class TicketTypeServiceImpl  implements TicketTypeService{
    @Autowired
    TicketTypeMapper ticketTypeMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public TicketType selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<TicketType> selectTicketType = ticketTypeMapper.selectTicketType(map);
        return !selectTicketType.isEmpty()?selectTicketType.get(0):null;
        
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectAll()
     */
    @Override
    public List<TicketType> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketTypeMapper.selectTicketType(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(TicketType record) {
        // TODO Auto-generated method stub
        return ticketTypeMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(TicketType record) {
        // TODO Auto-generated method stub
        return ticketTypeMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return ticketTypeMapper.deleteByPrimaryKey(id);
    }

}
