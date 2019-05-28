package com.highmind_Tms.entity;

public class TicketStock {
    private Long id;

    private Integer domainid;

    private String stockname;

    private Integer daystock;

    private String remark;

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

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname == null ? null : stockname.trim();
    }

    public Integer getDaystock() {
        return daystock;
    }

    public void setDaystock(Integer daystock) {
        this.daystock = daystock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}