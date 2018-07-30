package pl.coderslab.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OwnValidValidator implements ConstraintValidator<OwnValid, String> {

    private int param;

    @Override
    public void initialize(OwnValid ownValid) {
        this.param = ownValid.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isUpper = false;
        if (!s.isEmpty()) {
            if (Character.isUpperCase(s.charAt(param))) {
                isUpper = true;
            }
        }
        return isUpper;
    }
}
