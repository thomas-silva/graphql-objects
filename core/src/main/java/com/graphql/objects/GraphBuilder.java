package com.graphql.objects;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by tsilva on 8/18/17.
 */
public class GraphBuilder {

    public static GraphQLObjectType buildGraphQL(Class graphClass) {

        final GraphQLObjectType.Builder builder = newObject()
                        .name(graphClass.getSimpleName().replace("Graph", ""));

        Stream.of(graphClass.getDeclaredMethods())
                  .filter(method -> Modifier.isPublic(method.getModifiers()))
                  .forEach(method -> builder.field(toFieldDefinition(method)));

        return builder.build();
    }

    private static GraphQLFieldDefinition.Builder toFieldDefinition(Method method) {

        if (method.getReturnType().isAnnotationPresent(Graph.class)) {
            return newFieldDefinition()
                            .name(method.getName())
                            .type(buildGraphQL(method.getReturnType()))
                            .dataFetcher(environment -> {
                                GraphProvider provider = environment.getContext();
                                GraphDataInjector injector = provider.injector(method.getReturnType());
                                return invoke(method, provider, injector);
                            });
        } else {
            return newFieldDefinition()
                            .name(method.getName())
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment -> invoke(method, environment.getContext()));
        }
    }

    private static Object invoke(Method method, GraphProvider graphProvider, Object ... args) {
        try {
            return method.invoke(graphProvider.obtainGraph(method.getDeclaringClass()), args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
