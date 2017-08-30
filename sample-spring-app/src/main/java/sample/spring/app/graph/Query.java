package sample.spring.app.graph;

import com.graphql.objects.spring.Graph;

/**
 * Created by tsilva on 8/29/17.
 */
@Graph
public class Query {

    public String helloWorld() {
        return "hello world !";
    }
}
