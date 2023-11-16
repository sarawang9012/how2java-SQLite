package com.how2java.springboot.pojo;

import jakarta.persistence.*;

import java.lang.annotation.Target;


/**
 * ClassName: Category
 * Package: com.how2java.springboot.pojo
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/9 17:48
 * @Version 1.0
 */
@Entity
@Table(name="category_")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return "Category [id=" + id + ", name="+ name + "]";
    }
}
