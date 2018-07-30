package pl.coderslab.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = OwnValidValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OwnValid {

    int value() default 0;

    String message() default "{ownValid.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

