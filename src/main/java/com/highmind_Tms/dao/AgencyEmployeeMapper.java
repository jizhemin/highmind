package com.highmind_Tms.dao;

import com.highmind_Tms.entity.AgencyEmployee;

import java.util.List;

public interface AgencyEmployeeMapper {

    int addemployee(long id, AgencyEmployee agencyEmployee);

    Integer delAgencyEmployeeById(long id);

    List<AgencyEmployee> agencyemployeelist(long agencyid);

    Integer updateById(AgencyEmployee agencyEmployee);
}
