package com.digitalNepal.demo.repository;

import com.digitalNepal.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
