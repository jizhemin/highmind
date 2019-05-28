package com.highmind_Tms.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

public class Holiday {
    private Long id;

    private Long domainid;

    private String holidayname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField (format="yyyy-MM-dd") 
    private Date holidaydate;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public String getHolidayname() {
        return holidayname;
    }

    public void setHolidayname(String holidayname) {
        this.holidayname = holidayname == null ? null : holidayname.trim();
    }

    public Date getHolidaydate() {
        return holidaydate;
    }

    public void setHolidaydate(Date holidaydate) {
        this.holidaydate = holidaydate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getDomainid() {
        return domainid;
    }

    public void setDomainid(Long domainid) {
        this.domainid = domainid;
    }

    @Override
    public String toString() {
        return "Holiday [id=" + id + ", domainid=" + domainid + ", holidayname=" + holidayname + ", holidaydate="
                + holidaydate + ", remark=" + remark + "]";
    }
    
    public boolean equals(Object o){
        if(o instanceof Holiday){
            Holiday h = (Holiday) o;
            return DateUtil.between(this.holidaydate, h.getHolidaydate(), DateUnit.DAY)==0;
        }
        return false;
    }
}