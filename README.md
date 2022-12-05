# JokesDB

A minimal application to play with JPA and spring data topics.

## 🐳 Postgres with Docker

A simple solution expects a https://www.baeldung.com/linux/docker-run-without-sudo[running docker without sudo].
To get a Database connection (and associated JPA-autocomplete), run `./gradlew bootRun` (it will hang).

Alternatively launch a postgres docker container similar to the `dockerPostgres`-Task in `build.gradle` by hand.

## 🪣 IntelliJ Database View

View | Tool Windows | Database | + | Data Source from URL
```
jdbc:postgresql://localhost:5432/localdb
User: localuser, Password: localpass
```

## Focus
The application focuses on backend features as well as Unit-Tests. There is a github actions workflow that runs all Unit-Tests after a push on a branch. This action shall help with testing and seeing if something broke during the change.

Jokes can be created, deleted, and you can get a Joke. With the link `localhost:8080/jokes/${category}` you will get a Joke from an API. The category will define what kind of joke it will be for example programming or dark.
Ratings can be created as well as viewed.

## Anforderungen
WebFlux-Client-Anbindung an https://jokeapi.dev (read-only, d.h. ohne `/submit`)
- **min**: hardcoded client für ein paar Usecases
- **think-tank**: generischer Jokes-Client, mehrere Parameter (zB `lang` werden supportet), Rate-Limit-Awareness
. Jokes werden local cached.
- **min**: Remote Jokes werden in der lokalen DB gespeichert und Duplikate werden verhindert.
- **think-tank**: webclient-connection-pooling, explizites `@Transactional`-Handling
  . Die Datenbank wird durch sinnvolle Felder erweitert und Jokes können mit einem Sterne-Rating pro User versehen werden.
- **min**: Technische Datenbankfelder creation-timestamp, modified-timestamp (and friends) per Tabelle, joke-ratings in einer zweiten Tabelle
- **think-tank**: Techfelder in einer Basis-Klasse, weitere Columns wie "Category" entsprechend jokeapi.dev, Ratings per User und User login via BasicAuth
  . Verwendet sinnvolle https://projectlombok.org/[Lombok] Features
- **min**: keine Getter/Setters in Code
- besser: Loggers, ToString,...
  . JUnit Testing mit `@SpringBootTest` und https://assertj.github.io/doc/[AssertJ]
- **min**: `WebTestClient` Tests der eigenen Endpunkte
- **think-tank**: automatisierte CI-Tests bei jedem Commit und sinnvolle Coverage
  . Dokumentation
- **min**: README mit einer Selbsteinschätzung, Diskussion der verwendeten Features und wo der Fokus gesetzt wurde.

## Selbsteinschätzung
Meiner Meinung nach ist der Code gut geschrieben und alle mindestens requirements wurden implementiert.
Nebenbei konnte ich eine CI Pipeline erstellen sowie mehrere Tests. Aus diesen Features erwarte ich ca. eine 5.5, da der Code
verständlich ist sowie auch die mindestens requirements übertroffen geworden sind.
