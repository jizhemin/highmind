package com.highmind_Tms.service.impl;

import com.highmind_Tms.dao.SelfMachineTicketMapper;
import com.highmind_Tms.entity.SelfMacheineTicket;
import com.highmind_Tms.service.SelfMachineTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelfMachineTicketServiceImpl implements SelfMachineTicketService {
    @Autowired
    private SelfMachineTicketMapper SelfMachineTicketMapper;
    @Override
    public int addTicket(long id, SelfMacheineTicket SelfMacheineTicket) {
        return SelfMachineTicketMapper.addTicket(id,SelfMacheineTicket);
    }

    @Override
    public int updateById(long id) {
        return SelfMachineTicketMapper.updateById(id);
    }
}
