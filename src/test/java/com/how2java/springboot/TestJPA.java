package com.how2java.springboot;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: TestJPA
 * Package: com.how2java.springboot
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/15 10:00
 * @Version 1.0
 */
@SpringBootTest
public class TestJPA {
    @Autowired
    CategoryDAO categoryDAO;

    @Test
    @Disabled
    public void testCategory(){
       List<Category> cs = categoryDAO.findAll();
       for(Category c:cs){
           System.out.println(c.getName());
       }
    }

    @BeforeEach
    public void before(){
        List<Category> cs = categoryDAO.findAll();
        for(Category c:cs){
            categoryDAO.delete(c);
        }
        for (int i = 0; i < 10; i++) {
            Category c= new Category();
            c.setName("Category "+i);
           categoryDAO.save(c);
        }
    }
    @Test
    public void test1(){

        List<Category> cs = categoryDAO.findAll();
        System.out.println("All category information:");
        for(Category c:cs){
            System.out.println(c.getName());
        }
        System.out.println();

    }

    @Test
    public void test2(){
        System.out.println("Query category with name \"Category 1\"");
        List<Category> cs = categoryDAO.findByName("Category 1");
        for(Category c : cs){
            System.out.println("c.getName()" + c.getName());
        }
        System.out.println();
    }
    @Test
    public void test3(){
        System.out.println("query by name, id greater than 5, and sort by name asc");
        List<Category> cs = categoryDAO.findByNameLikeOrIdGreaterThanOrderByNameAsc("%3%",
                5);
        for(Category c : cs){
            System.out.println(c);
        }
        System.out.println();
    }
}
