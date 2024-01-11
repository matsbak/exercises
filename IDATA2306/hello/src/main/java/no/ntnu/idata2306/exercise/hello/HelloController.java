package no.ntnu.idata2306.exercise.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("/hello")
  public String greeting() {
    return "Hello, World!";
  }
}
