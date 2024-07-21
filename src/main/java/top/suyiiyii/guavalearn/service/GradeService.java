package top.suyiiyii.guavalearn.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.guavalearn.dto.GradeDto;
import top.suyiiyii.guavalearn.mapper.GradeMapper;
import top.suyiiyii.guavalearn.models.Grade;

@Service
public class GradeService {

    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private GradeMapper gradeMapper;

    public Grade getGradeByStudentid(String studentId) {
        return gradeMapper.getGradeById(studentId);
    }

    public void addGrade(GradeDto grade) {
        System.out.println(grade);
        gradeMapper.addGrade(modelMapper.map(grade, Grade.class));
    }
}
