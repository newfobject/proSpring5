package ch10;

import ch10.config.AppConfig2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServDemo {
    private static Logger logger = LoggerFactory.getLogger(ConvFormatServDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig2.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("Singer info: " + john);
        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        logger.info("BirthDate of singer is : " +
                conversionService.convert(john.getBirthDate(), String.class));

        ctx.close();
    }
}
