package ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        Singer parent = (Singer) ctx.getBean("parent");
        Singer child = (Singer) ctx.getBean("child");

        System.out.println("parent:\n" + parent);
        System.out.println("child:\n" + child);
    }
}
