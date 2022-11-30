package ch.bbw.m151.jokesdb.controller;

import ch.bbw.m151.jokesdb.datamodel.JokeRating;
import ch.bbw.m151.jokesdb.service.JokeRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JokeRatingController {

    private final JokeRatingService service;

    @PostMapping("/rating")
    public JokeRating createJokeRating(@RequestBody JokeRating rating) {
        return service.createRating(rating);
    }

}
