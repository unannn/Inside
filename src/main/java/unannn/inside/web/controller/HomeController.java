package unannn.inside.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unannn.inside.config.auth.PrincipalDetails;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails == null){
            return "redirect:/login";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

}
