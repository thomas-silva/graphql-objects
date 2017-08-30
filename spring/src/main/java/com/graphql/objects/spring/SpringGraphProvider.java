package com.graphql.objects.spring;

import com.graphql.objects.GraphData;
import com.graphql.objects.GraphDataInjector;
import com.graphql.objects.GraphProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

import javax.annotation.PreDestroy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Created by tsilva on 8/18/17.
 */
@AllArgsConstructor
public class SpringGraphProvider implements GraphProvider {

    private final ConcurrentHashMap<Class, Object> graphs = new ConcurrentHashMap<>();

    private final ApplicationContext context;

    @Override public <T> T obtainGraph(Class<T> graphClass, Object ... dependencies) {

        if (!graphs.containsKey(graphClass)) {
            graphs.put(graphClass, context.getBean(graphClass, dependencies));
        }

        return (T) graphs.get(graphClass);
    }

    @Override public <T> GraphDataInjector<T> injector(Class<T> graphClass) {

        final Stream<Object> dependencies = Stream.of(graphClass.getConstructors()[0].getParameters())
                              .filter(parameter -> parameter.getAnnotation(GraphData.class) == null)
                              .map(parameter -> context.getBean(parameter.getType()));

        return data -> obtainGraph(graphClass, Stream.concat(dependencies, Stream.of(data)).toArray());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("bean is being destroyed...");
    }
}
