package com.it.competition.controller;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Student;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.CompetitionService;
import com.it.competition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ApplyinfoService applyinfoService;
    @Autowired
    private StudentService studentService;

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("competitions", competitionService.getCompetitions());
        return "admin/index";
    }

    @GetMapping("apply")
    public String apply(Model model) {
        model.addAttribute("applyInfos", applyinfoService.getApplyInfos());
        return "admin/applyInfos";
    }

    @GetMapping("passCompetition")
    public String passCompetition(String id) {
        competitionService.setCompetitionState(Integer.valueOf(id), true);
        return "redirect:/admin/index";
    }

    @GetMapping("passApplyInfo")
    public String passApplyInfo(String id) {
        applyinfoService.setApplyInfoState(Integer.valueOf(id), true);
        return "redirect:/admin/apply";
    }

    @GetMapping("deleteApplyInfo")
    public String deleteApplyInfo(String id) {
        applyinfoService.deleteApplyInfo(Integer.valueOf(id));
        return "redirect:/admin/apply";
    }

    @GetMapping("applyInfo")
    public String applyInfo(String id, Model model) {
        List<Applyinfo> applyInfos = applyinfoService.getApplyInfosByCompetitionId(Integer.valueOf(id));
        model.addAttribute("applyInfos", applyInfos);
        return "admin/applyInfos";
    }

    @GetMapping("student")
    public String student(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "admin/student";
    }
}
