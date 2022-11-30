package ch.bbw.m151.jokesdb.repository;

import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRatingRepository extends JpaRepository<JokeRating, Integer> {
}
