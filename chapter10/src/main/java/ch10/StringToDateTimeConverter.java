package ch10;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.PostConstruct;

public class StringToDateTimeConverter implements Converter<String, DateTime> {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    private DateTimeFormatter dateTimeFormat;
    private String datePattern = DEFAULT_PATTERN;

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dateTimeFormat = DateTimeFormat.forPattern(datePattern);
    }

    @Override
    public DateTime convert(String source) {
        return dateTimeFormat.parseDateTime(source);
    }
}
