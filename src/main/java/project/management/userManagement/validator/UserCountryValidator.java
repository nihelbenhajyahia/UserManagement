package project.management.userManagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserCountryValidator implements 
  ConstraintValidator<UserCountryConstraint, String> {

    @Override
    public void initialize(UserCountryConstraint country) {
    }

    @Override
    public boolean isValid(String country,
      ConstraintValidatorContext cxt) {
        return country != null && country.toLowerCase().equals("french".toLowerCase());
    }

}
