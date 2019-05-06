package com.it.competition.controller;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;
import com.it.competition.domain.Organizer;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.CompetitionService;
import com.it.competition.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("organizer")
public class OrganizerController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ApplyinfoService applyinfoService;
    @Autowired
    private OrganizerService organizerService;

    @GetMapping("index")
    public String index(Model model, HttpSession session) {
        List<Competition> competitions = competitionService.getCompetitionsByOrganizerId((Integer) session.getAttribute("id"));
        model.addAttribute("competitions", competitions);
        return "organizer/index";
    }

    @GetMapping("create")
    public String create(String id, Model model) {
        if (id == null) {
            model.addAttribute("competition", new Competition());
        } else {
            model.addAttribute("competition", competitionService.getCompetitionById(Integer.valueOf(id)));
        }
        return "organizer/create";
    }

    @PostMapping("create")
    public String create(Competition competition, @RequestParam(value = "refFile", required = false) MultipartFile refFile, Model model, HttpServletRequest request) throws IOException {
        String newFileName = null;
        if (!refFile.isEmpty()) {
            String path = request.getServletContext().getRealPath("/resource/uploadFile/");
            File dir = new File(path);
            boolean dirExist = dir.exists() || dir.mkdirs();
            if (dirExist) {
                String originalFileName = refFile.getOriginalFilename();
                newFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileName.substring(originalFileName.lastIndexOf("."));
                File newFile = new File(path + "/" + newFileName);
                refFile.transferTo(newFile);
            }
        }
        Organizer organizer = organizerService.getOrganizer(competition.getOrganizerId());
        competition.setOrganizerName(organizer.getName());
        if (competition.getId() != null) {
            if (newFileName != null) {
                competition.setReferenceData(newFileName);
            }
            System.out.println("update -> " + competition.toString());
            competitionService.updateCompetition(competition);
        } else {
            if (newFileName != null) {
                competition.setReferenceData(newFileName);
            }
            competition.setState(false);
            System.out.println("create -> " + competition.toString());
            competitionService.createCompetition(competition);
        }
        return "redirect:/organizer/index";
    }

    @GetMapping("delete")
    public String delete(String id) {
        competitionService.delete(Integer.valueOf(id));
        return "redirect:/organizer/index";
    }

    @GetMapping("applyInfo")
    public String applyInfo(String id, Model model) {
        List<Applyinfo> applyInfos = applyinfoService.getApplyInfosByCompetitionId(Integer.valueOf(id));
        model.addAttribute("applyInfos", applyInfos);
        return "organizer/applyInfos";
    }

    @GetMapping("upload")
    public String upload(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        return "organizer/upload";
    }

    @PostMapping("upload")
    public String upload(String id, @RequestParam(value = "result", required = false) MultipartFile result, HttpServletRequest request) throws IOException {
        String newFileName = null;
        if (!result.isEmpty()) {
            String path = request.getServletContext().getRealPath("/resource/uploadFile/");
            File dir = new File(path);
            boolean dirExist = dir.exists() || dir.mkdirs();
            if (dirExist) {
                String originalFileName = result.getOriginalFilename();
                newFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileName.substring(originalFileName.lastIndexOf("."));
                File newFile = new File(path + "/" + newFileName);
                result.transferTo(newFile);
            }
        }
        if (newFileName != null) {
            Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
            competitionById.setResult(newFileName);
            competitionService.updateCompetition(competitionById);
        }
        return "redirect:/organizer/index";
    }
}
