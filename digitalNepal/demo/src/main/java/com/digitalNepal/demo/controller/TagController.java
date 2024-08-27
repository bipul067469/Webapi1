package com.digitalNepal.demo.controller;

import com.digitalNepal.demo.model.Tags;
import com.digitalNepal.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public String listTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tag/list";
    }

    @GetMapping("/create")
    public String createTagForm(Model model) {
        model.addAttribute("tag", new Tags());
        return "tag/form";
    }

    @PostMapping
    public String saveTag(@ModelAttribute Tags tag) {
        tagService.saveTag(tag);
        return "redirect:/tags";
    }

    @GetMapping("/{id}/edit")
    public String editTagForm(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id).orElseThrow());
        return "tag/form";
    }

    @PostMapping("/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute Tags tag) {
        tag.setId(id);
        tagService.saveTag(tag);
        return "redirect:/tags";
    }

    @PostMapping("/{id}/delete")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/tags";
    }
}
