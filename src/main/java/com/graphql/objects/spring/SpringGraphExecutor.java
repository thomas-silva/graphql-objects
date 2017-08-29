package com.graphql.objects.spring;

import com.graphql.objects.GraphExecutor;
import com.graphql.objects.GraphProvider;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tsilva on 8/19/17.
 */

@Component
@AllArgsConstructor
public class SpringGraphExecutor implements GraphExecutor {

    private final ApplicationContext context;

    private final GraphQL graphQL;

    @Override public Map execute(String query) {

        ExecutionResult result = graphQL.execute(query, context.getBean(GraphProvider.class));

        return result.getData();
    }
}
