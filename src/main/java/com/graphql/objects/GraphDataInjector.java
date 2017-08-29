package com.graphql.objects;

/**
 * Created by tsilva on 8/21/17.
 */
public interface GraphDataInjector<T> {

    T graphWithData(Object ... data);
}
