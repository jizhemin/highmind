package com.highmind_Tms.entity;

public class SelfMacheineTicket {
    private long id;
    private Integer domainid;
    private long machine_id;
    private long ticket_id;
    private String image;

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

    public long getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(long machine_id) {
        this.machine_id = machine_id;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SelfMacheineTicket() {
    }

    @Override
    public String toString() {
        return "SelfMacheineTicket{" +
                "id=" + id +
                ", domainid=" + domainid +
                ", machine_id=" + machine_id +
                ", ticket_id=" + ticket_id +
                ", image='" + image + '\'' +
                '}';
    }
}
