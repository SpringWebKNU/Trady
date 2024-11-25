package com.example.trady.controller;

import com.example.trady.dto.MemberForm;
import com.example.trady.entity.Member;
import com.example.trady.repository.MemberRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

//    @GetMapping("/")
//    public String home(HttpSession session, Model model) {
//        Boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            loggedInUser = false; // 비로그인 상태 처리
//        }
//        model.addAttribute("loggedInUser", loggedInUser);
//        return "index";
//    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 세션에서 로그인 상태 가져오기
        Boolean loggedInUser = (Boolean) session.getAttribute("loggedInUser");

        // 로그인 상태가 null이면 false 처리
        if (loggedInUser == null) {
            loggedInUser = false;
        }

        // 모델에 로그인 상태 전달
        model.addAttribute("loggedInUser", loggedInUser);
        return "index"; // index 페이지로 리턴
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
            Model model
    ) {
        Member member = memberRepository.findByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", true); // 로그인 상태 저장
            session.setAttribute("loggedInUser", member); // Member 객체를 저장
            return "redirect:/products/all";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "members/login";
        }
    }

//    @PostMapping("/members/login")
//    public String login(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            HttpSession session,
//            HttpServletRequest request,
//            RedirectAttributes redirectAttributes,
//            Model model
//    ) {
//        // 사용자 조회
//        Member member = memberRepository.findByUsername(username);
//        Member memberLogin = memberRepository.findByUsername(username);
//
//
//        if (member != null && member.getPassword().equals(password)) {
//            // 로그인 성공: 세션에 회원 정보 저장
//
//            //session.setAttribute(HttpSessionUtils.User_SESSION_KEY, member); // 로그인 상태 저장
//            session.setAttribute("currentUser", member); // 로그인한 사용자 정보 저장
//
//            model.addAttribute("msg", "로그인 성공");
//            return "redirect:/products/all"; // 홈 페이지로 리다이렉트
//        } else {
//            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "members/login"; // 로그인 페이지로 다시 이동
//        }
//    }

    @PostMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 초기화
        }

        return "redirect:/"; // 홈 페이지로 리다이렉트
    }







//    @GetMapping("/members/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/members/login";
//    }


    @GetMapping("/members/join")
    public String newMemberJoinForm() {
        return "members/join";
    }

    @PostMapping("/members/create")
    public String createUser(MemberForm memberForm, Model model) {
        Member member = memberForm.toEntity();
        member.logInfo();

        Member saved = memberRepository.save(member);
        saved.logInfo();

        model.addAttribute("username", saved.getUsername()); // Mustache 템플릿에 전달할 데이터

        return "members/welcome"; // Welcome 페이지로 이동
    }


//    @GetMapping("/members/all")
//    public String myPage(HttpSession session, Model model) {
//        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
//
//        if (loggedInUser != null) {
//            model.addAttribute("loggedInUser", loggedInUser);
//            return "members/mypage"; // 마이페이지 템플릿
//        } else {
//            return "redirect:/members/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
//        }
//    }

    @GetMapping("/members/mypage")
    public String allUsers(Model model) {
        Iterable<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members/mypage";
    }

//    @GetMapping("/members/mypage")
//    public String myPage(HttpSession session, Model model) {
//        // 세션에서 로그인된 사용자 정보 가져오기
//        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
//
//        if (loggedInUser != null) {
//            model.addAttribute("loggedInUser", loggedInUser);
//            return "members/mypage"; // 마이페이지 템플릿
//        } else {
//            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
//            return "redirect:/members/mypage";
//        }
//    }



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
