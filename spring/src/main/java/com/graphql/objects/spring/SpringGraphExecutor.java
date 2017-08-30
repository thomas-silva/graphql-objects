package com.graphql.objects.spring;

import com.graphql.objects.GraphExecutor;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tsilva on 8/19/17.
 */

@Component
@AllArgsConstructor
public class SpringGraphExecutor implements GraphExecutor {

    private final GraphQL graphQL;

    private final GraphProviderFactory graphProviderFactory;

    @Override public Map execute(String query) {

        ExecutionResult result = graphQL.execute(query, graphProviderFactory.graphProvider());

        return result.getData();
    }
}
