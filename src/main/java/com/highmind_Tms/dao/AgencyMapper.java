package com.highmind_Tms.dao;

import com.highmind_Tms.entity.Agency;

import java.util.List;

public interface AgencyMapper {

    int addAgency(Agency agency);

    int delAgencyById(long id);

    Agency getAgencyById(long id);

    int editAgency(Agency agency);


    List<Agency> listagency();


}
