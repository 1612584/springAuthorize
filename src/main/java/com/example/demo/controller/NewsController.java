package com.example.demo.controller;

import com.example.demo.Utils.FakeUtils;
import com.example.demo.model.AccountCredentials;
import com.example.demo.model.News;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {
    @GetMapping
    public List<News> news() {
        // Tham khảo FakeUtils mình có viết trong package Utils để fake dữ liệu nhé
        return FakeUtils.getAllNews();
    }
    @PostMapping
    public String postNew(@RequestBody AccountCredentials accountCredentials) {
        // Tham khảo FakeUtils mình có viết trong package Utils để fake dữ liệu nhé
        return accountCredentials.getPassword();
    }
}