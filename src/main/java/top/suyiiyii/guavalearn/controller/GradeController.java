package top.suyiiyii.guavalearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.guavalearn.models.Grade;
import top.suyiiyii.guavalearn.service.GradeService;

@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/grade/{studentId}")
    public Grade getGradeByStudentid(@PathVariable String studentId) {
        return gradeService.getGradeByStudentid(studentId);
    }
}

