package ch.bbw.m151.jokesdb.controller;

import java.util.List;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JokesController {

    private final JokeService jokesService;

    /**
     * @param pageable to be called with params `?page=3&size=5`
     * @return hilarious content
     */
    @GetMapping("/jokes")
    public List<Joke> getJokes(Pageable pageable) {
        return jokesService.getAllJokes(pageable);
    }

    @GetMapping("/jokes/{category}")
    public Joke getJokeFilteredByCategory(@PathVariable String category) {
        return jokesService.getJokeFilteredByCategory(category);
    }

    @PostMapping("/jokes")
    public Joke createJoke(@RequestBody Joke data) {
        return jokesService.createJoke(data);
    }
}
