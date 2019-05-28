package com.highmind_Tms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.dao.AgencyEmployeeMapper;
import com.highmind_Tms.entity.AgencyEmployee;
import com.highmind_Tms.service.AgencyEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyEmployeeServiceImpl implements AgencyEmployeeService {
    @Autowired
    private AgencyEmployeeMapper agencyEmployeeMapper;
    @Override
    public int addEmployee(long id, AgencyEmployee agencyEmployee) {
        return agencyEmployeeMapper.addemployee(id,agencyEmployee);
    }

    @Override
    public Integer delAgencyEmployeeById(long id) {
        return agencyEmployeeMapper.delAgencyEmployeeById(id);
    }

    @Override
    public JSONObject agencyemployeelist(long agencyid) {
        List<AgencyEmployee> data =agencyEmployeeMapper.agencyemployeelist(agencyid);
        JSONObject jo = new JSONObject();
        jo.put("data",data);
        return jo;

    }





    @Override
    public Integer updateById(AgencyEmployee agencyEmployee) {
        return agencyEmployeeMapper.updateById(agencyEmployee);
    }
}
