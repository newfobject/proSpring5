package ch3.xml;

public class InjectSimpleConfig {
    private String name = "John Mayer";
    private int age = 39;
    private float height = 1.92f;
    private boolean programmer = false;
    private Long ageInSeconds = 1_241_401_112L;
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public float getHeight() {
        return height;
    }
    public boolean isProgrammer() {
        return programmer;
    }
    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
