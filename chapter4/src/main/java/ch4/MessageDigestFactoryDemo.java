package ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactoryDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digesterFactoryBean", MessageDigester.class);
        digester.digest("Hello World!");

        ctx.close();
    }
}
