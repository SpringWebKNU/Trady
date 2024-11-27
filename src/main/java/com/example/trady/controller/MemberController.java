package com.example.trady.controller;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import com.example.trady.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        Boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");
        loggedInUser = loggedInUser != null && loggedInUser;
        Member currentUser = (Member) session.getAttribute("currentUser");

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("currentUser", currentUser);

        return "index";
    }

    @GetMapping("/members/login")
    public String memberLoginForm() {
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        boolean success = memberService.login(username, password, session);

        if (success) {
            redirectAttributes.addFlashAttribute("msg", "로그인 성공!");
            return "redirect:/products/all";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/login";
        }
    }

    @PostMapping("/members/logout")
    public String logout(HttpSession session) {
        memberService.logout(session);
        return "redirect:/";
    }

    @GetMapping("/members/join")
    public String newMemberJoinForm() {
        return "members/join";
    }

    @PostMapping("/members/create")
    public String createUser(MemberForm memberForm, Model model) {
        Member saved = memberService.createUser(memberForm);
        model.addAttribute("username", saved.getUsername());
        return "members/welcome";
    }

    @GetMapping("/members/mypage")
    public String allUsers(Model model) {
        Iterable<Member> members = memberService.getAllUsers();
        model.addAttribute("members", members);
        return "members/mypage";
    }

    @GetMapping("/members/{userid}")
    public String showUser(@PathVariable("userid") Long userid, Model model) {
        Member member = memberService.getUserById(userid);
        model.addAttribute("member", member);
        return "members/show";
    }

    @GetMapping("/members/{userid}/edit")
    public String editUser(@PathVariable("userid") Long userid, Model model) {
        Member member = memberService.getUserById(userid);
        model.addAttribute("member", member);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String updateUser(MemberForm memberForm) {
        boolean success = memberService.updateUser(memberForm);
        return success ? "redirect:/members/" + memberForm.getUserid() : "redirect:/members/mypage";
    }

    @GetMapping("/members/{userid}/delete")
    public String deleteUser(@PathVariable("userid") Long userid, RedirectAttributes rttr) {
        boolean success = memberService.deleteUser(userid);
        rttr.addFlashAttribute("msg", success ? "삭제되었습니다." : "삭제에 실패했습니다.");
        return "redirect:/members/all";
    }
}
