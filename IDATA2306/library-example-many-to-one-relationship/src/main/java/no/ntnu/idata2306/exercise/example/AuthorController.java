package no.ntnu.idata2306.exercise.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
 * REST API controller for author collection.
 */
@RestController
@RequestMapping("authors")
public class AuthorController {
  private Map<Integer, Author> authors;

  public AuthorController() {
    this.initializeData();
  }

  /**
   * Initialize dummy author data for the collection.
   */
  private void initializeData() {
    this.authors = new HashMap<>();
    this.addAuthorToCollection(new Author("James", "Kurose", 1960));
    this.addAuthorToCollection(new Author("Keith", "Ross", 1965));
    this.addAuthorToCollection(new Author("Jordan", "Peterson", 1960));
  }

  /**
   * Get All authors.
   * HTTP GET to /authors
   *
   * @return List of all authors currently stored in the collection
   */
  @GetMapping
  public Collection<Author> getAll() {
    return this.authors.values();
  }

  /**
   * Get a specific author.
   *
   * @param id ID of the author to be returned
   * @return Author with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<Author> getOne(@PathVariable Integer id) {
    ResponseEntity<Author> response;
    Author author = this.findAuthorById(id);
    if (author != null) {
      response = new ResponseEntity<>(author, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Add an author to the collection.
   *
   * @param author Author to be added, from HTTP response body
   * @return 201 CREATED status on success,
   *         400 Bad request on error
   */
  @PostMapping
  public ResponseEntity<String> add(@RequestBody Author author) {
    ResponseEntity<String> response;
    try {
      this.addAuthorToCollection(author);
      response = new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  private void addAuthorToCollection(Author author) throws IllegalArgumentException {
    if (!author.isValid()) {
      throw new IllegalArgumentException("Invalid author");
    }
    this.authors.put(author.getId(), new Author(author.getFirstName(), author.getLastName(),
                                                author.getBirthYear()));
  }

  /**
   * Delete an author from the collection.
   *
   * @param id ID of the author to delete
   * @return 200 OK on success,
   *         404 Not found on error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    if (this.removeAuthorFromCollection(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Try to remove an author from the collection.
   *
   * @param id ID of the author to remove
   * @return True when author with given ID was found and removed, false otherwise
   */
  private boolean removeAuthorFromCollection(int id) {
    Author removedAuthor = this.authors.remove(id);
    return removedAuthor != null;
  }

  /**
   * Update an author in the repository.
   *
   * @param id ID of the author to update, from the URL
   * @param author New author data to store, from request body
   * @return 200 OK on success,
   *         400 Bad request on error
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody Author author) {
    ResponseEntity<String> response;
    try {
      this.updateAuthor(id, author);
      response = new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  private void updateAuthor(int id, Author author) throws IllegalArgumentException {
    Author existingAuthor = this.findAuthorById(id);
    if (existingAuthor == null) {
      throw new IllegalArgumentException("No author with id " + id + " found");
    }
    if (author == null || !author.isValid()) {
      throw new IllegalArgumentException("Wrong data in request body");
    }
    if (author.getId() != id) {
      throw new IllegalArgumentException("Author ID in the URL does not match the ID in the " +
                                         "response body");
    }
    this.authors.put(id, author);
  }

  /**
   * Search through the author collection, find the author by given ID.
   *
   * @param id Author ID
   * @return Author or null if not found
   */
  private Author findAuthorById(Integer id) {
    return this.authors.get(id);
  }
}
