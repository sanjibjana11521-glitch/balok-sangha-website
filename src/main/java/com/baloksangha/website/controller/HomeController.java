package com.baloksangha.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.baloksangha.website.service.MemberService;
import com.baloksangha.website.service.VisitCounterService;

@Controller
public class HomeController {

    private final VisitCounterService visitCounterService;
    private final MemberService memberService;

    public HomeController(VisitCounterService visitCounterService, MemberService memberService) {
        this.visitCounterService = visitCounterService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("visitorCount", visitCounterService.registerVisitAndGetTotal());
        model.addAttribute("memberCount", memberService.getAllMembers().size());
        return "index";
    }

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members";
    }
}
