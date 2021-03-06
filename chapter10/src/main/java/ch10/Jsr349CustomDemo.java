package ch10;

import ch10.config.AppConfig;
import ch10.obj.Genre;
import ch10.obj.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349CustomDemo {
    private static Logger logger =
            LoggerFactory.getLogger(Jsr349CustomDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidationService singerValidationService = context.getBean(SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);
        context.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService singerValidationService) {
        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("No. of violations: " + violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info("Validation error for property: " +
                    violation.getPropertyPath()
                    + " with value: " + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        }
    }
}
