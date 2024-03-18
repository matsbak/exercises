package no.ntnu.idata2306.exercise.example;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Represents a resource: an author. We store Author objects in the application state.
 *
 * @param id        Unique ID
 * @param firstName First name
 * @param lastName  Last name
 * @param birthYear The year this person was born
 */
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;
  @ManyToMany
  @JoinTable(
    name = "author_book",
    joinColumns = @JoinColumn(name = "author_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id")
  )
  @JsonIgnore
  private Set<Book> books = new HashSet<>();

  public Author() {}

  public Author(String firstName, String lastName, int birthYear) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  public int getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public int getBirthYear() {
    return this.birthYear;
  }

  public Set<Book> getBooks() {
    return this.books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }

  /**
   * Checks if the object is a valid author.
   *
   * @return True if it is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.firstName != null && !this.firstName.isBlank() && this.lastName != null &&
           !this.lastName.isBlank() && this.birthYear > 0;
  }
}
