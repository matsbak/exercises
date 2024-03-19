package no.ntnu.idata2306.exercise.hellomonolith;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  /**
   * The 'Home' page.
   * 
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/")
  public String getHome() {
    return "index";
  }

  /**
   * The 'Books' page.
   * 
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/books")
  public String getBooks() {
    return "books";
  }

  /**
   * The 'About' page
   * 
   * @return Name of the ThymeLeaf template to render
   */
  @GetMapping("/about")
  public String getAbout() {
    return "about";
  }
}
