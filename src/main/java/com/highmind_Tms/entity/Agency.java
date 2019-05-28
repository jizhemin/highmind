package com.highmind_Tms.entity;

public class Agency {
    private Long id;

    private Integer domainid;

    private String agencycode;

    private String agencyname;

    private String shotname;

    private String person;

    private String phone;

    private String address;

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

    public String getAgencycode() {
        return agencycode;
    }

    public void setAgencycode(String agencycode) {
        this.agencycode = agencycode == null ? null : agencycode.trim();
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname == null ? null : agencyname.trim();
    }

    public String getShotname() {
        return shotname;
    }

    public void setShotname(String shotname) {
        this.shotname = shotname == null ? null : shotname.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}