package sample.spring.app.graph;

import com.graphql.objects.Graph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by tsilva on 8/29/17.
 */
@Graph
@Component
@Scope("prototype")
public class Query {

    public String helloWorld() {
        return "hello world !";
    }
}
