package es.marcos.microservices.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.marcos.microservices.dao.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public List<UserEntity> findByAgeLessThan(int age);

	@Query(value = "select * from users where name = ?1 and age >= ?2 and age <= ?3", nativeQuery = true)
	public List<UserEntity> findAllUsersBetweenAgeAndName(String name, int ageBeing, int ageEnd);
}
