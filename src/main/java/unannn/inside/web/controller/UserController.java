package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;
import unannn.inside.web.dto.LoginDto;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final EntityManager em;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @GetMapping
    public String userForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        if(principalDetails == null){
            return "redirect:/login";
        }

        User loginUser = em.merge(principalDetails.getUser()); //준영속 상태의 엔티티를 다시 영속상태로 변경
        model.addAttribute("user", loginUser);
        return "mainForm";
    }

    @GetMapping("/login")
    public String loginForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) {
            return "redirect:/";
        }

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
