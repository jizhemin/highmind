package com.highmind_Tms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.dao.AgencyTicketMapper;
import com.highmind_Tms.entity.AgencyTicket;
import com.highmind_Tms.service.AgencyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyTicketServiceImpl implements AgencyTicketService {
    @Autowired
    private AgencyTicketMapper agencyTicketMapper;
    @Override
    public int addTicket(long id, AgencyTicket agencyTicket) {
        return agencyTicketMapper.addTicketType(id,agencyTicket);

    }

    @Override
    public int updateById(AgencyTicket agencyTicket) {
        return agencyTicketMapper.updateById(agencyTicket);
    }

    @Override
    public Integer delAgencyTicketById(long id) {
        return agencyTicketMapper.delAgencyTicketById(id);
    }

    @Override
    public JSONObject agencyticketlist(long agencyid) {
        List<AgencyTicket> data=agencyTicketMapper.agencyticketlist(agencyid);
        JSONObject jo=new JSONObject();
        jo.put("data",data);
        return jo;

    }
}
