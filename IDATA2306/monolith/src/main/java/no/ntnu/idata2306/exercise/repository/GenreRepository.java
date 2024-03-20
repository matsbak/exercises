package no.ntnu.idata2306.exercise.repository;

import no.ntnu.idata2306.exercise.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Genre data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
