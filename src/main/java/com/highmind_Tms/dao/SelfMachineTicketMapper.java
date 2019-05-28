package com.highmind_Tms.dao;

import com.highmind_Tms.entity.SelfMacheineTicket;

public interface SelfMachineTicketMapper {
    int addTicket(long id, SelfMacheineTicket SelfMacheineTicket);

    int updateById(long id);
}
