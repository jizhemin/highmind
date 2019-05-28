/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.service.impl
 *
 *    Filename:    TicketStockServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月28日 上午11:36:45
 *
 *    Revision:
 *
 *    2019年4月28日 上午11:36:45
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.TicketStockMapper;
import com.highmind_Tms.entity.TicketStock;
import com.highmind_Tms.service.TicketStockService;

/**
 * @ClassName TicketStockServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年4月28日 上午11:36:45
 * @version 1.0.0
 */
@Service
public class TicketStockServiceImpl implements TicketStockService{
    @Autowired
    TicketStockMapper ticketStockMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public TicketStock selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<TicketStock> selectTicketStock = ticketStockMapper.selectTicketStock(map);
        return !selectTicketStock.isEmpty()?selectTicketStock.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#selectAll(java.util.Map)
     */
    @Override
    public List<TicketStock> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return ticketStockMapper.selectTicketStock(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(TicketStock record) {
        // TODO Auto-generated method stub
        return ticketStockMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(TicketStock record) {
        // TODO Auto-generated method stub
        return ticketStockMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return ticketStockMapper.deleteByPrimaryKey(id);
    }

}
