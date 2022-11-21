package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.TestHelper;
import ch.bbw.m151.jokesdb.datamodel.JokeDto;
import org.junit.jupiter.api.Test;

import javax.management.BadAttributeValueExpException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Here are the tests for the usage of the client.
 */
public class RemoteApiServiceTest extends TestHelper {

    private final static String VALID_CATEGORY = "programming";
    private final static String INVALID_CATEGORY = "testAJoke";

    @Test
    public void getJokeByCategorySuccessful() throws Exception {
        JokeDto result = getRemoteApiService().getJokeFilteredByCategory(VALID_CATEGORY);

        assertNotNull(result);
        assertFalse(result.isError());
        assertTrue(result.getJoke() != null || (result.getSetup() != null && result.getDelivery() != null));
    }

    @Test
    public void categoryDoesNotExist() {
        assertThrows(BadAttributeValueExpException.class,
                () -> getRemoteApiService().getJokeFilteredByCategory(INVALID_CATEGORY));
    }
}