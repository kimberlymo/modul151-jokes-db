package ch.bbw.m151.jokesdb.datamodel;

import lombok.Data;

@Data
public class JokeDto {
    String joke;
    String setup;
    String delivery;
    boolean error;
    String message;
    String additionalInfo;

    public Joke getJoke() {
        Joke entity = new Joke();
        entity.setJoke((joke == null) ? setup + " " + delivery : joke);
        return entity;
    }
}
