package no.ntnu.idata2306.exercise.service;

import no.ntnu.idata2306.exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic related to authors.
 */
@Service
public class AuthorService {
  @Autowired
  private AuthorRepository authorRepository;

  /**
   * Get the number of authors in the database.
   *
   * @return The total number of authors stored in the database.
   */
  public long getCount() {
    return authorRepository.count();
  }
}
