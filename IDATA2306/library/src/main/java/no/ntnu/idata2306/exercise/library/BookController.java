package no.ntnu.idata2306.exercise.library;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BookController {
  ArrayList<Book> books;

  public BookController() {
    this.books = new ArrayList<>();
    this.initializeData();
  }

  private void initializeData() {
    Book book1 = new Book("Wolf Hall", 2009, 653);
    Book book2 = new Book("Gilead", 2004, 247);
    Book book3 = new Book("Secondhand Time", 2013, 496);
    this.books.add(book1);
    this.books.add(book2);
    this.books.add(book3);
  }

  @GetMapping("/books")
  public ArrayList<Book> restGetBooks() {
    return this.books;
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<Book> restGetBook(@PathVariable int id) {
    ResponseEntity<Book> responseEntity;
    Book book = null;
    try {
      book = this.books.get(id - 1);
      responseEntity = ResponseEntity.status(HttpStatus.OK).body(book);
    } catch (IndexOutOfBoundsException e) {
      responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
    }
    return responseEntity;
  }

  @PostMapping("/books")
  @Operation(
    summary = "Create new book",
    description = "Create a new book that is added to the collection of books if it is created " +
                  "with valid parameters"
  )
  @ApiResponses(value =  {
    @ApiResponse(
      responseCode = "201",
      description = "A confirmation message is returned in the response body"
    ),
    @ApiResponse(
      responseCode = "400",
      description = "An error message explaining why the request was bad is returned in the " +
                    "response body"
    )
  })
  public ResponseEntity<Integer> restAddBook(
    @Parameter(description = "The book to create")
    @RequestBody
    Book book
  ) {
    ResponseEntity<Integer> responseEntity = null;
    // if (book.getTitle().isBlank()) {
    //   responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be blank");
    //   Book.decrement();
    // }
    // if (book.getYear() < 0) {
    //   responseEntity = ResponseEntity
    //   .status(HttpStatus.BAD_REQUEST).body("Year cannot be negative");
    //   Book.decrement();
    // }
    // if (book.getNumberOfPages() < 1) {
    //   responseEntity = ResponseEntity
    //   .status(HttpStatus.BAD_REQUEST).body("Number of pages cannot be negative or 0");
    //   Book.decrement();
    // }
    if (responseEntity == null) {
      this.books.add(book);
      responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(book.getId());
    }
    return responseEntity;
  }

  @DeleteMapping("/books/{id}")
  public ResponseEntity<String> restDeleteBook(@PathVariable int id) {
    ResponseEntity<String> response;
    try {
      this.books.remove(id - 1);
      response = ResponseEntity.status(HttpStatus.OK).body("");
      Book.decrement();
    } catch (IndexOutOfBoundsException e) {
      response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }
    return response;
  }
}
