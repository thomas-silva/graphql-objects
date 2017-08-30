package com.graphql.objects.spring.graph;

import com.graphql.objects.GraphDataInjector;
import com.graphql.objects.spring.Graph;
import com.graphql.objects.spring.domain.Customer;

/**
 * Created by tsilva on 8/17/17.
 */
@Graph
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
