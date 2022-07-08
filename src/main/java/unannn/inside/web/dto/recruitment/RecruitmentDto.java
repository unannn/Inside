package unannn.inside.web.dto.recruitment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.web.dto.recruitment.question.QuestionDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class RecruitmentDto {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<QuestionDto> questionDtos;

    @Builder
    public RecruitmentDto(UUID id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<QuestionDto> questionDtos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionDtos = questionDtos;
    }

    public static RecruitmentDto toDto(Recruitment recruitment) {

        return RecruitmentDto.builder()
                .id(recruitment.getId())
                .title(recruitment.getTitle())
                .description(recruitment.getDescription())
                .startTime(recruitment.getStartTime())
                .endTime(recruitment.getEndTime())
                .build();
    }
}
