package com.graphql.objects.spring;

import com.graphql.objects.GraphProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * Created by tsilva on 8/29/17.
 */
@AllArgsConstructor
public class GraphProviderFactory {

    private final ApplicationContext context;

    public GraphProvider graphProvider() {

        return new SpringGraphProvider(context);
    }
}
