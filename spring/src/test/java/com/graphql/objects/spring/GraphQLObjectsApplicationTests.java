package com.graphql.objects.spring;

import com.graphql.objects.GraphExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphQLObjectsApplication.class)
public class GraphQLObjectsApplicationTests {

	@Autowired
	private GraphExecutor executor;

	@Test
	public void contextLoads() {

		String query = "query { customer { firstName lastName } }";

		System.out.println(executor.execute(query));

		System.out.println(executor.execute(query));

		System.out.println(executor.execute(query));
	}
}
