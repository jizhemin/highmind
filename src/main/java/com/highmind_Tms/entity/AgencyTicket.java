package com.highmind_Tms.entity;

import java.math.BigDecimal;

public class AgencyTicket {
    private Long id;

    private Integer domainid;

    private Long agency_id;

    private Long ticket_id;

    private BigDecimal srcprice;

    private BigDecimal price;

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

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public BigDecimal getSrcprice() {
        return srcprice;
    }

    public void setSrcprice(BigDecimal srcprice) {
        this.srcprice = srcprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}