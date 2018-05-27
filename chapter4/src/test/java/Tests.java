import ch4.*;
import ch4.config.DestructiveBeanConfigDemo;
import ch4.config.DestructiveBeanWithHook;
import ch4.config.MessageDigesterDemo;
import ch4.config.SingerConfigDemo;
import ch4.custom.CustomEditorExample;
import org.junit.Test;

public class Tests {

    @Test
    public void run() throws Exception {
        String[] args = {};
        DestructiveBean.main(args);
        DestructiveBeanWithInterface.main(args);
        DestructiveBeanWithInterface2.main(args);
        DestructiveBeanWithJSR250.main(args);
        NamedSingerDemo.main(args);
        Singer.main(args);
        SingerWithInterface.main(args);
        SingerWithJSR250.main(args);
        DestructiveBeanConfigDemo.main(args);
        DestructiveBeanWithHook.main(args);
        SingerConfigDemo.main(args);
        MessageDigestDemo.main(args);
        MessageDigesterDemo.main(args);
        AccessingFactoryBeans.main(args);
        MessageDigestFactoryDemo.main(args);
        PropertyEditorBean.main(args);
        CustomEditorExample.main(args);
        MessageSourceDemo.main(args);
        Publisher.main(args);
        ResourceDemo.main(args);
        JavaConfigXmlExample.main(args);
        JavaConfigExampleOne.main(args);
        JavaConfigSimpleExample.main(args);
        JavaConfigExampleTwo.main(args);
    }
}
