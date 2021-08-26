package es.marcos.microservices.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "System user")
public class UserDTO {

	@ApiModelProperty(notes = "Unique identifier of the User.", example = "0")
	private Integer id;

	@NonNull
	@NotBlank()
	@ApiModelProperty(notes = "Name of the User.", example = "Marcos")
	private String name;

	@Size(min = 1, max = 999)
	@ApiModelProperty(notes = "LastName of the User.", example = "Puerto")
	private String lastName;

	@Positive
	@Min(1)
	@ToString.Exclude
	@ApiModelProperty(notes = "Age of the User.", example = "18")
	private int age;

	@Past(message = "{spring.app.field.birth_day.error}")
	@ApiModelProperty(notes = "Birthday of the User.", example = "1998-10-17")
	private LocalDate birthday;

	@Email
	@ApiModelProperty(notes = "Mail of the User.", example = "carlos@example.com")
	private String email;

	@ApiModelProperty(notes = "If the user is married.", example = "true")
	private boolean married;
}
