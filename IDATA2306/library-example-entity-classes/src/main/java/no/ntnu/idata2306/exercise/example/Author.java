package no.ntnu.idata2306.exercise.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

  public Author() {}

  public Author(int id, String firstName, String lastName, int birthYear) {
    this.id = id;
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

  /**
   * Checks if the object is a valid author.
   *
   * @return True if it is valid, false otherwise
   */
  @JsonIgnore
  public boolean isValid() {
    return this.firstName != null && this.lastName != null && this.birthYear > 0;
  }
}
