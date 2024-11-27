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

    public Member toEntity() {
        return new Member(userid, username, password, email);
    }

    public void logInfo() {
        log.info("userid: {}, username: {}, password: {}, email: {}", userid, username, password, email);
    }
}
