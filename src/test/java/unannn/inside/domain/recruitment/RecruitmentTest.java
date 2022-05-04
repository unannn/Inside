package unannn.inside.domain.recruitment;

import jdk.swing.interop.SwingInterOpUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import unannn.inside.domain.recruitment.form.CheckboxForm;
import unannn.inside.domain.recruitment.form.SelectElement;
import unannn.inside.domain.recruitment.form.SelectForm;
import unannn.inside.domain.recruitment.form.TextForm;
import unannn.inside.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(value = false) //insert Query 볼려고
class RecruitmentTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void TextForm_생성() throws Exception {
        //given
        Question question = new TextForm(100,500);
        question.setQuestion(1,"이름이 뭐에요?");

        //when
        em.persist(question);
        Question findQuestion = em.find(Question.class, question.getId());

        //then
        assertThat(findQuestion.getQuestion()).isEqualTo(question.getQuestion());
    }

    @Test
    public void SelectForm_생성() throws Exception {
        //given
        List<String> elementNames = new ArrayList<>();
        elementNames.add("남성");
        elementNames.add("여성");

        SelectForm question = new SelectForm(elementNames);
        question.setQuestion(1, "성별 선택");

        //when
        em.persist(question);
        SelectForm findQuestion = em.find(SelectForm.class, question.getId());

        //then
        assertThat(findQuestion.getElements().size()).isEqualTo(question.getElements().size());
        assertThat(findQuestion.getQuestion()).isEqualTo(question.getQuestion());

    }
    
    @Test
    public void CheckBoxForm_생성() throws Exception {
        //given
        List<String> elementNames = new ArrayList<>();
        elementNames.add("사과");
        elementNames.add("바나나");
        elementNames.add("키위");
        elementNames.add("포도");

        Question question = new CheckboxForm(elementNames);
        question.setQuestion(1, "좋아하는 과일은?");

        //when
        em.persist(question);
        Question findQuestion = em.find(Question.class, question.getId());

        //then
        assertThat(findQuestion.getQuestion()).isEqualTo(question.getQuestion());
    }

    @Test
    public void Recruiment에_Question추가() throws Exception {
        //given
        Question question = new TextForm(100,500);
        question.setQuestion(1,"이름이 뭐에요?");
        Recruitment recruitment = getTestRecruitment();

        //when

        recruitment.addQuestion(question); //

        em.persist(recruitment);
        em.flush();
        em.clear();

        //then
        Recruitment findRecruitment = em.find(Recruitment.class, recruitment.getId());
        assertThat(findRecruitment.getId()).isEqualTo(recruitment.getId());

        List<Question> questions = findRecruitment.getQuestions();
        assertThat(questions.get(0).getQuestion()).isEqualTo(question.getQuestion());
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
}