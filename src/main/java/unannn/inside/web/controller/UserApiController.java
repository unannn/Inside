package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.RecruitmentDto;
import unannn.inside.web.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/recruitments")
    public List<RecruitmentDto> userRecruitmentList(@AuthenticationPrincipal PrincipalDetails userDetails) {
        return userService.getUserRecruitments(userDetails.getUser());
    }

    @PostMapping("/api/recruitments/new")
    public List<RecruitmentDto> newRecruitment(@AuthenticationPrincipal PrincipalDetails userDetails) {
        userService.addNewRecruitment(userDetails.getUser());
        return userService.getUserRecruitments(userDetails.getUser());
    }
}
