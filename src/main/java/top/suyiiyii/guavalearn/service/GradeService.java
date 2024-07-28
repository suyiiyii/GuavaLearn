package top.suyiiyii.guavalearn.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.guavalearn.dto.GradeDto;
import top.suyiiyii.guavalearn.mapper.GradeMapper;
import top.suyiiyii.guavalearn.models.Grade;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private GradeMapper gradeMapper;

    public GradeDto getGradeByStudentid(String studentId) {
        return modelMapper.map(gradeMapper.getGradeById(studentId), GradeDto.class);
    }

    public void addGrade(GradeDto grade) {
        System.out.println(grade);
        gradeMapper.addGrade(modelMapper.map(grade, Grade.class));
    }

    public void addByCsvFile(FileReader file) throws IOException {
        List<Grade> grades = new ArrayList<>();
        CSVParser csvParser = new CSVParser(file, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        csvParser.forEach(record -> {
            Grade grade = new Grade();
            grade.setStudentid(record.get(0));
            grade.setGrade(Integer.parseInt(record.get(1)));
            grades.add(grade);
        });
        gradeMapper.addGrades(grades);
    }

    public List<Grade> getAllGrades() {
        return gradeMapper.getAllGrades();
    }
}
