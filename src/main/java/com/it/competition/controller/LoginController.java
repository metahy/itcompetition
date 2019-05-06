package com.it.competition.controller;

import com.it.competition.domain.Admin;
import com.it.competition.domain.Message;
import com.it.competition.domain.Organizer;
import com.it.competition.domain.Student;
import com.it.competition.service.AdminService;
import com.it.competition.service.MessageService;
import com.it.competition.service.OrganizerService;
import com.it.competition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MessageService messageService;

    @GetMapping("organizer")
    public String organizer() {
        return "login/organizer";
    }

    @PostMapping("organizer")
    public String organizer(Organizer organizer, HttpSession session) {
        Organizer organizerDB = organizerService.login(organizer);
        if (organizerDB != null) {
            session.setAttribute("id", organizerDB.getId());
            session.setAttribute("name", organizerDB.getName());
            session.setAttribute("role", "organizer");
            return "redirect:/organizer/index";
        }
        return "login/organizer";
    }

    @GetMapping("student")
    public String student() {
        return "login/student";
    }

    @PostMapping("student")
    public String student(Student student, HttpSession session) {
        Student studentDB = studentService.login(student);
        if (studentDB != null) {
            session.setAttribute("id", studentDB.getId());
            session.setAttribute("num", studentDB.getStudentNum());
            session.setAttribute("name", studentDB.getStudentName());
            session.setAttribute("role", "student");
            List<Message> unReadmessages = messageService.getUnReadMessages(studentDB.getId());
            session.setAttribute("msg", unReadmessages.size());
            return "redirect:/";
        }
        return "login/student";
    }

    @GetMapping("admin")
    public String admin() {
        return "login/admin";
    }

    @PostMapping("admin")
    public String admin(Admin admin, HttpSession session) {
        Admin adminDB = adminService.login(admin);
        if (adminDB != null) {
            session.setAttribute("id", adminDB.getId());
            session.setAttribute("name", adminDB.getUserName());
            session.setAttribute("role", "admin");
            return "redirect:/admin/index";
        }
        return "login/admin";
    }
}
