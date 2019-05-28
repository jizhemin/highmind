package com.highmind_Tms.entity;

public class Passage {
    private Long id;

    private Integer domainid;

    private Long spot_id;

    private String passagecode;

    private String passagename;

    private String ip;

    private Short io;

    private Boolean enabled;

    private String passagetype;

    private Long employee_id;

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

    public Long getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(Long spot_id) {
        this.spot_id = spot_id;
    }

    public String getPassagecode() {
        return passagecode;
    }

    public void setPassagecode(String passagecode) {
        this.passagecode = passagecode == null ? null : passagecode.trim();
    }

    public String getPassagename() {
        return passagename;
    }

    public void setPassagename(String passagename) {
        this.passagename = passagename == null ? null : passagename.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Short getIo() {
        return io;
    }

    public void setIo(Short io) {
        this.io = io;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassagetype() {
        return passagetype;
    }

    public void setPassagetype(String passagetype) {
        this.passagetype = passagetype == null ? null : passagetype.trim();
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
}