package es.marcos.microservices.controller;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.marcos.microservices.dao.entities.UserEntity;
import es.marcos.microservices.model.UserDTO;
import es.marcos.microservices.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
@Api(tags = "User API Rest")
public class UsersControllerRest {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	@ApiOperation(notes = "Retrieve a user by id", value = "Get user by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Response ok if the operation was successful"),
			@ApiResponse(code = 404, message = "Response not found if the resource could not be found") })
	public ResponseEntity<UserDTO> getUserById(
			@ApiParam(example = "1", value = "Identifier for User", allowableValues = "1,2,3,4", required = true) @PathVariable Integer id) {
		System.out.println("Recovery user by id");
		Optional<UserDTO> optionalUserDTO = this.userService.getUserById(id);

		try {
			UserDTO userDTO = optionalUserDTO.orElseThrow(NoSuchElementException::new);
			optionalUserDTO.ifPresent(user -> mostrarUsuarioPorConsola(user));
			return ResponseEntity.ok(userDTO);
		} catch (Exception e) {
			System.out.println("Usuario no encontrado");
			return ResponseEntity.notFound().build();
		}

	}

	private void mostrarUsuarioPorConsola(UserDTO user) {
		System.out.println("Usuario encontrado: " + user);
	}

	@GetMapping()
	@ApiOperation(notes = "Retrieve a list of users, you can apply a filter by name, lastName or age", value = "List All Users")
	public ResponseEntity<List<UserDTO>> listAllUsers(@RequestParam(required = false) String name,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) Integer age,
			@PageableDefault(size = 10, sort = { "name", "age" }, direction = Direction.ASC) Pageable pageable) {

		List<UserDTO> lista = this.userService.listAllUsers(pageable);

		if (name != null) {
			lista = lista.stream().filter(user -> user.getName().contains(name)).collect(Collectors.toList());
		}
		if (lastName != null) {
			lista = lista.stream().filter(user -> user.getLastName().contains(lastName)).collect(Collectors.toList());
		}
		if (age != null) {
			lista = lista.stream().filter(user -> user.getAge() == age).collect(Collectors.toList());
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/filter/betweenAgeAndName/{name}/{ageBeing}/{ageEnd}")
	@ApiOperation(notes = "Retrieve a list of users filtered by name and age range", value = "Find All Users Between Age And Name")
	public ResponseEntity<List<UserDTO>> findAllUsersBetweenAgeAndName(@PathVariable String name,
			@PathVariable int ageBeing, @PathVariable int ageEnd) {
		List<UserDTO> lista = this.userService.findAllUsersBetweenAgeAndName(name, ageBeing, ageEnd);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/filter/age/{age}")
	@ApiOperation(notes = "Retrieve a list of users under a specific age", value = "Find By Age Less Than")
	public ResponseEntity<List<UserDTO>> findByAgeLessThan(@PathVariable int age) {
		List<UserDTO> lista = this.userService.findByAgeLessThan(age);
		return ResponseEntity.ok(lista);
	}

	@PostMapping()
	@ApiOperation(notes = "Create a user", value = "Create user")
	@ApiResponses(@ApiResponse(code = 201, message = "Response created if the operation was successful"))
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		System.out.println("Creating user " + userDTO);
		userDTO = userService.createUser(userDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping()
	@ApiOperation(notes = "Retrieve the modified user", value = "Update User")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTOModify) {
		System.out.println("Updating user");
		userDTOModify = this.userService.updateUser(userDTOModify);
		return ResponseEntity.ok(userDTOModify);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(notes = "Remove user by ID", value = "Delete User")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		this.userService.deleteUserById(id);
		System.out.println("Delete User");
		return ResponseEntity.ok(null);
	}

}
