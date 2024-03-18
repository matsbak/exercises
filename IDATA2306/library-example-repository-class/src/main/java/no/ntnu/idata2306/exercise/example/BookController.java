package no.ntnu.idata2306.exercise.example;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API controller for book table.
 */
@RestController
@RequestMapping("/books")
public class BookController {
  private static final Logger logger = LoggerFactory.getLogger(BookController.class);

  @Autowired
  private BookRepository bookRepository;

  /**
   * Get All books.
   * HTTP GET to /books
   *
   * @return List of all books currently stored in the table
   */
  @GetMapping
  public Iterable<Book> getAll() {
    logger.info("Getting all books");
    return this.bookRepository.findAll();
  }

  /**
   * Get a specific book.
   *
   * @param id ID of the book to be returned
   * @return Book with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getOne(@PathVariable Integer id) {
    ResponseEntity<Book> response;
    Optional<Book> book = this.bookRepository.findById(id);
    if (book.isPresent()) {
      response = new ResponseEntity<>(book.get(), HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * HTTP POST endpoint for adding a new book.
   *
   * @param book Data of the book to add
   * @return 201 Created on success and the new ID in the response body,
   *         400 Bad request if some data is missing or incorrect
   */
  @PostMapping()
  ResponseEntity<String> add(@RequestBody Book book) {
    ResponseEntity<String> response;
    try {
      int id = this.addBookToTable(book);
      response = new ResponseEntity<>("" + id, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Add a new book to the table.
   *
   * @param book The book to add
   * @return The ID of the added book
   * @throws IllegalArgumentException When the provided book is not valid
   */
  private int addBookToTable(Book book) throws IllegalArgumentException {
    if (!book.isValid()) {
      throw new IllegalArgumentException("Book is invalid");
    }
    this.bookRepository.save(book);
    return book.getId();
  }

  /**
   * Delete a book from the table.
   *
   * @param id ID of the book to delete
   * @return 200 OK on success,
   *         404 Not found on error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    if (this.removeBookFromTable(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Remove a book from the table.
   *
   * @param id ID of the book to remove
   * @return True when book with that ID existed and was removed, false otherwise
   */
  private boolean removeBookFromTable(int id) {
    Optional<Book> book = this.bookRepository.findById(id);
    if (book.isPresent()) {
      this.bookRepository.delete(book.get());
    }
    return book.isPresent();
  }

  /**
   * Update a book in the table.
   *
   * @param id ID of the book to update, from the URL
   * @param book New book data to store, from request body
   * @return 200 OK on success,
   *         400 Bad request on error with error message in the response body
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
    ResponseEntity<String> response;
    try {
      this.updateBook(id, book);
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Try to update a book with given ID. The ID of the book must match the given ID.
   *
   * @param id The given ID
   * @param book The updated book data
   * @throws IllegalArgumentException If something goes wrong
   */
  private void updateBook(int id, Book book) throws IllegalArgumentException {
    Optional<Book> existingBook = this.bookRepository.findById(id);
    if (!existingBook.isPresent()) {
      throw new IllegalArgumentException("No book with id " + id + " found");
    }
    if (book == null || !book.isValid()) {
      throw new IllegalArgumentException("Wrong data in request body");
    }
    if (book.getId() != id) {
      throw new IllegalArgumentException(
          "Book ID in the URL does not match the ID in JSON data (response body)");
    }
    this.bookRepository.save(book);
  }
}
