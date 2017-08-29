package com.graphql.objects.graph;

import com.graphql.objects.Graph;
import com.graphql.objects.GraphDataInjector;
import com.graphql.objects.domain.Customer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by tsilva on 8/17/17.
 */
@Graph
@Component
@Scope("prototype")
public class Query {

    public String info() {
        return "some interesting info";
    }

    public CustomerGraph customer(GraphDataInjector<CustomerGraph> injector) {

        Customer customer = new Customer();
        customer.firstName = "Thomas";
        customer.lastName = "Silva";

        return injector.graphWithData(customer);
    }
}
