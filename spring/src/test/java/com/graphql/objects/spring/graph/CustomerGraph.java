package com.graphql.objects.spring.graph;

import com.graphql.objects.GraphData;
import com.graphql.objects.spring.Graph;
import com.graphql.objects.spring.domain.Customer;
import com.graphql.objects.spring.service.InfoService;

/**
 * Created by tsilva on 8/18/17.
 */
@Graph
public class CustomerGraph {

    private final InfoService infoService;

    private final Customer customer;

    public CustomerGraph(InfoService infoService, @GraphData Customer customer) {
        this.infoService = infoService;
        this.customer = customer;
    }

    public String firstName() {
        return customer.firstName + infoService.getInfo();
    }

    public String lastName() {
        return customer.lastName + infoService.getInfo();
    }
}
