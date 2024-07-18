package top.suyiiyii.guavalearn.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthzContorller {

    @GetMapping("/healthz")
    public String healthz() {
        return "ok";
    }
}
