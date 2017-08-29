package com.graphql.objects;

import java.util.Map;

/**
 * Created by tsilva on 8/19/17.
 */
public interface GraphExecutor {

    Map execute(String query);
}
