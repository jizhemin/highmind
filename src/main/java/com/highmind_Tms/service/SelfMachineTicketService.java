package com.highmind_Tms.service;

import com.highmind_Tms.entity.SelfMacheineTicket;

public interface SelfMachineTicketService {
    int addTicket(long id, SelfMacheineTicket ticketType);

    int updateById(long id);
}
