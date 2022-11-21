package ch.bbw.m151.jokesdb;

import ch.bbw.m151.jokesdb.controller.JokesController;
import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import ch.bbw.m151.jokesdb.service.JokesService;
import ch.bbw.m151.jokesdb.service.RemoteApiService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Data
@SpringBootTest
@ActiveProfiles("test")
public class TestHelper {

    @Autowired
    private JokesService jokesService;
    @Autowired
    private JokesController jokesController;
    @Autowired
    private RemoteApiService remoteApiService;
    @Autowired
    private JokesRepository jokesRepository;

    public Joke loadJokeForTests() {
        Joke jokesEntity = new Joke();
        jokesEntity.setJoke("your mam");
        return jokesEntity;
    }
}
