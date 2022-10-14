package project.management.userManagement.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UserAdultValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultConstraint {
	String message() default "invalid birthdate, the user is not an adult person";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
