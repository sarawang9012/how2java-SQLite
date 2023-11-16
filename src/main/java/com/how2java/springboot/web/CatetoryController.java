package com.how2java.springboot.web;

import com.how2java.springboot.dao.CategoryDAO;
import com.how2java.springboot.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CatetoryController
 * Package: com.how2java.springboot.web
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/9 17:53
 * @Version 1.0
 */
@Controller
public class CatetoryController {
    CategoryDAO categoryDAO;

    public CatetoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }


    @GetMapping("/categories")
    public String listCategory(Model m,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        System.out.println("in get all method");
        start = start < 0 ? 0 : start;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Category> page = categoryDAO.findAll(pageable);
        System.out.println(page.getNumber());
        System.out.println(page.getNumberOfElements());
        System.out.println(page.getSize());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        int prev = page.hasPrevious()?page.getNumber()-1:0;
        int next = page.hasNext()? page.getNumber()+1:page.getTotalPages()-1;
        int pages = page.getTotalPages()-1;
        m.addAttribute("page",page.getContent());
        m.addAttribute("prev",prev);
        m.addAttribute("next",next);
        m.addAttribute("pages",pages);
        return "/categories";
    }

    @PostMapping("/categories")
    public String addCategory(Category c) throws Exception {
        System.out.println("in save method");
        categoryDAO.save(c);
        return "redirect:/categories";

    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
        categoryDAO.delete(c);
        return "redirect:/categories";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
        categoryDAO.save(c);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") int id, Model m) throws Exception{
        System.out.println("in get one method");
       Category c = categoryDAO.getReferenceById(id);
       m.addAttribute("c",c);
       return "editCategory";
    }

}

