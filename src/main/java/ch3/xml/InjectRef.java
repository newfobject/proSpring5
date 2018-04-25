package ch3.xml;

import ch3.Oracle;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {
    private Oracle oracle;

    public void setOracle(Oracle oracle) {
        this.oracle = oracle;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring/app-context-xml.xml");
        context.refresh();

        InjectRef injectRef = (InjectRef) context.getBean("injectRef");
        System.out.println(injectRef);
        context.close();
    }

    @Override
    public String toString() {
        return oracle.defineMeaningOfLife();
    }
}
