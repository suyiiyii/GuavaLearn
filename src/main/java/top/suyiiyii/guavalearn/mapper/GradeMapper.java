package top.suyiiyii.guavalearn.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.guavalearn.models.Grade;

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

    @Select("select * from grade where studentid = #{studentid}")
    public Grade getGradeById(String studentid);

}
