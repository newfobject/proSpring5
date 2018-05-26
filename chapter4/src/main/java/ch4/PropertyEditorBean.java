package ch4;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class PropertyEditorBean {
    private byte[] bytes;
    private Character character;
    private Class cls;
    private Boolean trueOrFalse;
    private List<String> stringList;
    private Date date;
    private Float floatValue;
    private File file;
    private InputStream stream;
    private Locale locale;
    private Pattern pattern;
    private Properties properties;
    private String trimString;
    private URL url;

    public static void main(String[] args) throws IOException {
        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-01.xml");
        ctx.refresh();

        PropertyEditorBean bean = (PropertyEditorBean) ctx.getBean("builtInSample");
        ctx.close();
    }

    public void setBytes(byte[] bytes) {
        System.out.println("Setting bytes: " + Arrays.toString(bytes));
        this.bytes = bytes;
    }

    public void setCharacter(Character character) {
        System.out.println("Setting character: " + character);
        this.character = character;
    }

    public void setCls(Class cls) {
        System.out.println("Setting class: " + cls.getName());
        this.cls = cls;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        System.out.println("Setting boolean: " + trueOrFalse);
        this.trueOrFalse = trueOrFalse;
    }

    public void setStringList(List<String> stringList) {
        System.out.println("Setting string list: " + stringList.size());
        this.stringList = stringList;
    }

    public void setDate(Date date) {
        System.out.println("Setting date: " + date);
        this.date = date;
    }

    public void setFloatValue(Float floatValue) {
        System.out.println("Setting float: " + floatValue);
        this.floatValue = floatValue;
    }

    public void setFile(File file) {
        System.out.println("Setting file: " + file.getName());
        this.file = file;
    }

    public void setStream(InputStream stream) {
        System.out.println("Setting stream: " + stream);
        this.stream = stream;
    }

    public void setLocale(Locale locale) {
        System.out.println("Setting locale: " + locale);
        this.locale = locale;
    }

    public void setPattern(Pattern pattern) {
        System.out.println("Setting pattern: " + pattern);
        this.pattern = pattern;
    }

    public void setProperties(Properties properties) {
        System.out.println("Setting properties: " + properties);
        this.properties = properties;
    }

    public void setTrimString(String trimString) {
        System.out.println("Setting trim string: " + trimString);
        this.trimString = trimString;
    }

    public void setUrl(URL url) {
        System.out.println("Setting url: " + url);
        this.url = url;
    }

    public static class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

        @Override
        public void registerCustomEditors(PropertyEditorRegistry registry) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            registry.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
            registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        }
    }
}
