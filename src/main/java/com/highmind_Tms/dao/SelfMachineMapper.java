package com.highmind_Tms.dao;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.entity.SelfMachine;

import java.util.List;

public interface SelfMachineMapper {
    int addSelfMachine(SelfMachine SelfMachine);

    int delSelfMachineById(long id);

    SelfMachine selectSelfMachineById(long id);

    int editSelfMachine(SelfMachine SelfMachine);

    List<SelfMachine> listSelfMachine(JSONObject jsonObject);

    long countSelfMachine(JSONObject jsonObject);

}
