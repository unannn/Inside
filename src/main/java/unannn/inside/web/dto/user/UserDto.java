package unannn.inside.web.dto.user;

import lombok.Builder;
import unannn.inside.domain.application.Application;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String name;

    private List<Recruitment> recruitments;

    private List<Application> applications;

    public UserDto(User user) {
        this.name = user.getName();
        this.applications = user.getApplications();
        this.recruitments = user.getRecruitments();
    }
}
