package unannn.inside.domain.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import unannn.inside.domain.application.form.Text;
import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.recruitment.form.TextForm;
import unannn.inside.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ApplicationTest {


    @Autowired
    private TestEntityManager em;

    @Test
    public void Text_Answer_저장() throws Exception {
        //given
        User user = getTestUser();
        Recruitment recruitment = getTestRecruitment();
        Question textQuestion = getTextQuestion();
        recruitment.addQuestion(textQuestion);
        user.register(recruitment);

        String content = "안녕하세요, unannn 입니다.";
        Text text = new Text(textQuestion.getQuestionNumber(), content);
        Application application = new Application();

        //when
        application.storeAnswer(text);
        em.persist(application);
        em.flush();
        em.clear();

        //then
        Application findApplication = em.find(Application.class, application.getId());
        assertThat(findApplication.getId()).isEqualTo(application.getId());

        List<Answer> answers = findApplication.getAnswers();
        Answer answer = answers.stream().findFirst().orElse(null);
        System.out.println(answer);
        assertThat(answer).isNotNull();
        assertThat(answer.getId()).isEqualTo(text.getId());

        assertThat(answer.getQuestionNumber()).isEqualTo(textQuestion.getQuestionNumber());
    }

    private Recruitment getTestRecruitment() {
        Recruitment recruitment = Recruitment.builder()
                .title("과식동아리 모집")
                .description("과일을 잘 챙겨 먹읍시다")
                .startTime(LocalDateTime.of(2022, 5, 10, 9, 0))
                .endTime(LocalDateTime.of(2022, 5, 20, 17, 0))
                .build();
        return recruitment;
    }

    private Question getTextQuestion() {
        Question question = new TextForm(100,500);
        question.setQuestion(1,"이름이 뭐에요?");
        return question;
    }

    private User getTestUser() {
        return User.builder()
                .email("woooia1@gmail.com")
                .userName("이윤환")
                .phoneNumber("01012345678")
                .encodedPassword("인코딩된알수없는패스워드")
                .build();
    }
}