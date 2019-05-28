package com.highmind_Tms.dao;

import com.highmind_Tms.entity.TicketEmployee;
import java.util.List;
import java.util.Map;

public interface TicketEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TicketEmployee record);

    int insertSelective(TicketEmployee record);

    List<TicketEmployee> selectTicketEmployee(Map<String, Object> map);

    TicketEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TicketEmployee record);

    int updateByPrimaryKey(TicketEmployee record);
    
    /**
     * 
     * @Description 根据区域id删除景点
     * @param aid 区域id
     * @return
     */
    int deleteByEid(Long eId);
}