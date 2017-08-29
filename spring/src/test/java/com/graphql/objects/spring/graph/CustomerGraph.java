package com.graphql.objects.spring.graph;

import com.graphql.objects.Graph;
import com.graphql.objects.GraphData;
import com.graphql.objects.spring.domain.Customer;
import com.graphql.objects.spring.service.InfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by tsilva on 8/18/17.
 */
@Graph
@Component
@Scope("prototype")
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
