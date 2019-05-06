package com.it.competition.controller;

import com.alibaba.druid.util.StringUtils;
import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;
import com.it.competition.domain.Message;
import com.it.competition.domain.Student;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.CompetitionService;
import com.it.competition.service.MessageService;
import com.it.competition.service.StudentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("competition")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ApplyinfoService applyinfoService;
    @Autowired
    private MessageService messageService;

    @GetMapping("apply")
    public String apply(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        return "student/apply";
    }

    @PostMapping("apply")
    public String apply(String competitionId, String teamName, String teamLeaderNum, String studentsNum, HttpServletRequest request) {
        String studentNum;
        Student student;
        Applyinfo applyinfo;
        Competition competition = competitionService.getCompetitionById(Integer.valueOf(competitionId));
        List<Applyinfo> applyInfos = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(studentsNum); i++) {
            studentNum = request.getParameter("studentNum" + ( i + 1));
            if (studentNum != null) {
                student = studentService.getStudentByStudentNum(studentNum);
                if (student != null) {
                    applyinfo = new Applyinfo();
                    applyinfo.setCompetitionId(Integer.valueOf(competitionId));
                    applyinfo.setCompetitionTitle(competition.getTitle());
                    applyinfo.setCompetitionStartTime(competition.getStartTime());
                    applyinfo.setCompetitionEndTime(competition.getEndTime());
                    applyinfo.setStudentId(student.getId());
                    applyinfo.setStudentName(student.getStudentName());
                    applyinfo.setStudentNum(student.getStudentNum());
                    applyinfo.setStudentCollege(student.getCollege());
                    applyinfo.setStudentPhone(student.getPhone());
                    applyinfo.setTeamName(teamName);
                    System.out.println(Integer.valueOf(studentsNum));
                    if (StringUtils.equals(teamLeaderNum, student.getStudentNum()) || Integer.valueOf(studentsNum) == 1) {
                        applyinfo.setTeamLeader(true);
                    } else {
                        applyinfo.setTeamLeader(false);
                    }
                    applyinfo.setState(false);
                    applyInfos.add(applyinfo);
                }
            }
        }
        System.out.println(Arrays.toString(applyInfos.toArray()));
        competitionService.apply(applyInfos);
        return "redirect:/student/index";
    }

    @GetMapping("info")
    public String info(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        return "competitionInfo";
    }

    @GetMapping("print")
    public ResponseEntity<byte[]> print(String id, HttpServletRequest request) throws IOException {
        System.out.println("applyInfoId : " + id);
        File fileDir = new File(request.getServletContext().getRealPath("/resource/uploadFile/"));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(fileDir + "temp.pdf");
        Document document = new Document();
        PdfWriter writer;
        Applyinfo applyinfo = applyinfoService.getApplyInfo(Integer.valueOf(id));
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 12, Font.NORMAL);
            Font font16Bold = new Font(bfChinese, 16, Font.BOLD);
            Font font10 = new Font(bfChinese, 10, Font.NORMAL);
            PdfPTable table = new PdfPTable(3);
            PdfPCell titleCell = new PdfPCell(new Paragraph("准考证", font16Bold));
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setBorder(0);
            table.setWidths(new int[] { 10, 80, 10 });
            table.getDefaultCell().setBorder(0);
            table.addCell("");
            table.addCell(titleCell);
            table.addCell("");
            document.add(table);
            document.add(new Paragraph(applyinfo.getCompetitionTitle(), font));
            document.add(new Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(applyinfo.getCompetitionStartTime()), font));
            document.add(new Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(applyinfo.getCompetitionEndTime()), font));
            document.add(new Paragraph(applyinfo.getStudentName(), font));
            document.add(new Paragraph(applyinfo.getStudentNum(), font));
            document.add(new Paragraph(applyinfo.getStudentCollege(), font));
            document.add(new Paragraph(applyinfo.getStudentPhone(), font));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attchement;filename=" + applyinfo.getStudentNum() + ".pdf");
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, statusCode);
    }

    @GetMapping("upload")
    public String upload(String id, Model model) {
        Applyinfo applyInfo = applyinfoService.getApplyInfo(Integer.valueOf(id));
        model.addAttribute("applyInfo", applyInfo);
        return "student/upload";
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
        Applyinfo applyInfo = applyinfoService.getApplyInfo(Integer.valueOf(id));
        Competition competitionById = competitionService.getCompetitionById(applyInfo.getCompetitionId());
        if (competitionById.getStudentsNum() > 1) {
            List<Applyinfo> applyinfos = applyinfoService.getApplyInfosByCompetitionId(applyInfo.getCompetitionId(), applyInfo.getTeamName());
            for (Applyinfo applyinfo : applyinfos) {
                applyinfo.setResult(newFileName);
            }
            applyinfoService.updateApplyInfos(applyinfos);
        } else {
            applyInfo.setResult(newFileName);
            applyinfoService.updateApplyInfo(applyInfo);
        }
        return "redirect:/student/index";
    }

    @GetMapping("downloadData")
    public ResponseEntity<byte[]> downloadData(String id, HttpServletRequest request) throws IOException {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        File fileDir = new File(request.getServletContext().getRealPath("/resource/uploadFile/"));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(fileDir + File.separator + competitionById.getReferenceData());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attchement;filename=" + competitionById.getReferenceData());
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, statusCode);
    }

    @GetMapping("downloadResult")
    public ResponseEntity<byte[]> downloadResult(String id, HttpServletRequest request) throws IOException {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        File fileDir = new File(request.getServletContext().getRealPath("/resource/uploadFile/"));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(fileDir + File.separator + competitionById.getResult());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attchement;filename=" + competitionById.getResult());
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, statusCode);
    }

    @GetMapping("sendResult")
    public String sendResult(String id) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        List<Applyinfo> applyinfos = applyinfoService.getApplyInfosByCompetitionId(competitionById.getId());
        for (Applyinfo applyinfo : applyinfos) {
            Message message = new Message();
            message.setCompetitionId(competitionById.getId());
            message.setCompetitionTitle(competitionById.getTitle());
            message.setStudentId(applyinfo.getStudentId());
            message.setStudentName(applyinfo.getStudentName());
            message.setStudentNum(applyinfo.getStudentNum());
            message.setTeamName(applyinfo.getTeamName());
            StringBuilder content = new StringBuilder();
            content.append("亲爱的").append(applyinfo.getStudentName()).append("同学，你");
            if (competitionById.getStudentsNum() > 1) {
                content.append("所在的队伍 ").append(applyinfo.getTeamName()).append(" ");
            }
            content.append("参加的比赛 ").append(competitionById.getTitle()).append(" 已经公布成绩，请尽快下载查看！");
            message.setContent(content.toString());
            message.setState(false);
            message.setLinkTo(competitionById.getId().toString());
            messageService.send(message);
        }
        competitionById.setSendResult(true);
        competitionService.updateCompetition(competitionById);
        return "redirect:/admin/index";
    }
}
