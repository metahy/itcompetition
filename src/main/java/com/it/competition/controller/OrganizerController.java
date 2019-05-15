package com.it.competition.controller;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;
import com.it.competition.domain.Organizer;
import com.it.competition.domain.Score;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.CompetitionService;
import com.it.competition.service.OrganizerService;
import com.it.competition.service.ScoreService;
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
import java.util.Objects;
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
    @Autowired
    private ScoreService scoreService;

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
    public String create(Competition competition, @RequestParam(value = "refFile", required = false) MultipartFile[] refFile, Model model, HttpServletRequest request) throws IOException {
        String newFileName = null;
        StringBuilder sb = new StringBuilder();
        if (refFile != null) {
            for (MultipartFile rf : refFile) {
                if (!rf.isEmpty()) {
                    String path = request.getServletContext().getRealPath("/resource/uploadFile/");
                    File dir = new File(path);
                    boolean dirExist = dir.exists() || dir.mkdirs();
                    if (dirExist) {
                        String originalFileName = rf.getOriginalFilename();
                        newFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileName.substring(originalFileName.lastIndexOf("."));
                        File newFile = new File(path + "/" + newFileName);
                        rf.transferTo(newFile);
                        sb.append(newFileName).append(",");
                    }
                }
            }
        }
        Organizer organizer = organizerService.getOrganizer(competition.getOrganizerId());
        competition.setOrganizerName(organizer.getName());
        String imgStr = sb.toString();
        if (competition.getId() != null) {
            if (newFileName != null) {
                competition.setReferenceData(imgStr.length() > 0 ? imgStr.substring(0, imgStr.length() - 1) : null);
            }
            System.out.println("update -> " + competition.toString());
            competitionService.updateCompetition(competition);
        } else {
            if (newFileName != null) {
                competition.setReferenceData(imgStr.length() > 0 ? imgStr.substring(0, imgStr.length() - 1) : null);
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
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        List<Applyinfo> applyInfos = applyinfoService.getApplyInfosByCompetitionId(Integer.valueOf(id));
        model.addAttribute("applyInfos", applyInfos);
        return "organizer/applyInfos";
    }

    @GetMapping("upload")
    public String upload(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        List<Score> scores = scoreService.getAllScores(Integer.valueOf(id));
        model.addAttribute("scores", scores);
        return "organizer/upload";
    }

    @PostMapping("upload")
    public String upload(String id, @RequestParam(value = "result", required = false) MultipartFile result) {
        //判断文件是否为空
        if (result == null) {
            return "redirect:/organizer/upload?id=" + id;
        }
        //获取文件名
        String name = result.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size = result.getSize();
        if (name == null || ("").equals(name) && size == 0) {
            return "redirect:/organizer/upload?id=" + id;
        }
        String extension = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
        if (!Objects.equals("xls", extension) && !Objects.equals("xlsx", extension)) {
            return "redirect:/organizer/upload?id=" + id;
        }
        //批量导入。参数：文件名，文件。
        List<Score> scoreList = scoreService.batchImport(Integer.valueOf(id), result);
        if (scoreList != null) {
            scoreList.forEach((score) -> scoreService.addScore(score));
        }
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        competitionById.setResult("tmp");
        competitionService.updateCompetition(competitionById);
        return "redirect:/organizer/upload?id=" + id;
    }

    @GetMapping("uploadData")
    public String uploadData(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        return "organizer/uploadData";
    }

    @PostMapping("uploadData")
    public String uploadData(String id, @RequestParam(value = "refFile", required = false) MultipartFile[] refFile, Model model, HttpServletRequest request) throws IOException {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        String newFileName = null;
        if (refFile != null) {
            StringBuilder sb = new StringBuilder();
            for (MultipartFile rf : refFile) {
                if (!rf.isEmpty()) {
                    String path = request.getServletContext().getRealPath("/resource/uploadFile/");
                    File dir = new File(path);
                    boolean dirExist = dir.exists() || dir.mkdirs();
                    if (dirExist) {
                        String originalFileName = rf.getOriginalFilename();
                        newFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileName.substring(originalFileName.lastIndexOf("."));
                        File newFile = new File(path + "/" + newFileName);
                        rf.transferTo(newFile);
                        sb.append(newFileName).append(",");
                    }
                }
            }
            if (competitionById != null && newFileName != null) {
                String imgStr = sb.toString();
                String before = competitionById.getReferenceData();
                if (before != null) {
                    imgStr = before + "," + imgStr;
                }
                competitionById.setReferenceData(imgStr.length() > 0 ? imgStr.substring(0, imgStr.length() - 1) : null);
                competitionService.updateCompetition(competitionById);
            }
        }
        return "redirect:/organizer/uploadData?id=" + id;
    }
}
