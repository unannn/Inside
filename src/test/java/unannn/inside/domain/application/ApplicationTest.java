package unannn.inside.domain.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import unannn.inside.domain.application.form.Checkbox;
import unannn.inside.domain.application.form.CheckboxValue;
import unannn.inside.domain.application.form.Select;
import unannn.inside.domain.application.form.Text;
import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.Recruitment;
import unannn.inside.domain.recruitment.form.CheckboxForm;
import unannn.inside.domain.recruitment.form.SelectForm;
import unannn.inside.domain.recruitment.form.TextForm;
import unannn.inside.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class ApplicationTest {


    @Autowired
    private TestEntityManager em;

    @Test
    public void Text_저장() throws Exception {
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


    @Test
    public void CheckBox_Answer_저장() throws Exception {
        //given
        User user = getTestUser();
        Recruitment recruitment = getTestRecruitment();
        CheckboxForm checkboxQuestion = (CheckboxForm) getCheckboxQuestion();
        recruitment.addQuestion(checkboxQuestion);
        user.register(recruitment);

        String content = "안녕하세요, unannn 입니다.";
        Application application = new Application();

        List<String> elements = List.of("수박");
        Checkbox checkbox = new Checkbox(checkboxQuestion.getQuestionNumber(), elements);

        //when
        user.register(application);

        application.storeAnswer(checkbox);
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
        assertThat(answer.getId()).isEqualTo(checkbox.getId());

        assertThat(answer.getQuestionNumber()).isEqualTo(checkboxQuestion.getQuestionNumber());

        //Application 영속화할 때 CACADE를 통해 User 엔티티도 업데이트
        assertThat(user.getApplications().size()).isEqualTo(1);
    }

    @Test
    public void Select_Answer_저장() throws Exception {
        //given
        User user = getTestUser();
        Recruitment recruitment = getTestRecruitment();
        SelectForm selectQuestion = (SelectForm) getSelectQuestion();
        recruitment.addQuestion(selectQuestion);
        user.register(recruitment);

        Application application = new Application();

        Select select = new Select(selectQuestion.getQuestionNumber(), "남자");

        //when
        application.storeAnswer(select);
        user.register(application);
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
        assertThat(answer.getId()).isEqualTo(select.getId());

        assertThat(answer.getQuestionNumber()).isEqualTo(selectQuestion.getQuestionNumber());

        //Application 영속화할 때 CACADE를 통해 User 엔티티도 업데이트
        assertThat(user.getApplications().size()).isEqualTo(1);
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

    private Question getCheckboxQuestion() {
        List<String> elements = new ArrayList<>();
        elements.add("수박");
        elements.add("사과");
        elements.add("키위");
        Question question = new CheckboxForm(elements);
        question.setQuestion(1,"올해 먹은 과일 선택");
        return question;
    }

    private Question getSelectQuestion() {
        List<String> elementNames = new ArrayList<>();
        elementNames.add("남자");
        elementNames.add("여자");
        Question question = new SelectForm(elementNames);
        question.setQuestion(1,"성별?");
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