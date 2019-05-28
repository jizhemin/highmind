package com.highmind_Tms.dao;


import com.highmind_Tms.entity.AgencyTicket;

import java.util.List;

public interface AgencyTicketMapper {
    int addTicketType(long id, AgencyTicket agencyTicket);

    int updateById(AgencyTicket agencyTicket);

    Integer delAgencyTicketById(long id);

    List<AgencyTicket> agencyticketlist(long agencyid);
}
