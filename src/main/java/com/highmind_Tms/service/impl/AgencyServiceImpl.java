package com.highmind_Tms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.dao.AgencyMapper;
import com.highmind_Tms.entity.Agency;
import com.highmind_Tms.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;
    @Override
    public int addAgency(Agency agency) {
        return agencyMapper.addAgency(agency);
    }

    @Override
    public int delAgencyById(long id) {
        return agencyMapper.delAgencyById(id);
    }

    @Override
    public Agency selectAgencyById(long id) {
        return agencyMapper.getAgencyById(id);
    }

    @Override
    public int editAgency(Agency agency) {
        return agencyMapper.editAgency(agency);

    }

    @Override
    public JSONObject agencyList() {
        List<Agency> data = agencyMapper.listagency();
        JSONObject jo=new JSONObject();
        jo.put("data",data);
        return jo;
    }
}
