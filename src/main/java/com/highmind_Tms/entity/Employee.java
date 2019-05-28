package com.highmind_Tms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Employee {
    private Long id;

    private Long domainid;

    private String name;

    private String email;

    private Long department_id;
    
    private String loginId;

    private Byte isLoginEnabled;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField (format="yyyy-MM-dd")  
    private Date birthday;

    private String qq;

    private Long sex;

    private String photo;

    private String password;
    
    private String tel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDomainid() {
        return domainid;
    }

    public void setDomainid(Long domainid) {
        this.domainid = domainid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public Byte getIsLoginEnabled() {
        return isLoginEnabled;
    }

    public void setIsLoginEnabled(Byte isLoginEnabled) {
        this.isLoginEnabled = isLoginEnabled;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    //一对一关系
    private EmployeeSet employeeSet;
    
    public EmployeeSet getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(EmployeeSet employeeSet) {
        this.employeeSet = employeeSet;
    }
    
    private List<Ticket> tickets;
    
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
   
}   