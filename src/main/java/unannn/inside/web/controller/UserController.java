package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.application.Application;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.domain.user.UserRepository;
import unannn.inside.web.dto.JoinDto;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserRepository userRepository;

    private final EntityManager em;

    @Transactional
    @GetMapping
    public String userMain(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        User loginUser = em.merge(principalDetails.getUser()); //준영속 상태의 엔티티를 다시 영속상태로 변경

        model.addAttribute("name", loginUser.getName());
        model.addAttribute("applications", loginUser.getApplications());
        model.addAttribute("recruitments", loginUser.getRecruitments());
        return "userMain";
    }


}
