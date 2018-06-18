package ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml-03.xml");
        ctx.refresh();

        NewDocumentarist documentarist = ctx.getBean(
                "documentarist", NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
