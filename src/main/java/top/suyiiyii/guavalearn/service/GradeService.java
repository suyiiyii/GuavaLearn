package top.suyiiyii.guavalearn.service;

import org.springframework.stereotype.Service;
import top.suyiiyii.guavalearn.models.Grade;

@Service
public class GradeService {
    public Grade getGradeByStudentid(String studentId) {
        return new Grade(studentId, 90);
    }
}
