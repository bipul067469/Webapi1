package com.digitalNepal.demo.repository;

import com.digitalNepal.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
