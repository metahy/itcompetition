package com.it.competition.controller;

import com.it.competition.domain.Applyinfo;
import com.it.competition.domain.Competition;
import com.it.competition.domain.Score;
import com.it.competition.domain.Student;
import com.it.competition.service.ApplyinfoService;
import com.it.competition.service.CompetitionService;
import com.it.competition.service.ScoreService;
import com.it.competition.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
    @Autowired
    private ScoreService scoreService;

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
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
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

    @GetMapping("upload")
    public String upload(String id, Model model) {
        Competition competitionById = competitionService.getCompetitionById(Integer.valueOf(id));
        model.addAttribute("competition", competitionById);
        List<Score> scores = scoreService.getAllScores(Integer.valueOf(id));
        model.addAttribute("scores", scores);
        return "admin/upload";
    }

    @GetMapping("exportStudent")
    @ResponseBody
    public void exportStudent(HttpServletResponse response) {
        List<Student> students = studentService.getStudents();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("学生ID（请勿修改）");
        cell = row.createCell(1);
        cell.setCellValue("姓名（请勿修改）");
        cell = row.createCell(2);
        cell.setCellValue("学号（请勿修改）");
        cell = row.createCell(3);
        cell.setCellValue("学院（请勿修改）");
        cell = row.createCell(4);
        cell.setCellValue("电话（请勿修改）");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(student.getId());
            cell = row.createCell(1);
            cell.setCellValue(student.getStudentName());
            cell = row.createCell(2);
            cell.setCellValue(student.getStudentNum());
            cell = row.createCell(3);
            cell.setCellValue(student.getCollege());
            cell = row.createCell(4);
            cell.setCellValue(student.getPhone());
        }
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";
        //响应到客户端
        setResponse(response, workbook, fileName);
    }

    @GetMapping("exportApplyStudent")
    @ResponseBody
    public void exportApplyStudent(String id, HttpServletResponse response) {
        List<Applyinfo> applyinfos = applyinfoService.getApplyInfosByCompetitionId(Integer.valueOf(id));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("学生ID（请勿修改）");
        cell = row.createCell(1);
        cell.setCellValue("姓名（请勿修改）");
        cell = row.createCell(2);
        cell.setCellValue("学号（请勿修改）");
        cell = row.createCell(3);
        cell.setCellValue("学院（请勿修改）");
        cell = row.createCell(4);
        cell.setCellValue("电话（请勿修改）");
        cell = row.createCell(5);
        cell.setCellValue("成绩");
        for (int i = 0; i < applyinfos.size(); i++) {
            Applyinfo applyinfo = applyinfos.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(applyinfo.getStudentId());
            cell = row.createCell(1);
            cell.setCellValue(applyinfo.getStudentName());
            cell = row.createCell(2);
            cell.setCellValue(applyinfo.getStudentNum());
            cell = row.createCell(3);
            cell.setCellValue(applyinfo.getStudentCollege());
            cell = row.createCell(4);
            cell.setCellValue(applyinfo.getStudentPhone());
            cell = row.createCell(5);
            cell.setCellValue("");
        }
        String fileName = "学生报名表" + System.currentTimeMillis() + ".xls";
        //响应到客户端
        setResponse(response, workbook, fileName);
    }

    private void setResponse(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            try {
                try {
                    fileName = new String(fileName.getBytes(),"ISO8859-1");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                response.setContentType("application/octet-stream;charset=ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
                response.addHeader("Pargam", "no-cache");
                response.addHeader("Cache-Control", "no-cache");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
