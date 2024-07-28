package top.suyiiyii.guavalearn.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.guavalearn.models.Grade;

import java.util.List;

/*
grade 表结构
CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `studentid` varchar(255) NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 */
@Mapper
public interface GradeMapper {

    @Select("select * from grade where studentid = #{studentid} limit 1")
    Grade getGradeById(String studentid);

    @Insert("insert into grade (studentid, grade) values (#{studentid}, #{grade})")
    void addGrade(Grade grade);

    //TODO:xml方式
    @Insert({
            "<script>",
            "insert into grade (studentid, grade) values ",
            "<foreach collection='list' item='grade' separator=','>",
            "(#{grade.studentid}, #{grade.grade})",
            "</foreach>",
            "</script>"
    })
    void addGrades(List<Grade> grades);

    @Select("select * from grade")
    List<Grade> getAllGrades();
}
