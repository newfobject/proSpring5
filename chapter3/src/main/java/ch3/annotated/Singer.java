package ch3.annotated;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("johnMayer")
@Trophy(name = {"grammy", "platinum disk"})
@DependsOn("gopher")
public class Singer implements ApplicationContextAware {
    private String name = "unknown";
    private String lyric = "I played a quick game of chess " +
            "with the salt and pepper shaker";
    private ApplicationContext ctx;
    private Guitar guitar;

    public Singer() {
    }

    public Singer(@Value("John Mayer") String name) {
        this.name = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.ctx = applicationContext;
    }

    public void sing() {
        guitar = ctx.getBean("gopher", Guitar.class);
        guitar.sing();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return Objects.equals(name, singer.name) &&
                Objects.equals(lyric, singer.lyric);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lyric);
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", lyric='" + lyric + '\'' +
                '}';
    }
}
