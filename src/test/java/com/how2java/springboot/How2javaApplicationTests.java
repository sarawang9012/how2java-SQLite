package com.how2java.springboot;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class How2javaApplicationTests {
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	private ApplicationContext context;
	@Test
	void contextLoads() {
		assertNotNull(context);
		System.out.println(context.getClass().getName());
		int count = context.getBeanDefinitionCount();
		System.out.println("There are " + count +
				" beans in the application context.");
		Arrays.stream(context.getBeanDefinitionNames())
				.forEach(System.out::println);
	}
	@Test
	public void testJPA(){
		List<Category> cs = categoryDAO.findAll();
		for(Category c: cs){
			System.out.println("c.getName()"+c.getName());
		}

	}

}
