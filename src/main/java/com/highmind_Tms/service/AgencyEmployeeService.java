package com.highmind_Tms.service;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.entity.AgencyEmployee;

public interface AgencyEmployeeService {

    int addEmployee(long id, AgencyEmployee agencyEmployee);

    Integer delAgencyEmployeeById(long id);

    JSONObject agencyemployeelist(long agencyid);


    Integer updateById(AgencyEmployee agencyEmployee);
}
