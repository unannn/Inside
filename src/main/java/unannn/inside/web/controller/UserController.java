package unannn.inside.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/user")
public class UserController {

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }
}
