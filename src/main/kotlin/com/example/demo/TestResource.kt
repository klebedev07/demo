package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestResource {

    @GetMapping("/test")
    fun test(): String {return "TEST!!!"}
}