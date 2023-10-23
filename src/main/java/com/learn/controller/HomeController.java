package com.learn.controller;

import com.learn.model.EmployeeDto;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home-service/v1")
public class HomeController {

  @GetMapping("employee")
  @RateLimiter(name = "employeeDetails")
  public ResponseEntity<EmployeeDto> employeeDetails() {

    return ResponseEntity.ok(
        EmployeeDto.builder().id("100ER20").name("Rajesh").salary(4000.0f).build());
  }
}
