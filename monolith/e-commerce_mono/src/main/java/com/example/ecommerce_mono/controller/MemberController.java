package com.example.ecommerce_mono.controller;

import com.example.ecommerce_mono.dto.MemberFormDto;
import com.example.ecommerce_mono.entity.Member;
import com.example.ecommerce_mono.entity.Order;
import com.example.ecommerce_mono.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member-service")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(MemberFormDto memberFormDto) {
        Member member = Member.createMember(memberFormDto);
        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberLoginForm";
    }

    @PostMapping(value = "/login")
    public String loginMember(MemberFormDto memberFormDto,Model model) {
        Member member = memberService.findByUserId(memberFormDto.getUserId());
        if (member == null){
            model.addAttribute("message", "없는 회원입니다.");
            return "members/memberLoginError";
        } else if (!member.getPwd().equals(memberFormDto.getPwd())) {
            model.addAttribute("message", "잘못된 비밀번호입니다.");
            return "members/memberLoginError";
        }
        model.addAttribute("userId",memberFormDto.getUserId());
        return "orders/main";
    }

    @GetMapping(value = "/{userId}")
    public String getOrderList(@PathVariable("userId") String userId,Model model){
        List<Order> orders = memberService.getOrders(userId);
        model.addAttribute("orderList", orders);
        return "orders/orderList";
    }
}
