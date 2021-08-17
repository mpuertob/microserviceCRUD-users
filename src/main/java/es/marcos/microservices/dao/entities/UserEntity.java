package es.marcos.microservices.dao.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "users")
public class UserEntity {

//	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NonNull
	private String name;

	@Column(name = "last_Name")
	private String lastName;

	private int age;

	private LocalDate birthday;

	private String email;

	private boolean married;

}
