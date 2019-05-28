package com.highmind_Tms.service;


import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.entity.AgencyTicket;

public interface AgencyTicketService {


    int addTicket(long id, AgencyTicket ticketType);

    int updateById(AgencyTicket agencyTicket);

    Integer delAgencyTicketById(long id);

    JSONObject agencyticketlist(long agencyid);
}
