/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.service.impl
 *
 *    Filename:    SpotServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午9:26:05
 *
 *    Revision:
 *
 *    2019年4月17日 上午9:26:05
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.SpotMapper;
import com.highmind_Tms.entity.Spot;
import com.highmind_Tms.service.SpotService;




/**
 * @ClassName SpotServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午9:26:05
 * @version 1.0.0
 */
@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotMapper spotMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public Spot selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<Spot> selectSpot = spotMapper.selectSpot(map);
        return !selectSpot.isEmpty()?selectSpot.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectAll()
     */
    @Override
    public List<Spot> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return spotMapper.selectSpot(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(Spot record) {
        // TODO Auto-generated method stub
        return spotMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(Spot record) {
        // TODO Auto-generated method stub
        return spotMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return spotMapper.deleteByPrimaryKey(id);
    }

}
