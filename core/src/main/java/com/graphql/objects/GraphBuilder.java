package com.graphql.objects;

import com.sun.istack.internal.Nullable;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLScalarType;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
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

        final Class returnType = method.getReturnType();

        if (isGraph(returnType)) {
            return newFieldDefinition()
                            .name(method.getName())
                            .type(buildGraphQL(returnType))
                            .dataFetcher(environment -> {
                                GraphProvider provider = environment.getContext();
                                GraphDataInjector injector = provider.injector(returnType);
                                return invoke(method, provider, injector);
                            });
        } else {

            GraphQLScalarType scalarType = Optional.ofNullable(scalarType(returnType))
                                            .orElseThrow(() -> new RuntimeException("unsupported return type"));

            return newFieldDefinition()
                            .name(method.getName())
                            .type(scalarType)
                            .dataFetcher(environment -> invoke(method, environment.getContext()));
        }
    }

    private static boolean isGraph(Class possibleGraph) {

        return possibleGraph.isAnnotationPresent(Graph.class) ||
               Stream.of(possibleGraph.getAnnotations())
                     .anyMatch(annotation -> annotation.annotationType().isAnnotationPresent(Graph.class));
    }

    @Nullable private static GraphQLScalarType scalarType(Class scalarClass) {

        if (scalarClass == String.class) {
            return Scalars.GraphQLString;
        }
        else if (scalarClass == Integer.class) {
            return Scalars.GraphQLInt;
        }
        else if (scalarClass == Long.class) {
            return Scalars.GraphQLLong;
        }
        else if (scalarClass == Boolean.class) {
            return Scalars.GraphQLBoolean;
        }
        else if (scalarClass == Float.class || scalarClass == Double.class) {
            return Scalars.GraphQLFloat;
        }

        return null;
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
