package com.highmind_Tms.service;

import java.util.List;

import com.highmind_Tms.entity.TicketEmployee;

/**
 * @ClassName TicketEmployeeService
 * @Description TODO
 * @author 61430
 * @Date 2019年4月15日 上午7:53:03
 * @version 1.0.0
 */
public interface TicketEmployeeService extends BaseService<TicketEmployee>{
    /**
     * 批量添加售票员票类
     * @Description
     * @param areaSpots
     * @return
     */
    int addTicketEmployees(List<TicketEmployee> ticketEmployee);
}
