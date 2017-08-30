package sample.spring.app;

import com.graphql.objects.GraphExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.spring.app.graph.Query;

import java.util.Map;

/**
 * Created by tsilva on 8/29/17.
 */
@SpringBootApplication
@Configuration
@Slf4j
public class SampleSpringApplication {

    @Bean
    Class queryClass() {
        return Query.class;
    }

    public static void main(String ... args) {

        ApplicationContext context = SpringApplication.run(SampleSpringApplication.class, args);

        GraphExecutor graphExecutor = context.getBean(GraphExecutor.class);

        Map result = graphExecutor.execute("{ helloWorld }");

        log.info("result: {}", result);
    }
}
