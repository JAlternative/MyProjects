package main.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "animals")
@Entity
@Data
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(nullable = false)
	private Integer id;

	@Column(nullable = false)
	private String kind;

	@Column(name = "reg_time", nullable = false) //TODO с именами колонок тоже подумать
	private LocalDateTime dateOfBirth;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
