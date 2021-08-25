package es.marcos.microservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import es.marcos.microservices.model.UserDTO;

public interface UserService {
  public Optional<UserDTO> getUserById(Integer id);

  public List<UserDTO> listAllUsers(Pageable pageable);

  public UserDTO createUser(UserDTO userDTO);

  public UserDTO updateUser(UserDTO userDTO);

  public void deleteUserById(Integer id);

  public List<UserDTO> findAllUsersBetweenAgeAndName(String name, int ageBeing, int ageEnd);

  public List<UserDTO> findByAgeLessThan(int age);

}
