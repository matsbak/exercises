package no.ntnu.idata2306.exercise.library;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a book in the library")
public class Book {
  private static int i = 1;
  @Schema(description = "Unique auto-generated ID")
  private int id;
  @Schema(description = "Title of the book")
  private String title;
  @Schema(description = "Year the book was published")
  private int year;
  @Schema(description = "Number of pages in the book")
  private int numberOfPages;

  public Book(String title, int year, int numberOfPages) {
    this.id = i++;
    this.title = title;
    this.year = year;
    this.numberOfPages = numberOfPages;
  }

  public static void decrement() {
    i--;
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

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }
}
