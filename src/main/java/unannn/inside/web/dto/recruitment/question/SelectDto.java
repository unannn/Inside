package unannn.inside.web.dto.recruitment.question;

import unannn.inside.domain.recruitment.form.SelectElement;
import unannn.inside.domain.recruitment.form.SelectForm;

import java.util.List;
import java.util.stream.Collectors;

public class SelectDto extends QuestionDto{
    private List<String> elements;

    SelectDto(SelectForm selectForm) {
        List<SelectElement> selectElements = selectForm.getElements();
        elements = selectElements.stream()
                .map(SelectElement::getElement)
                .collect(Collectors.toList());
    }

}
