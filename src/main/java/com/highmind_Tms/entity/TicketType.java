package com.highmind_Tms.entity;

public class TicketType {
    private Long id;

    private String typename;

    private String equipvoice;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getEquipvoice() {
        return equipvoice;
    }

    public void setEquipvoice(String equipvoice) {
        this.equipvoice = equipvoice == null ? null : equipvoice.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}