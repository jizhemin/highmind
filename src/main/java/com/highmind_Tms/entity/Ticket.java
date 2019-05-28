package com.highmind_Tms.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class Ticket {
    private Long id;

    private Integer domainid;

    private Long type_id;

    private Long area_id;

    private String ticketcode;

    private String ticketname;
    
    private BigDecimal ticketprice;

    private String ticketclass;

    private Integer effectiveday;
    
    @DateTimeFormat(pattern = "HH:mm")
    @JSONField (format="HH:mm") 
    private Date stime;
    
    @DateTimeFormat(pattern = "HH:mm")
    @JSONField (format="HH:mm") 
    private Date etime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField (format="yyyy-MM-dd") 
    private Date sdate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField (format="yyyy-MM-dd") 
    private Date edate;

    private String day;

    private String holiday;

    private Integer serial;

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

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Long getArea_id() {
        return area_id;
    }

    public void setArea_id(Long area_id) {
        this.area_id = area_id;
    }

    public String getTicketcode() {
        return ticketcode;
    }

    public void setTicketcode(String ticketcode) {
        this.ticketcode = ticketcode == null ? null : ticketcode.trim();
    }

    public String getTicketname() {
        return ticketname;
    }

    public void setTicketname(String ticketname) {
        this.ticketname = ticketname == null ? null : ticketname.trim();
    }

    public BigDecimal getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(BigDecimal ticketprice) {
        this.ticketprice = ticketprice;
    }

    public String getTicketclass() {
        return ticketclass;
    }

    public void setTicketclass(String ticketclass) {
        this.ticketclass = ticketclass == null ? null : ticketclass.trim();
    }

    public Integer getEffectiveday() {
        return effectiveday;
    }

    public void setEffectiveday(Integer effectiveday) {
        this.effectiveday = effectiveday;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    // 一对一关系
    private TicketType ticketType;
    
    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    // 一对一关系
    private Area area;
    
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    // 一个票下面可以有多个景点
    private List<Spot> spots;
    
    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
    
    private TicketStock  ticketStock;

    
    public TicketStock getTicketStock() {
        return ticketStock;
    }

    
    public void setTicketStock(TicketStock ticketStock) {
        this.ticketStock = ticketStock;
    }
    
    public Long isstock;

    
    public Long getIsstock() {
        return isstock;
    }

    
    public void setIsstock(Long isstock) {
        this.isstock = isstock;
    }


    private Long stock_id;
    
    public Long getStock_id() {
        return stock_id;
    }

    public void setStock_id(Long stock_id) {
        this.stock_id = stock_id;
    }
    
}