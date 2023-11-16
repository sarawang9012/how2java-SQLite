package com.how2java.springboot.dao;

import com.how2java.springboot.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * ClassName: CategoryDAO
 * Package: com.how2java.springboot.dao
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/9 17:51
 * @Version 1.0
 */
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    public List<Category> findByName(String name);
    public List<Category> findByNameLikeAndIdGreaterThanOrderByNameAsc(String name, int id);
    public List<Category> findByNameLikeOrIdGreaterThanOrderByNameAsc(String name, int id);
}
