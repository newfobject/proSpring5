package ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimpleSpel {

    private String name;
    private String age;
    private String height;
    private String programmer;
    private String ageInSeconds;

    public static void main(String[] args) {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("classpath:spring/app-context-xml.xml");
        context.refresh();

        InjectSimpleSpel simpleSpel = (InjectSimpleSpel)
                context.getBean("injectSimpleSpel");
        System.out.println(simpleSpel);
        context.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getProgrammer() {
        return programmer;
    }

    public void setProgrammer(String programmer) {
        this.programmer = programmer;
    }

    public String getAgeInSeconds() {
        return ageInSeconds;
    }

    public void setAgeInSeconds(String ageInSeconds) {
        this.ageInSeconds = ageInSeconds;
    }

    public String toString() {
        return "Name: " + name + "\n"
                + "Age: " + age + "\n"
                + "Age in Seconds: " + ageInSeconds + "\n"
                + "Height: " + height + "\n"
                + "Is Programmer?: " + programmer;
    }
}
