package com.it.competition.controller;

import com.it.competition.domain.Competition;
import com.it.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("")
    public String index(Model model) {
        System.out.println(123);
        List<Competition> competitions = competitionService.getTrueCompetitions();
        model.addAttribute("competitions", competitions);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
