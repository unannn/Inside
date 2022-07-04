package unannn.inside.web.dto.recruitment.question;

import unannn.inside.domain.recruitment.Question;
import unannn.inside.domain.recruitment.form.CheckboxElement;
import unannn.inside.domain.recruitment.form.CheckboxForm;
import unannn.inside.domain.recruitment.form.SelectElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckboxDto extends QuestionDto{
    private final List<String> elements;

    CheckboxDto(CheckboxForm checkboxForm) {
        List<CheckboxElement> selectElements = checkboxForm.getElements();
        this.elements = selectElements.stream()
                .map(CheckboxElement::getElement)
                .collect(Collectors.toList());
    }

}
