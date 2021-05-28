package com.example.graphqlsample.demo.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {
    @GetMapping
    fun health(): HttpEntity<HttpStatus> {
        return HttpEntity(HttpStatus.OK)
    }
}