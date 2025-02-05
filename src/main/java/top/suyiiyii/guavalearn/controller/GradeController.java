package top.suyiiyii.guavalearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.guavalearn.dto.CommonResponse;
import top.suyiiyii.guavalearn.dto.GradeDto;
import top.suyiiyii.guavalearn.service.GradeService;
import top.suyiiyii.guavalearn.controller.exception.BusinessException;
import top.suyiiyii.guavalearn.result.Code;

@RestController
public class GradeController {
    @Autowired
    private GradeService gradeService;

    //TODO:找不到不要返回404
    @GetMapping("/grade/{studentId}")
    public GradeDto getGradeByStudentid(@PathVariable String studentId) {
        System.out.println("studentId = " + studentId);
        GradeDto gradeByStudentid = gradeService.getGradeByStudentid(studentId);
        if (gradeByStudentid == null) {
            throw BusinessException.of(Code.NOT_FOUND);
        }
        return gradeByStudentid;
    }

    @PostMapping("/grade")
    public CommonResponse addGrade(@RequestBody GradeDto grade) {
        gradeService.addGrade(grade);
        return new CommonResponse("添加成功");
    }
}

