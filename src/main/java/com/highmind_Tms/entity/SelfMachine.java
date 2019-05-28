package com.highmind_Tms.entity;

public class SelfMachine {
    private long id;
    private Integer domainid;
    private String machinecode;
    private String machinename;
    private String remark;
    private String longinid;
    private String password;
    private String software;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getDomainid() {
        return domainid;
    }

    public void setDomainid(Integer domainid) {
        this.domainid = domainid;
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLonginid() {
        return longinid;
    }

    public void setLonginid(String longinid) {
        this.longinid = longinid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public SelfMachine() {
    }

    @Override
    public String toString() {
        return "SelfMachine{" +
                "id=" + id +
                ", domainid=" + domainid +
                ", machinecode='" + machinecode + '\'' +
                ", machinename='" + machinename + '\'' +
                ", remark='" + remark + '\'' +
                ", longinid='" + longinid + '\'' +
                ", password='" + password + '\'' +
                ", software='" + software + '\'' +
                '}';
    }
}
