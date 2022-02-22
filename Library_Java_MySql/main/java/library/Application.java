package library;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import library.view.Dashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class  Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        Dashboard dashboard = context.getBean(Dashboard.class);
        dashboard.initialize();
    }

//    @GetMapping("/hello")
//    public static String sayHello(@RequestParam(value = "myName", defaultValue = "World, I am Horia") String name) {
//        return String.format("Hello %s!", name);
//    }
}
