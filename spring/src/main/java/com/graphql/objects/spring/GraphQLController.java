package com.graphql.objects.spring;

import com.graphql.objects.GraphExecutor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by tsilva on 8/30/17.
 */
@RestController
@AllArgsConstructor
public class GraphQLController {

    private final GraphExecutor graphExecutor;

    @PostMapping(path = "/graphql")
    public ResponseEntity graphql(@Valid @RequestBody Payload payload) {

        Map result = graphExecutor.execute(payload.getQuery());

        return ResponseEntity.ok(result);
    }
}
