package com.it.competition.service.impl;

import com.it.competition.dao.ScoreMapper;
import com.it.competition.domain.Score;
import com.it.competition.domain.ScoreExample;
import com.it.competition.service.ScoreService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> getAllScores(Integer competitionId) {
        ScoreExample example = new ScoreExample();
        ScoreExample.Criteria criteria = example.createCriteria();
        criteria.andCompetitionIdEqualTo(competitionId);
        return scoreMapper.selectByExample(example);
    }

    @Override
    public int addScore(Score score) {
        return scoreMapper.insert(score);
    }

    @Override
    public List<Score> batchImport(Integer id, MultipartFile result) {
        System.out.println(id);
        Workbook workbook;
        try {
            workbook = new HSSFWorkbook(result.getInputStream());
        } catch (OfficeXmlFileException | IOException e) {
            try {
                workbook = new XSSFWorkbook(result.getInputStream());
            } catch (Exception ex) {
                return null;
            }
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row rowHead = sheet.getRow(0);
        System.out.println(rowHead.getPhysicalNumberOfCells());
        if (rowHead.getPhysicalNumberOfCells() != 6) {
            System.out.println("表头数量不正确！");
            return null;
        }
        int totalRowNum = sheet.getLastRowNum();
        Score score;
        List<Score> scores = new ArrayList<>();
        for (int i = 1; i <= totalRowNum; i++) {
            Row row = sheet.getRow(i);
            score = new Score();
            score.setCompetitionId(id);
            Cell studentIdCell = row.getCell(0);
            studentIdCell.setCellType(CellType.NUMERIC);
            score.setStudentId((int)Math.round(studentIdCell.getNumericCellValue()));

            Cell studentNameCell = row.getCell(1);
            studentNameCell.setCellType(CellType.STRING);
            score.setStudentName(studentNameCell.getStringCellValue());

            Cell studentNumCell = row.getCell(2);
            studentNumCell.setCellType(CellType.STRING);
            score.setStudentNum(studentNumCell.getStringCellValue());

            Cell studentCollegeCell = row.getCell(3);
            studentCollegeCell.setCellType(CellType.STRING);
            score.setStudentCollege(studentCollegeCell.getStringCellValue());

            Cell studentPhoneCell = row.getCell(4);
            studentPhoneCell.setCellType(CellType.STRING);
            score.setStudentPhone(studentPhoneCell.getStringCellValue());

            Cell studentScoreCell = row.getCell(5);
            studentScoreCell.setCellType(CellType.STRING);
            score.setScore(studentScoreCell.getStringCellValue());

            scores.add(score);
            System.out.println(score);
        }
        return scores;
    }

    @Override
    public Score getScoreByCompetitionIdAndStudentId(Integer competitionId, Integer studentId) {
        ScoreExample example = new ScoreExample();
        ScoreExample.Criteria criteria = example.createCriteria();
        System.out.println(competitionId);
        System.out.println(studentId);
        criteria.andCompetitionIdEqualTo(competitionId).andStudentIdEqualTo(studentId);
        List<Score> scoreList = scoreMapper.selectByExample(example);
        System.out.println(scoreList);
        return (scoreList != null && scoreList.size() > 0) ? scoreList.get(0) : null;
    }
}
