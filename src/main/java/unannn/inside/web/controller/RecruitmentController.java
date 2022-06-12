package unannn.inside.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller("/recruitment")
public class RecruitmentController {

    @GetMapping
    public String createForm() {
        return "recruitment/createForm";
    }
}
