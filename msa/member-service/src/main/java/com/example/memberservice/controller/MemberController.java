package com.example.memberservice.controller;

import com.example.memberservice.dto.MemberFormDto;
import com.example.memberservice.entity.Member;
import com.example.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "member-service/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberForm";
    }

    @PostMapping(value = "member-service/new")
    public String newMember(MemberFormDto memberFormDto, Model model) {
        Member member = Member.createMember(memberFormDto);
        memberService.saveMember(member);
        System.out.println("Id출력 "+member.getId());
        return "redirect:/";
    }

    @GetMapping(value = "member-service/login")
    public String loginMember(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberLoginForm";
    }

    @PostMapping(value = "member-service/login")
    public String loginMember(MemberFormDto memberFormDto,Model model) {
        Member member = memberService.findByUserId(memberFormDto.getUserId());
        if (member == null){
            model.addAttribute("message", "없는 회원입니다.");
            return "members/memberLoginError";
        } else if (!member.getPwd().equals(memberFormDto.getPwd())) {
            model.addAttribute("message", "잘못된 비밀번호입니다.");
            return "members/memberLoginError";
        }
        return "redirect:/";
    }
}
