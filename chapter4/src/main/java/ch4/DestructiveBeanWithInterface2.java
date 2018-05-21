package ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBeanWithInterface2 {
    private File file;
    private String filePath;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.registerShutdownHook(); // useless since we're using ShutdownHookBean
        ctx.refresh();
        ctx.getBean("shutdownHook"); // or set default-lazy-init="false"
        ctx.getBean("destructiveBeanWithInterface2", DestructiveBeanWithInterface2.class);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");
        if (filePath == null) {
            throw new IllegalArgumentException(
                    "You must specify filePath property of " + DestructiveBeanWithInterface2.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Bean");
        if (!file.delete()) {
            System.err.println("Error: failed to delete file");
        }
        System.out.println("File exists: " + file.exists());
    }
}
