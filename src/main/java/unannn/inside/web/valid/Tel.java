package unannn.inside.web.valid;


import javax.validation.Constraint;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = TelValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface Tel {
    String message() default "휴대폰 번호";
    Class[] groups() default {};
    Class[] payload() default {};
}
