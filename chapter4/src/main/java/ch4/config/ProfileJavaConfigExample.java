package ch4.config;

import ch4.Food;
import ch4.FoodProviderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ProfileJavaConfigExample {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("highschool");
        ((AnnotationConfigApplicationContext) ctx).register(
                KinderGartenConfig.class, HighSchoolConfig.class);
        ctx.refresh();

        FoodProviderService foodProviderService = ctx.getBean("foodProviderService", FoodProviderService.class);
        for (Food food : foodProviderService.provideLunchSet()) {
            System.out.println("Food " + food.getName());
        }
    }
}
