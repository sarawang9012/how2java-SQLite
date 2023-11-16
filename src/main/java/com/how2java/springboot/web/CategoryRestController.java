package com.how2java.springboot.web;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CategoryRestController
 * Package: com.how2java.springboot.web
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/16 11:38
 * @Version 1.0
 */
@RestController
public class CategoryRestController {
    private CategoryDAO categoryDAO;
    @Autowired
    private StringRedisTemplate template;
    @Resource(name="stringRedisTemplate")
    private ListOperations<String, String> listOps;
    @Autowired
    private void setCategoryDAO(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    @GetMapping("/category")
    public List<Category> listCategory(@RequestParam(value="start", defaultValue = "0")int start, @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Category> page = categoryDAO.findAll(pageable);
        listOps.leftPush("categories",page.getContent().toString());
        for(Category c : page.getContent()){

            listOps.leftPush("categories",c.toString());
        }
        return page.getContent();
    }
   @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id")int id)throws Exception{
        Category c = categoryDAO.getReferenceById(id);
        System.out.println(c);
        return c;
    }
    @PutMapping("/category")
    public Category addCategory(@RequestBody Category category) throws Exception{
        System.out.println("Springboot accepts JSON format data: "+category);
        categoryDAO.save(category);
        return category;
    }
}
