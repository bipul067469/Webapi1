package com.digitalNepal.demo.repository;

import com.digitalNepal.demo.model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tags, Long> {
}
