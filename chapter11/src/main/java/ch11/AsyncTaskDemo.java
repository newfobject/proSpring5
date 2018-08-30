package ch11;

import ch11.config.AppConfig;
import ch11.services.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTaskDemo {
    private static Logger logger =
            LoggerFactory.getLogger(AsyncTaskDemo.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

        for (int i = 0; i < 10; i++) {
            asyncService.asyncTask();
        }

        Future<String> future = asyncService.asyncWithReturn("John Mayer");
        Future<String> future1 = asyncService.asyncWithReturn("Eric Clapton");
        Future<String> future2 = asyncService.asyncWithReturn("BB king");
        Thread.sleep(6000);

        logger.info("Result1: " + future.get());
        logger.info("Result2: " + future1.get());
        logger.info("Result3: " + future2.get());


        System.in.read();
        ctx.close();
    }
}
