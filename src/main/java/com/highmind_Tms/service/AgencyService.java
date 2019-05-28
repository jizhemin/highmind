package com.highmind_Tms.service;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.entity.Agency;

public interface AgencyService {
    int addAgency(Agency agency);

    int delAgencyById(long id);

    Agency selectAgencyById(long id);

    int editAgency(Agency agency);

    JSONObject agencyList();


}
