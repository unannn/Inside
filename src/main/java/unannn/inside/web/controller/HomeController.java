package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;
import unannn.inside.web.dto.LoginDto;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails == null){
            return "redirect:/login";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto("",""));
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model) {
        model.addAttribute("loginDto", loginDto);
        if (!loginDto.getUsername().isEmpty() && !loginDto.getPassword().isEmpty()) {
            bindingResult.reject("InvalidAccount");
        }
        return "loginForm";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinDto joinDto, BindingResult bindingResult, Model model) {

        if(!joinDto.getPassword().equals(joinDto.getVerifyPassword())){
            bindingResult.reject("NotSamePassword");
        }

        if(bindingResult.hasErrors()){
            log.debug("errors = {}", bindingResult.getAllErrors());
            return "joinForm";
        }

        User user = User.builder()
                .username(joinDto.getUsername())
                .name(joinDto.getName())
                .encodedPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .build();


        userRepository.save(user);

        return "redirect:/login";
    }
}
