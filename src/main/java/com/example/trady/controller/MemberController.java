package com.example.trady.controller;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import com.example.trady.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }


    @PostMapping("/members/create")
    public String createUser(MemberForm memberForm) {
        Member member = memberForm.toEntity();
        member.logInfo();

        //User saved = userRepository.save(user);
        Member saved = memberRepository.save(member);
        saved.logInfo();

        return "redirect:/members/" + saved.getUserid();
    }


    @GetMapping("/members/all")
    public String allUsers(Model model) {
        Iterable<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members/all";
    }


    @GetMapping("/members/{userid}")
    public String showUser(@PathVariable("userid") Long userid, Model model) {
        log.info("userid = {} ", userid);


        Member member =  memberRepository.findById(userid).orElse(null);
        model.addAttribute("member", member);

        return "members/show";
    }


    @GetMapping("/members/{userid}/edit")
    public String editUser(@PathVariable("userid") Long userid, Model model) {
        Member member = memberRepository.findById(userid).orElse(null);
        model.addAttribute("member", member);

        return "members/edit";
    }


    @PostMapping("/members/update")
    public String updateUser(MemberForm memberForm) {
        log.info(memberForm.toString());

        Member memberEntity = memberForm.toEntity();
        log.info(memberEntity.toString());

        Member target = memberRepository.findById(memberEntity.getUserid()).orElse(null);

        if (target != null) {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getUserid();
    }

    @GetMapping("/members/{userid}/delete")
    public String deleteUser(@PathVariable("userid") Long userid, RedirectAttributes rttr) {
        Member target = memberRepository.findById(userid).orElse(null);

        if (target != null) {
            memberRepository.deleteById(userid);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }

        return "redirect:/members/all";
    }
}
