package ch.bbw.m151.jokesdb.service;

import ch.bbw.m151.jokesdb.datamodel.JokeDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;

import javax.management.BadAttributeValueExpException;

@Service
public class RemoteApiService {

    private WebClient client;
    private void init() {
        client = WebClient.builder()
                .baseUrl("https://v2.jokeapi.dev")
                .build();
    }

    public JokeDto getJokeFilteredByCategory(String category) throws BadAttributeValueExpException {
        init();
        JokeDto joke = client.get().uri("/joke/" + category)
                .retrieve()
                .onStatus(HttpStatus::isError, ClientResponse::createException)
                .bodyToMono(JokeDto.class)
                .block();

        if (joke != null && joke.isError()) {
            throw new BadAttributeValueExpException(joke.getMessage() + ": " + joke.getAdditionalInfo());
        }
        return joke;
    }
}
