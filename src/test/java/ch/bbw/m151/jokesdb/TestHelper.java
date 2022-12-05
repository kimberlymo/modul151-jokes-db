package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import ch.bbw.m151.jokesdb.service.RemoteApiService;
import lombok.Data;
import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Data
@SpringBootTest
@ActiveProfiles("test")
public class TestHelper implements WithAssertions {

    @Autowired
    private RemoteApiService remoteApiService;

    public static Joke loadJokeForTests() {
        Joke jokesEntity = new Joke();
        jokesEntity.setJoke("your mam");
        return jokesEntity;
    }

    public static JokeRating loadRatingForTests() {
        JokeRating rating = new JokeRating();
        rating.setStars(4.4);
        return rating;
    }
}
