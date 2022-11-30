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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.management.BadAttributeValueExpException;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
public class JokesServiceTest extends TestHelper {
    
    @InjectMocks
    private JokeService service;

    @Mock
    private JokesRepository repository;

    @Mock
    private RemoteApiService remoteApiService;

    @Test
    public void createJokeWithAPISuccessful() throws BadAttributeValueExpException {
        Joke expected = loadJokeForTests();
        Mockito.when(remoteApiService.getJokeFilteredByCategory("programming")).thenReturn(loadJokeDtoForTests());
        Mockito.when(repository.save(expected)).thenReturn(expected);

        Joke result = service.getJokeFilteredByCategory("programming");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getJoke(), expected.getJoke());
    }

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

    @Test
    public void getJokeFilteredByCategory() throws BadAttributeValueExpException {
        Joke expected = loadJokeForTests();
        System.out.println(expected);

        Mockito.when(repository.save(expected)).thenReturn(expected);
        Mockito.when(remoteApiService.getJokeFilteredByCategory("programming")).thenAnswer(invocation -> {
            if ("".equals(invocation.getArgument(0))) throw new NoSuchElementException();
            return loadJokeDtoForTests();
        });

        Joke result = service.getJokeFilteredByCategory("programming");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getJoke(), result.getJoke());
    }
}