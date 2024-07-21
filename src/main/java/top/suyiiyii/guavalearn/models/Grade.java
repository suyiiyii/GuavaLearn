package top.suyiiyii.guavalearn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Grade {
    int id;
    String studentid;
    int grade;

    public Grade(String studentid, int grade) {
        this.studentid = studentid;
        this.grade = grade;
    }
}
