package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import ch.bbw.m151.jokesdb.repository.JokeRatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JokeRatingService {
    private final JokeRatingRepository repository;

    public JokeRating createRating(JokeRating rating) {
        if (rating == null || rating.getStars() <= 0) {
            throw new IllegalArgumentException("The given Argument was NULL and cannot be processed");
        }
        return repository.save(rating);
    }

    public JokeRating getById(Integer id) {
        Optional<JokeRating> rating = repository.findById(id);

        if (rating.isEmpty()) {
            throw new IllegalArgumentException("The entity with the id " + id + " does not exist!");
        }

        return rating.get();
    }
}
