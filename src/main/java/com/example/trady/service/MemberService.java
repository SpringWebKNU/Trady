package com.example.trady.service;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import jakarta.servlet.http.HttpSession;

public interface MemberService {
    boolean login(String username, String password, HttpSession session);

    void logout(HttpSession session);

    Member createUser(MemberForm memberForm);

    Member getUserById(Long userid);

    Iterable<Member> getAllUsers();

    boolean updateUser(MemberForm memberForm);

    boolean deleteUser(Long userid);
}
