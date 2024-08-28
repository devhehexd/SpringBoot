package com.example.jpatest.product;

import com.example.jpatest.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findById(Member id);
    List<Product> findByNameLike(String name);
    List<Product> findByPriceBetween(int p1, int p2);
}
