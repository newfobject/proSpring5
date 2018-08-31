package ch12.config;

import ch12.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class HttpInvokerConfig {
    @Autowired
    SingerService singerService;

    @Bean(name = "/httpInvoker/singerService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        HttpInvokerServiceExporter invokerServiceExporter = new HttpInvokerServiceExporter();
        invokerServiceExporter.setService(singerService);
        invokerServiceExporter.setServiceInterface(SingerService.class);
        return invokerServiceExporter;
    }
}
