package com.graphql.objects.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tsilva on 8/29/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope("prototype")
@com.graphql.objects.Graph
public @interface Graph {
}
