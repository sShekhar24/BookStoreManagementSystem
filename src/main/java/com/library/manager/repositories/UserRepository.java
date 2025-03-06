package com.library.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.manager.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	@Query(value="select * from TLM_USER u where lower(u.FIRST_NAME) like :firstName "
			+ "or lower(u.LAST_NAME) like :firstName "
			+ "or lower(u.FIRST_NAME) like :lastName "
			+ "or lower(u.LAST_NAME) like :lastName", nativeQuery = true)
	List<UserEntity> findAllUserByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

	Optional<UserEntity> findUserEntityByUsername(String username);

}
