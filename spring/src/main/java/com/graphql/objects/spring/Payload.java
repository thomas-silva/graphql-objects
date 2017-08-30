package com.graphql.objects.spring;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by tsilva on 8/29/17.
 */
@Data
public class Payload {

    @NotEmpty
    private String query;
}
