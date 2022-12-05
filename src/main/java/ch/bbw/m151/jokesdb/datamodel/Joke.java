package ch.bbw.m151.jokesdb.datamodel;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "joke")
@Accessors(chain = true)
@Getter
@Setter
public class Joke {

	@Version
	private Long version = 1L;

	@Id
	@GeneratedValue
	int id;

	@Column(nullable = false)
	private String joke;

	@CreationTimestamp
	private LocalDateTime creationDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@ManyToOne
	@JoinColumn(name = "rating")
	private JokeRating rating;
}
