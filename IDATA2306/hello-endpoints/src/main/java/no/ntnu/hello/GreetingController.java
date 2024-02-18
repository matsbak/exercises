package no.ntnu.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST API controller which responds to HTTP requests for /hello.
 */
@RestController
public class GreetingController {

  /**
   * Responds to HTTP GET requests for /hello.
   *
   * @return a greeting message
   */
  @GetMapping("/hello")
  public String greeting() {
    return "Hello, World!";
  }

  @GetMapping("/hei")
  public ResponseEntity<String> invalidGreeting() {
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("Use /hello");
  }

  @DeleteMapping("/hello")
  public ResponseEntity<String> deleteGreeting() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
  }
}
