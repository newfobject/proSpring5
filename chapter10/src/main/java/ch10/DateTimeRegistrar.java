package ch10;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditorSupport;

public class DateTimeRegistrar implements PropertyEditorRegistrar {

    private DateTimeFormatter dateFormatter;

    public DateTimeRegistrar(String dateFormatter) {

        this.dateFormatter = DateTimeFormat.forPattern(dateFormatter);
    }

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(DateTime.class,
                new DateTimeEditor(dateFormatter));
    }

    private class DateTimeEditor extends PropertyEditorSupport {
        private DateTimeFormatter dateFormatter;

        public DateTimeEditor(DateTimeFormatter dateFormattter) {
            this.dateFormatter = dateFormattter;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(DateTime.parse(text, dateFormatter));
        }
    }
}
