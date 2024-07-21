package top.suyiiyii.guavalearn.models;

import lombok.Data;

@Data
public class Grade {
    int id;
    String name;
    int grade;

    public Grade(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}
