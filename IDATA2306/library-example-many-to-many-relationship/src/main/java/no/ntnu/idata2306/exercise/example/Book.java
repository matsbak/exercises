package no.ntnu.idata2306.exercise.example;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Represents a resource: a book. We store Book objects in the application state.
 */
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private int year;
  private int numberOfPages;
  @ManyToMany(mappedBy = "books")
  private Set<Author> authors = new HashSet<>();

  public Book() {}

  public Book(String title, int year, int numberOfPages) {
    this.title = title;
    this.year = year;
    this.numberOfPages = numberOfPages;
  }

  public int getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public int getYear() {
    return this.year;
  }

  public int getNumberOfPages() {
    return this.numberOfPages;
  }

  public Set<Author> getAuthors() {
    return this.authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  /**
   * Check if this object is a valid book.
   *
   * @return True if the book is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.title != null && !this.title.isBlank() && this.year > 0 && this.numberOfPages > 0;
  }
}
