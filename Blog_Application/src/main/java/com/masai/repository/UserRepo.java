/**
 * 
 */
package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.User;

/**
 * @author tejas
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	List<User> findByNameContaining(String keyword);

}
