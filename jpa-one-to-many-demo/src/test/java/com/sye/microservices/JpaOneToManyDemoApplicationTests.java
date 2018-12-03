package com.sye.microservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sye.microservices.controller.PostController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaOneToManyDemoApplicationTests {

	@Autowired
	PostController postController;
	@Test
	public void contextLoads() {
		postController.createPostAndComments();
	}

}
