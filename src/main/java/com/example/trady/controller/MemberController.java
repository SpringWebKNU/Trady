//package com.example.trady.controller;
//
//import com.example.trady.dto.MemberForm;
//import com.example.trady.entity.Member;
//import com.example.trady.repository.MemberRepository;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Slf4j
//@Controller
//public class MemberController {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @GetMapping("/")
//    public String home(HttpSession session, Model model) {
//        Boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");
//
//        if (loggedInUser == null) {
//            loggedInUser = false;
//        }
//
//        // 로그인된 사용자 정보도 추가
//        Member currentUser = (Member) session.getAttribute("currentUser");
//        model.addAttribute("loggedInUser", loggedInUser);
//        model.addAttribute("currentUser", currentUser);
//
//        return "index"; // home 페이지
//    }
//
//
//
//
//    @GetMapping("/members/login")
//    public String memberLoginForm() {
//        return "members/login";
//    }
//
//    @PostMapping("/members/login")
//    public String login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            HttpSession session,
//            Model model,
//            RedirectAttributes redirectAttributes
//    ) {
//        // 사용자 조회
//        Member member = memberRepository.findByUsername(username);
//
//        // 로그인 검증
//        if (member != null && member.getPassword().equals(password)) {
//            // 세션에 로그인 상태와 사용자 정보 저장
//            session.setAttribute("loggedInUser", true);  // 로그인 상태 저장
//            session.setAttribute("currentUser", member); // 로그인한 사용자 정보 저장
//
//            // 로그인 성공 메시지
//            redirectAttributes.addFlashAttribute("msg", "로그인 성공!");
//
//            return "redirect:/products/all"; // 로그인 성공 시 제품 목록 페이지로 리다이렉트
//        } else {
//            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "members/login"; // 로그인 실패 시 로그인 페이지로 다시 이동
//        }
//    }
//
//
//
//
//    @PostMapping("/members/logout")
//    public String logout(HttpSession session) {
//        // 로그아웃 시 세션 상태 출력
//        log.info("로그아웃 전 loggedInUser = {}", session.getAttribute("loggedInUser"));
//
//        // 세션 무효화
//        session.invalidate(); // 세션 초기화
//
//        // 로그아웃 후 세션 상태 출력
//        log.info("로그아웃 후 loggedInUser = {}", session.getAttribute("loggedInUser"));
//
//        return "redirect:/"; // 홈 페이지로 리다이렉트
//    }
//
//
//
//    @GetMapping("/members/join")
//    public String newMemberJoinForm() {
//        return "members/join";
//    }
//
//    @PostMapping("/members/create")
//    public String createUser(MemberForm memberForm, Model model) {
//        Member member = memberForm.toEntity();
//        member.logInfo();
//
//        Member saved = memberRepository.save(member);
//        saved.logInfo();
//
//        model.addAttribute("username", saved.getUsername()); // Mustache 템플릿에 전달할 데이터
//
//        return "members/welcome"; // Welcome 페이지로 이동
//    }
//
//
////    @GetMapping("/members/all")
////    public String myPage(HttpSession session, Model model) {
////        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
////
////        if (loggedInUser != null) {
////            model.addAttribute("loggedInUser", loggedInUser);
////            return "members/mypage"; // 마이페이지 템플릿
////        } else {
////            return "redirect:/members/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
////        }
////    }
//
//    @GetMapping("/members/mypage")
//    public String allUsers(Model model) {
//        Iterable<Member> members = memberRepository.findAll();
//
//        model.addAttribute("members", members);
//
//        return "members/mypage";
//    }
//
////    @GetMapping("/members/mypage")
////    public String myPage(HttpSession session, Model model) {
////        // 세션에서 로그인된 사용자 정보 가져오기
////        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
////
////        if (loggedInUser != null) {
////            model.addAttribute("loggedInUser", loggedInUser);
////            return "members/mypage"; // 마이페이지 템플릿
////        } else {
////            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
////            return "redirect:/members/mypage";
////        }
////    }
//
//
//
//    @GetMapping("/members/{userid}")
//    public String showUser(@PathVariable("userid") Long userid, Model model) {
//        log.info("userid = {} ", userid);
//
//
//        Member member =  memberRepository.findById(userid).orElse(null);
//        model.addAttribute("member", member);
//
//        return "members/show";
//    }
//
//
//
//
//    @GetMapping("/members/{userid}/edit")
//    public String editUser(@PathVariable("userid") Long userid, Model model) {
//        Member member = memberRepository.findById(userid).orElse(null);
//        model.addAttribute("member", member);
//
//        return "members/edit";
//    }
//
//
//    @PostMapping("/members/update")
//    public String updateUser(MemberForm memberForm) {
//        log.info(memberForm.toString());
//
//        Member memberEntity = memberForm.toEntity();
//        log.info(memberEntity.toString());
//
//        Member target = memberRepository.findById(memberEntity.getUserid()).orElse(null);
//
//        if (target != null) {
//            memberRepository.save(memberEntity);
//        }
//        return "redirect:/members/" + memberEntity.getUserid();
//    }
//
//    @GetMapping("/members/{userid}/delete")
//    public String deleteUser(@PathVariable("userid") Long userid, RedirectAttributes rttr) {
//        Member target = memberRepository.findById(userid).orElse(null);
//
//        if (target != null) {
//            memberRepository.deleteById(userid);
//            rttr.addFlashAttribute("msg", "삭제되었습니다.");
//        }
//
//        return "redirect:/members/all";
//    }
//}





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
