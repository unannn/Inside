package unannn.inside.web.valid;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UsernameValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface Username {
    String message() default "유저 아이디";
    Class[] groups() default {};
    Class[] payload() default {};
}
