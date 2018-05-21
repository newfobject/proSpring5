package ch4.config;

import ch4.DestructiveBeanWithJSR250;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

public class DestructiveBeanConfigDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DestructiveBeanConfig.class);
        DestructiveBeanWithJSR250 bean = ctx.getBean(DestructiveBeanWithJSR250.class);
        System.out.println("calling destroy");
        ctx.close();
        System.out.println("called destroy");
    }

    @Configuration
    static class DestructiveBeanConfig {
        @Lazy
        @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
        DestructiveBeanWithJSR250 getDestructiveBean() {
            DestructiveBeanWithJSR250 destructiveBean = new DestructiveBeanWithJSR250();
            destructiveBean.setFilePath(System.getProperty("java.io.tmpdir") +
                    System.getProperty("file.separator") + "test.txt");
            return destructiveBean;
        }
    }
}
