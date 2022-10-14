package project.management.userManagement.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserAdultValidator implements ConstraintValidator<AdultConstraint, Date> {

    @Override
    public void initialize(AdultConstraint birthdate) {
    }

    @Override
    public boolean isValid(Date birthdate,
      ConstraintValidatorContext cxt) {
    	Date date = new Date();
    	int age =date.getYear() - birthdate.getYear();
        return birthdate != null && age >= 18;
    }

}
