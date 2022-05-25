package unannn.inside.web.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        return value.matches("[a-z\\d!@#$%^&*_+=-]{8,20}")  || value.length() == 0;
    }
}
