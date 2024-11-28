package com.example.trady.service;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import com.example.trady.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    @Autowired
    private MemberRepository memberRepository;

    public MemberServiceImpl() {
    }

    public boolean login(String username, String password, HttpSession session) {
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("loggedInUser", true);
            session.setAttribute("currentUser", (Object)null);
            session.setAttribute("isAdmin", true);
            return true;
        } else {
            Member member = this.memberRepository.findByUsername(username);
            if (member != null && member.getPassword().equals(password)) {
                session.setAttribute("loggedInUser", true);
                session.setAttribute("currentUser", member);
                session.setAttribute("isAdmin", false);
                return true;
            } else {
                return false;
            }
        }
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public Member createUser(MemberForm memberForm) {
        Member member = memberForm.toEntity();
        return (Member)this.memberRepository.save(member);
    }

    public Member getUserById(Long userid) {
        return (Member)this.memberRepository.findById(userid).orElse(null);
    }

    public Iterable<Member> getAllUsers() {
        return this.memberRepository.findAll();
    }

    public boolean updateUser(MemberForm memberForm) {
        Member memberEntity = memberForm.toEntity();
        Member target = (Member)this.memberRepository.findById(memberEntity.getUserid()).orElse(null);
        if (target != null) {
            this.memberRepository.save(memberEntity);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(Long userid) {
        if (this.memberRepository.existsById(userid)) {
            this.memberRepository.deleteById(userid);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}