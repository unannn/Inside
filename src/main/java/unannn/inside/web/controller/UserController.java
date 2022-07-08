package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.dto.user.JoinDto;
import unannn.inside.web.dto.user.LoginDto;
import unannn.inside.web.dto.user.UserDto;
import unannn.inside.web.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    /*
    * 유저 메인 페이지
    * */
    @GetMapping
    public String userForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        //인증되지 않은 사용자의 경우 로그인 페이지로 리다이렉트
        if(principalDetails == null){
            return "redirect:/login";
        }

        UserDto loginUserDto = userService.createUserFormAttribute(principalDetails.getUser());
        model.addAttribute("user", loginUserDto);

        return "mainForm";
    }

    @GetMapping("/login")
    public String loginForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        //인증된 사용자의 경우 메인페이지로 리다이렉트
        if (principalDetails != null) {
            return "redirect:/";
        }

        model.addAttribute("loginDto", new LoginDto("",""));

        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model) {

        //username 과 password 가 모두 입력되었는데 인증에 실패했을 경우
        if (!loginDto.getUsername().isEmpty() && !loginDto.getPassword().isEmpty()) {
            bindingResult.reject("InvalidAccount");
        }

        model.addAttribute("loginDto", loginDto);

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

        userService.join(joinDto);

        return "redirect:/login";
    }
}
