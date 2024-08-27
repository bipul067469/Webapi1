package com.digitalNepal.demo.controller;

import com.digitalNepal.demo.model.Blog;
import com.digitalNepal.demo.model.Category;
import com.digitalNepal.demo.model.Tags;
import com.digitalNepal.demo.service.BlogService;
import com.digitalNepal.demo.service.CategoryService;
import com.digitalNepal.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blog/list";
    }

    @GetMapping("/create")
    public String createBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());
        return "blog/form";
    }

    @PostMapping
    public String saveBlog(@ModelAttribute Blog blog, @RequestParam("categoryId") Long categoryId, @RequestParam("tagIds") Set<Long> tagIds) {
        blog.setCategory(categoryService.getCategoryById(categoryId).orElseThrow());
        Set<Tags> tags = new HashSet<>();
        tagIds.forEach(tagId -> tags.add(tagService.getTagById(tagId).orElseThrow()));
        blog.setTags(tags);
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/edit")
    public String editBlogForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlogById(id).orElseThrow());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());
        return "blog/form";
    }

    @PostMapping("/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, @RequestParam("categoryId") Long categoryId, @RequestParam("tagIds") Set<Long> tagIds) {
        blog.setId(id);
        blog.setCategory(categoryService.getCategoryById(categoryId).orElseThrow());
        Set<Tags> tags = new HashSet<>();
        tagIds.forEach(tagId -> tags.add(tagService.getTagById(tagId).orElseThrow()));
        blog.setTags(tags);
        blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs";
    }
}
