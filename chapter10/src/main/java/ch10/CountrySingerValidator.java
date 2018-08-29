package ch10;

import ch10.obj.Singer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public boolean isValid(Singer singer,
                           ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        if (singer.getGenre() != null && (singer.isCountrySinger() &&
                (singer.getLastName() == null || singer.getGender() == null))) {
            result = false;
        }
        return result;
    }
}
