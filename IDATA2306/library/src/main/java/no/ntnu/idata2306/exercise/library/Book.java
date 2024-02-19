package no.ntnu.idata2306.exercise.library;

public class Book {
  private static int i = 1;
  private int id;
  private String title;
  private int year;
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
