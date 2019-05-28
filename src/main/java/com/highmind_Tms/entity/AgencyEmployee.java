package com.highmind_Tms.entity;

public class AgencyEmployee {
    private Long id;

    private Integer domainid;

    private Long agency_id;

    private Long empolyee_id;

    private Boolean enabled;

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

    public Long getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(Long agency_id) {
        this.agency_id = agency_id;
    }

    public Long getEmpolyee_id() {
        return empolyee_id;
    }

    public void setEmpolyee_id(Long empolyee_id) {
        this.empolyee_id = empolyee_id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}