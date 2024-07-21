package top.suyiiyii.guavalearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.guavalearn.dto.GradeDto;
import top.suyiiyii.guavalearn.models.Grade;
import top.suyiiyii.guavalearn.service.GradeService;

@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/grade/{studentId}")
    public Grade getGradeByStudentid(@PathVariable String studentId) {
        System.out.println("studentId = " + studentId);
        return gradeService.getGradeByStudentid(studentId);
    }

    @PostMapping("/grade")
    public void addGrade(@RequestBody GradeDto grade) {
        gradeService.addGrade(grade);
    }
}

