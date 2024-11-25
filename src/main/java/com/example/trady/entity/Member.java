package com.example.trady.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Entity @ToString @Slf4j @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userid;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String addr;


    public Member(Long userid, String username, String password, String email, String phonenumber, String addr) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.addr = addr;
    }

    public void logInfo(){
        log.info("userid: {}, username: {}, password: {}, email: {}, phonenumber: {}, addr: {}", userid, username, password, email, phonenumber, addr);
    }

}
