package top.suyiiyii.guavalearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.suyiiyii.guavalearn.mapper")
public class GuavaLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuavaLearnApplication.class, args);
    }

}
