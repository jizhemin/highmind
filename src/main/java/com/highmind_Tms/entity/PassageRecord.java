package com.highmind_Tms.entity;

import java.util.Date;

public class PassageRecord {
    private Long id;

    private Integer domainid;

    private Date FM_CreateTime;

    private Long passage_id;

    private String permit_type;

    private String permit_value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

    public Date getFM_CreateTime() {
        return FM_CreateTime;
    }

    public void setFM_CreateTime(Date FM_CreateTime) {
        this.FM_CreateTime = FM_CreateTime;
    }

    public Long getPassage_id() {
        return passage_id;
    }

    public void setPassage_id(Long passage_id) {
        this.passage_id = passage_id;
    }

    public String getPermit_type() {
        return permit_type;
    }

    public void setPermit_type(String permit_type) {
        this.permit_type = permit_type == null ? null : permit_type.trim();
    }

    public String getPermit_value() {
        return permit_value;
    }

    public void setPermit_value(String permit_value) {
        this.permit_value = permit_value == null ? null : permit_value.trim();
    }
}