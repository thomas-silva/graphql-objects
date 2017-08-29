package com.graphql.objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphQLObjectsApplication.class)
public class GraphQLObjectsApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	public void contextLoads() {

		GraphExecutor executor = context.getBean(GraphExecutor.class);

		String query = "query { customer { firstName lastName } }";

		System.out.println(executor.execute(query));

		System.out.println(executor.execute(query));

		System.out.println(executor.execute(query));
	}
}
