package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.TestHelper;
import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class JokesServiceTest extends TestHelper {
    
    @InjectMocks
    private JokeService service;

    @Mock
    private JokesRepository repository;

    @Test
    public void createJokeIsEmptyOrNull() {
        Joke emptyJoke = new Joke();
        emptyJoke.setJoke("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createJoke(emptyJoke));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createJoke(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.createJoke(new Joke()));
    }

    @Test
    public void createJokeSuccessful() {
        Joke expected = loadJokeForTests();
        Mockito.when(repository.save(expected)).thenReturn(expected);
        Joke result = service.createJoke(expected);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getJoke(), result.getJoke());
    }
}