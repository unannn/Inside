package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails.getUsername());
        return "userMain";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid JoinDto joinDto, BindingResult bindingResult, Model model) {

        model.asMap().entrySet().forEach(System.out::println);


        if(!joinDto.getPassword().equals(joinDto.getVerifyPassword())){
            bindingResult.reject("NotSamePassword");
        }


        if(bindingResult.hasErrors()){
            log.debug("errors = {}", bindingResult.getAllErrors());
            return "joinForm";
        }

        //검증 로직 수행


        User user = User.builder()
                .username(joinDto.getUsername())
                .encodedPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .phoneNumber(joinDto.getPhoneNumber())
                .build();


        userRepository.save(user);

        return "redirect:/login";
    }
}
