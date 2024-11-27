package com.example.trady.service;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import com.example.trady.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean login(String username, String password, HttpSession session) {
        Member member = memberRepository.findByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", true);
            session.setAttribute("currentUser", member);
            return true;
        }
        return false;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Override
    public Member createUser(MemberForm memberForm) {
        Member member = memberForm.toEntity();
        return memberRepository.save(member);
    }

    @Override
    public Member getUserById(Long userid) {
        return memberRepository.findById(userid).orElse(null);
    }

    @Override
    public Iterable<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    @Override
    public boolean updateUser(MemberForm memberForm) {
        Member memberEntity = memberForm.toEntity();
        Member target = memberRepository.findById(memberEntity.getUserid()).orElse(null);

        if (target != null) {
            memberRepository.save(memberEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long userid) {
        if (memberRepository.existsById(userid)) {
            memberRepository.deleteById(userid);
            return true;
        }
        return false;
    }
}
