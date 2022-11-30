package ch.bbw.m151.jokesdb.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import ch.bbw.m151.jokesdb.datamodel.Joke;
import ch.bbw.m151.jokesdb.repository.JokesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@Slf4j
@RequiredArgsConstructor
public class JokeService {

	private final JokesRepository jokesRepository;
	private final RemoteApiService remoteApiService;

	@EventListener(ContextRefreshedEvent.class)
	public void preloadDatabase() {
		if (jokesRepository.count() != 0) {
			log.info("database already contains data...");
			return;
		}
		log.info("will load jokes from classpath...");
		try (var lineStream = Files.lines(new ClassPathResource("chucknorris.txt").getFile()
				.toPath(), StandardCharsets.UTF_8)) {
			var jokes = lineStream.filter(x -> !x.isEmpty())
					.map(x -> new Joke().setJoke(x))
					.toList();
			jokesRepository.saveAll(jokes);
		} catch (IOException e) {
			throw new RuntimeException("failed reading jokes from classpath", e);
		}
	}

	public List<Joke> getAllJokes(Pageable pageable) {
		return jokesRepository.findAll(pageable)
				.getContent();
	}

	public Joke getJokeFilteredByCategory(String category) {
		try {
			Joke jokeDto = remoteApiService.getJokeFilteredByCategory(category).getJoke();
			return createJoke(jokeDto);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public Joke createJoke(Joke joke) throws IllegalArgumentException {
		if (joke == null || joke.getJoke() == null || joke.getJoke().isEmpty()) {
			throw new IllegalArgumentException("Joke is empty and cannot be processed");
		}

		Optional<Joke> isJokePresent = jokesRepository.findByJoke(joke.getJoke());

		if (isJokePresent.isPresent()) {
			throw new EntityExistsException(String.format("The joke %s already exists", joke.getJoke()));
		}

		return jokesRepository.save(joke);
	}
}
