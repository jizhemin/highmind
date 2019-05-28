package com.highmind_Tms.dao;

import com.highmind_Tms.entity.TicketStock;

import java.util.List;
import java.util.Map;

public interface TicketStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketStock record);

    int insertSelective(TicketStock record);

    List<TicketStock> selectTicketStock(Map<String, Object> map);

    TicketStock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TicketStock record);

    int updateByPrimaryKey(TicketStock record);
}