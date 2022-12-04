package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import ch.bbw.m151.jokesdb.service.RemoteApiService;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JokesDbApplicationTest extends TestHelper {

    @Autowired
    JokesRepository jokesRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void jokesAreLoadedAtStartup() {
        var jokes = jokesRepository.findAll();
        assertThat(jokes).hasSizeGreaterThan(100)
                .allSatisfy(x -> assertThat(x.getJoke()).isNotEmpty());
    }

    @Test
    void jokesCanBeRetrievedViaHttpGet() {
        var pageSize = 5;
        webTestClient.get()
                .uri("/jokes?page={page}&size={size}", 1, pageSize)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Joke.class)
                .hasSize(pageSize);
    }

    @Test
    void getJokeFilteredByCategory() {
        webTestClient.get().uri("/jokes/programming")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void createJoke() {
        webTestClient.post().uri("/jokes")
                .body(Mono.just(loadJokeForTests()), Joke.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Joke.class);
    }

    @Test
    void createRating() {
        webTestClient.post().uri("/rating")
                .body(Mono.just(loadRatingForTests()), JokeRating.class)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(JokeRating.class);
    }

}
