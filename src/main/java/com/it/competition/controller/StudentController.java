package com.it.competition.controller;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Message;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private ApplyinfoService applyinfoService;
    @Autowired
    private MessageService messageService;

    @GetMapping("index")
    public String index(HttpSession session, Model model) {
        List<Applyinfo> applyinfos = applyinfoService.getApplyInfosByStudentId((Integer) session.getAttribute("id"));
        model.addAttribute("applyInfos", applyinfos);
        return "student/index";
    }

    @GetMapping("message")
    public String message(HttpSession session, Model model) {
//        int msgNum = messageService.getMessageNum((Integer) session.getAttribute("id"));
//        session.setAttribute("msg", msgNum);
        List<Message> unReadmessages = messageService.getUnReadMessages((Integer) session.getAttribute("id"));
        List<Message> readmessages = messageService.getReadMessages((Integer) session.getAttribute("id"));
        model.addAttribute("unReadmessages", unReadmessages);
        model.addAttribute("unReadmessagesNum", unReadmessages.size());
        model.addAttribute("readmessages", readmessages);
        model.addAttribute("readmessagesNum", readmessages.size());
        return "student/message";
    }

    @GetMapping("read")
    public String read(String id, Model model) {
        Message message = messageService.getMessage(Integer.valueOf(id));
        message.setState(true);
        messageService.update(message);
        model.addAttribute("message", message);
        return "student/read";
    }
}
