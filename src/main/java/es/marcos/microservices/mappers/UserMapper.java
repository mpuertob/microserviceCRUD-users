package es.marcos.microservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import es.marcos.microservices.dao.entities.UserEntity;
import es.marcos.microservices.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
 
	public UserEntity getUserEntity(UserDTO userDTO);

	public UserDTO getUserDTO(UserEntity userEntity);

	public List<UserDTO> getUsersDtos(List<UserEntity> usersEntities);

}
