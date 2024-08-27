package com.digitalNepal.demo.service;
import com.digitalNepal.demo.model.Tags;
import com.digitalNepal.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tags> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tags> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    public Tags saveTag(Tags tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
