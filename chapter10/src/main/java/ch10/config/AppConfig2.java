package ch10.config;

import ch10.ApplicationConversionServiceFactoryBean;
import ch10.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Locale;

@Configuration
@ComponentScan(
        basePackages = "ch10",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = AppConfig.class))
public class AppConfig2 {

    @Autowired
    ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public Singer john() throws ParseException, MalformedURLException {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(conversionService.getDateTimeFormatter()
                .parse("1977-10-16", Locale.ENGLISH));
        singer.setPersonalSite(new URL("http://johnmayer.com/"));
        return singer;
    }
}
