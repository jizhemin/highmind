package com.highmind_Tms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.highmind_Tms.dao.SelfMachineMapper;
import com.highmind_Tms.entity.SelfMachine;
import com.highmind_Tms.service.SelfMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfMachineServiceImpl implements SelfMachineService {
    @Autowired
    private SelfMachineMapper SelfMachineMapper;
    @Override
    public int addSelfMachine(SelfMachine SelfMachine) {
        return SelfMachineMapper.addSelfMachine(SelfMachine);
    }

    @Override
    public int delSelfMachineById(long id) {
        return SelfMachineMapper.delSelfMachineById(id);
    }

    @Override
    public SelfMachine selectSelfMachineById(long id) {
        return SelfMachineMapper.selectSelfMachineById(id);
    }

    @Override
    public int editSelfMachine(SelfMachine SelfMachine) {
        return SelfMachineMapper.editSelfMachine(SelfMachine);
    }

    @Override
    public JSONObject SelfMachinelist(JSONObject jsonObject) {
        List<SelfMachine> rows = SelfMachineMapper.listSelfMachine(jsonObject);
        long total= SelfMachineMapper.countSelfMachine(jsonObject);
        JSONObject jo=new JSONObject();
        jo.put("rows",rows);
        jo.put("total",total);
        return jo;
    }
}
