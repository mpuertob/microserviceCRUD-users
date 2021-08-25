package es.marcos.microservices.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.marcos.microservices.dao.entities.UserEntity;
import es.marcos.microservices.dao.repositories.UserRepository;
import es.marcos.microservices.mappers.UserMapper;
import es.marcos.microservices.model.UserDTO;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserMapper userMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<UserDTO> getUserById(Integer id) {
		UserEntity userEntity = this.userRepository.getById(id);
		UserDTO userDTO = this.userMapper.getUserDTO(userEntity);
		return Optional.of(userDTO);
	}

	@Override
	public List<UserDTO> listAllUsers(Pageable pageable) {
		Page<UserEntity> page = this.userRepository.findAll(pageable);
		List<UserEntity> listaEntities = page.getContent();
		List<UserDTO> listaDtos = this.userMapper.getUsersDtos(listaEntities);
		return listaDtos;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		UserEntity userEntity = this.userMapper.getUserEntity(userDTO);
		userEntity = this.userRepository.save(userEntity);
		userDTO = userMapper.getUserDTO(userEntity);
		return userDTO;
	}

	@Override
	public void deleteUserById(Integer id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<UserDTO> findAllUsersBetweenAgeAndName(String name, int ageBeing, int ageEnd) {
		List<UserEntity> listaEntities = this.userRepository.findAllUsersBetweenAgeAndName(name, ageBeing, ageEnd);
		List<UserDTO> listaDtos = this.userMapper.getUsersDtos(listaEntities);
		return listaDtos;
	}

	@Override
	public List<UserDTO> findByAgeLessThan(int age) {
		List<UserEntity> listaEntities = this.userRepository.findByAgeLessThan(age);
		List<UserDTO> listaDtos = this.userMapper.getUsersDtos(listaEntities);
		return listaDtos;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTOMod) {
		Optional<UserDTO> optionalDTO = this.getUserById(userDTOMod.getId());
		UserDTO userDTORecuperado = optionalDTO.get();
		boolean isNull = Objects.isNull(userDTORecuperado);
		if (!isNull) {
			userDTOMod.setId(userDTORecuperado.getId());
			this.deleteUserById(userDTOMod.getId());
			userDTOMod = this.createUser(userDTOMod);
		}

		return userDTOMod;
	}

}
