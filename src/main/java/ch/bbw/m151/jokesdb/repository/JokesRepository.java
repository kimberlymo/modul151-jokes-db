package ch.bbw.m151.jokesdb.repository;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokesRepository extends JpaRepository<Joke, Integer> {
    Optional<Joke> findByJoke(String joke);
}
