package com.graphql.objects.spring.service;

import org.springframework.stereotype.Component;

/**
 * Created by tsilva on 8/17/17.
 */
@Component
public class InfoService {

    public String getInfo() {
        return "interesting info...";
    }
}
