package com.graphql.objects.spring;

import com.graphql.objects.spring.graph.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tsilva on 8/29/17.
 */
@SpringBootApplication
@Configuration
public class GraphQLObjectsApplication {

    @Bean
    public Class queryClass() {
        return Query.class;
    }

    public static void main(String ... args) {
        SpringApplication.run(GraphQLObjectsApplication.class, args);
    }
}
