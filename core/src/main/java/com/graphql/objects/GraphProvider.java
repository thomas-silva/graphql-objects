package com.graphql.objects;

/**
 * Created by tsilva on 8/18/17.
 */
public interface GraphProvider {

    <T> T obtainGraph(Class<T> graphClass, Object ... dependencies);

    <T> GraphDataInjector<T> injector(Class<T> graphClass);
}
