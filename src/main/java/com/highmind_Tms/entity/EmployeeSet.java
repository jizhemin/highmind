package com.highmind_Tms.entity;

public class EmployeeSet {
    private Long id;

    private Integer domainid;

    private Boolean ifprintprice;

    private String teamprint;

    private String personprint;
    
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

    public Boolean getIfprintprice() {
        return ifprintprice;
    }

    public void setIfprintprice(Boolean ifprintprice) {
        this.ifprintprice = ifprintprice;
    }

    public String getTeamprint() {
        return teamprint;
    }

    public void setTeamprint(String teamprint) {
        this.teamprint = teamprint == null ? null : teamprint.trim();
    }

    public String getPersonprint() {
        return personprint;
    }

    public void setPersonprint(String personprint) {
        this.personprint = personprint == null ? null : personprint.trim();
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
}