/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.service.impl
 *
 *    Filename:    AreaServiceImpl.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午9:25:21
 *
 *    Revision:
 *
 *    2019年4月17日 上午9:25:21
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highmind_Tms.dao.AreaMapper;
import com.highmind_Tms.entity.Area;
import com.highmind_Tms.service.AreaService;




/**
 * @ClassName AreaServiceImpl
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午9:25:21
 * @version 1.0.0
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaMapper areaMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public Area selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<Area> selectArea = areaMapper.selectArea(map);
        return !selectArea.isEmpty()?selectArea.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectAll()
     */
    @Override
    public List<Area> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return areaMapper.selectArea(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(Area record) {
        // TODO Auto-generated method stub
        return areaMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(Area record) {
        // TODO Auto-generated method stub
        return areaMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return areaMapper.deleteByPrimaryKey(id);
    }

}
