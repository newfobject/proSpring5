package ch10;

import ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.*;

public class MultipleConvServDemo {
    private static final Logger logger = LoggerFactory.getLogger(MultipleConvServDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);

        logger.info("Singer info: " + john);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        AnotherSinger anotherSinger = conversionService.convert(john, AnotherSinger.class);
        logger.info("Another singer info: " + anotherSinger);

        String[] stringArray = conversionService.convert("a,b,c", String[].class);
        if (stringArray != null) {
            logger.info(Arrays.toString(stringArray));
        }

        List<String> listString = new ArrayList<>();
        listString.add("a");
        listString.add("b");
        listString.add("c");

        Set<String> setString = conversionService.convert(listString, HashSet.class);
        for (String s : setString) {
            System.out.println(s);
        }
    }
}
