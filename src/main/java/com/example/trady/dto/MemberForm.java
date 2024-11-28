package com.example.trady.dto;

import com.example.trady.entity.Member;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberForm {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MemberForm.class);
    private Long userid;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String addr;

    public Member toEntity() {
        return new Member(this.userid, this.username, this.password, this.email, this.phonenumber, this.addr);
    }

    public void logInfo() {
        log.info("userid: {}, username: {}, password: {}, email: {}, phonenumber: {}, addr: {}", new Object[]{this.userid, this.username, this.password, this.email, this.phonenumber, this.addr});
    }

    @Generated
    public Long getUserid() {
        return this.userid;
    }

    @Generated
    public String getUsername() {
        return this.username;
    }

    @Generated
    public String getPassword() {
        return this.password;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPhonenumber() {
        return this.phonenumber;
    }

    @Generated
    public String getAddr() {
        return this.addr;
    }

    @Generated
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Generated
    public void setUsername(String username) {
        this.username = username;
    }

    @Generated
    public void setPassword(String password) {
        this.password = password;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Generated
    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Generated
    public MemberForm() {
    }

    @Generated
    public MemberForm(Long userid, String username, String password, String email, String phonenumber, String addr) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.addr = addr;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getUserid();
        return "MemberForm(userid=" + var10000 + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + ", phonenumber=" + this.getPhonenumber() + ", addr=" + this.getAddr() + ")";
    }
}