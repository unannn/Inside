package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;
import unannn.inside.web.dto.recruitment.RecruitmentDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/recruitments")
@Controller
public class RecruitmentController {

    /*
     * 모집 공고 수정 페이지
     * */
    @GetMapping("/{recruitmentId}/edit")
    @ResponseBody
    public String editForm(Model model, @PathVariable UUID recruitmentId) {
        model.addAttribute("recruitmentDto", new RecruitmentDto());
        return "recruitment/createForm";
    }

    @PostMapping("/test")
    public @ResponseBody String test(HttpServletRequest request) {
        String token = request.getHeader("X-CSRF-TOKEN");
        log.info("token = {}", token);
        return "hello jquery";
    }
}
