package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.TestHelper;
import ch.bbw.m151.jokesdb.datamodel.Joke;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.management.BadAttributeValueExpException;

import static org.junit.jupiter.api.Assertions.*;

public class JokesServiceTest extends TestHelper {

    @Test
    public void createJokeSuccessful() throws BadAttributeValueExpException {
        Mockito.when(getRemoteApiService().getJokeFilteredByCategory("")).then(r -> getData());

        Joke result = getJokesService().getJokeFilteredByCategory("programming");
        assertNotNull(result);
        assertNotNull(result.getJoke());
    }

    @Test
    public void createJokeIsEmptyOrNull() {
        Joke emptyJoke = new Joke();
        emptyJoke.setJoke("");

        assertThrows(IllegalArgumentException.class, () -> getJokesService().createJoke(null));
        assertThrows(IllegalArgumentException.class, () -> getJokesService().createJoke(new Joke()));
        assertThrows(IllegalArgumentException.class, () -> getJokesService().createJoke(emptyJoke));
    }

    private Joke getData() {
        Joke createJoke = new Joke();
        createJoke.setJoke("this is a test");

        return createJoke;
    }
}