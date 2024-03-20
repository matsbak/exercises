package no.ntnu.idata2306.exercise.service;

import no.ntnu.idata2306.exercise.model.Book;
import no.ntnu.idata2306.exercise.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Business logic related to books.
 */
@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  /**
   * Get all books currently stored in the application state (database).
   *
   * @return All the books
   */
  public Iterable<Book> getAll() {
    return bookRepository.findAll();
  }

  /**
   * Get a specific book from the database.
   * 
   * @param id The id of the book
   * @return An optional containing the book, empty when no book found
   */
  public Optional<Book> getOne(int id) {
    return bookRepository.findById(id);
  }

  /**
   * Get the first n books from the database.
   *
   * @param n The number of books to select
   * @return An iterable over the books, empty when no books found
   */
  public Iterable<Book> getFirst(int n) {
    return bookRepository.findAll(PageRequest.of(0, n));
  }

  /**
   * Get a selection of books from the database
   * 
   * @param ids The list containing ids of books to select
   * @return An iterable over the books, empty when no books found
   */
  public Iterable<Book> getSelection(List<Integer> ids) {
    List<Book> books = new ArrayList<>();
    for (int id : ids) {
      Optional<Book> book = getOne(id);
      if (book.isPresent()) {
        books.add(book.get());
      }
    }
    return books;
  }

  /**
   * Get the number of books in the database.
   *
   * @return The total number of books stored in the database
   */
  public long getCount() {
    return bookRepository.count();
  }
}
