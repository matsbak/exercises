package no.ntnu.idata2306.exercise.library;

public class Author {
  private static int i = 1;
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;

  public Author(String firstName, String lastName, int birthYear) {
    this.id = i++;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  public static void decrement() {
    i--;
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

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }
}
