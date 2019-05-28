package com.highmind_Tms.service;

import com.alibaba.fastjson.JSONObject;

import com.highmind_Tms.entity.SelfMachine;

public interface SelfMachineService {
    int addSelfMachine(SelfMachine SelfMachine);

    int delSelfMachineById(long id);

    SelfMachine selectSelfMachineById(long id);

    int editSelfMachine(SelfMachine SelfMachine);

    JSONObject SelfMachinelist(JSONObject jsonObject);
}
