package com.example.trady.dto;

import com.example.trady.entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @Slf4j
public class MemberForm {

    private Long userid;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String addr;

    public Member toEntity() {
        return new Member(userid, username, password, email, phonenumber, addr);
    }

    public void logInfo() {
        log.info("userid: {}, username: {}, password: {}, email: {}, phonenumber: {}, addr: {}", userid, username, password, email, phonenumber, addr);
    }

    public Long getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getAddr() {
        return addr;
    }
}
