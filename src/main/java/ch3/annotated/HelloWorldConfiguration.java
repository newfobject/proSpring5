package ch3.annotated;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring/app-context-annotation.xml"})
@Configuration
public class HelloWorldConfiguration {
}
