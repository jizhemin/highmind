/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.service.impl
 *
 *    Filename:    AreaSpotService.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午9:25:34
 *
 *    Revision:
 *
 *    2019年4月17日 上午9:25:34
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.highmind_Tms.dao.AreaSpotMapper;
import com.highmind_Tms.entity.AreaSpot;
import com.highmind_Tms.service.AreaSpotService;



/**
 * @ClassName AreaSpotService
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午9:25:34
 * @version 1.0.0
 */
@Service
public class AreaSpotServiceImpl implements AreaSpotService {
    @Autowired
    AreaSpotMapper areaSpotMapper;
    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectById(java.util.Map)
     */
    @Override
    public AreaSpot selectById(Map<String, Object> map) {
        // TODO Auto-generated method stub
        List<AreaSpot> selectAreaSpot = areaSpotMapper.selectAreaSpot(map);
        return !selectAreaSpot.isEmpty()?selectAreaSpot.get(0):null;
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#selectAll()
     */
    @Override
    public List<AreaSpot> selectAll(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return areaSpotMapper.selectAreaSpot(map);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#add(java.lang.Object)
     */
    @Override
    public int add(AreaSpot record) {
        // TODO Auto-generated method stub
        return areaSpotMapper.insertSelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#update(java.lang.Object)
     */
    @Override
    public int update(AreaSpot record) {
        // TODO Auto-generated method stub
        return areaSpotMapper.updateByPrimaryKeySelective(record);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind.service.BaseService#del(java.lang.Long)
     */
    @Override
    public int del(Long id) {
        // TODO Auto-generated method stub
        return areaSpotMapper.deleteByPrimaryKey(id);
    }

    /* (非 Javadoc)
     * Description:
     * @see com.highmind_Tms.service.AreaSpotService#addAreaSpots(java.util.List)
     */
    @Override
    public int addAreaSpots(List<AreaSpot> areaSpots) {
        // TODO Auto-generated method stub
        int time=0;
        for(AreaSpot areaSpot:areaSpots) {
            if(time==0) {
                time++;
                areaSpotMapper.deleteByAid(areaSpot.getArea_id());
            }
            int result=areaSpotMapper.insertSelective(areaSpot);
            if(result==0) {
                return 0;
            }
        }
         return 1;
    }

}
