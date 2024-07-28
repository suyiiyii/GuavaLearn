package top.suyiiyii.guavalearn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.guavalearn.models.User;
/*
create table guava_learn.user
(
    id              int auto_increment
        primary key,
    username        varchar(255) not null,
    hashed_password varchar(255) not null,
    email           varchar(255) null,
    phone           varchar(255) null,
    constraint user_pk
        unique (username),
    constraint user_username_uindex
        unique (username)
);


 */

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("insert into user (username, hashed_password, email, phone) values (#{username}, #{hashed_password}, #{email}, #{phone})")
    void addUser(User user);

}
