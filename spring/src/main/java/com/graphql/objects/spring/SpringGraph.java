package com.graphql.objects.spring;

import com.graphql.objects.Graph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tsilva on 8/19/17.
 */
@Component
@Scope("prototype")
@Graph
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringGraph {
}
