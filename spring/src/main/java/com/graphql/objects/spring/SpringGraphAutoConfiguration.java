package com.graphql.objects.spring;

import com.graphql.objects.GraphBuilder;
import com.graphql.objects.GraphExecutor;
import graphql.GraphQL;
import graphql.execution.ExecutorServiceExecutionStrategy;
import graphql.execution.SimpleExecutionStrategy;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tsilva on 8/19/17.
 */
@Configuration
public class SpringGraphAutoConfiguration {

    @Bean public ThreadPoolExecutor executor() {

        return new ThreadPoolExecutor(
                        2, /* core pool size 2 thread */
                        2, /* max pool size 2 thread */
                        30, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(),
                        new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Bean public GraphQL graphQL(ThreadPoolExecutor executor, @Qualifier("queryClass") Class rootClass) {

        GraphQLSchema schema = GraphQLSchema.newSchema().query(GraphBuilder.buildGraphQL(rootClass)).build();

        return GraphQL.newGraphQL(schema)
                      .queryExecutionStrategy(new ExecutorServiceExecutionStrategy(executor))
                      .mutationExecutionStrategy(new SimpleExecutionStrategy())
                      .build();
    }

    @Bean public GraphExecutor graphExecutor(GraphQL graphQL, ApplicationContext context) {

        return new SpringGraphExecutor(graphQL, new GraphProviderFactory(context));
    }
}
