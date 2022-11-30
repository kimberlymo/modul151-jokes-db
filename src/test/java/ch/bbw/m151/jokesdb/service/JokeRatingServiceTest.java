package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.TestHelper;
import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import ch.bbw.m151.jokesdb.repository.JokeRatingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class JokeRatingServiceTest extends TestHelper {
    @InjectMocks
    private JokeRatingService service;
    @Mock
    private JokeRatingRepository repository;

    @Test
    public void createRatingSuccessful() {
        JokeRating expected = loadRatingForTests();
        Mockito.when(repository.save(expected)).thenReturn(expected);

        JokeRating result = service.createRating(expected);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getStars(), result.getStars());
    }

    @Test
    public void createRatingEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createRating(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createRating(new JokeRating()));
    }
}