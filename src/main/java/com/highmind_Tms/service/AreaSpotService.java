package com.highmind_Tms.service;

import java.util.List;

import com.highmind_Tms.entity.AreaSpot;

/**
 * @ClassName AreaSpotService
 * @Description TODO
 * @author 61430
 * @Date 2019年4月15日 上午7:50:11
 * @version 1.0.0
 */
public interface AreaSpotService extends BaseService<AreaSpot>{
    /**
             *批量添加 区域景点
     * @Description
     * @param areaSpots
     * @return
     */
    int addAreaSpots(List<AreaSpot> areaSpots);
}
