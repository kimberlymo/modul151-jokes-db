package ch.bbw.m151.jokesdb.datamodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
@Getter
@Setter
public class JokeRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "joke", nullable = false, updatable = false)
    private Joke jokeId;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @UpdateTimestamp
    private LocalDateTime updatedTimestamp;

    @Size(max = 5, min = 1)
    private double stars;

    private String reasoning;
}
