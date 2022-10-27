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

    @GetMapping(value = "member-service/members")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberForm";
    }

    @PostMapping(value = "member-service/members")
    public String newMember(MemberFormDto memberFormDto, Model model) {
        Member member = Member.createMember(memberFormDto);
        memberService.saveMember(member);
        return "redirect:/";
    }
}
