/**
 * 
 */
package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Category;
import com.masai.model.Post;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	// Searching By Title
	List<Post> findByTitleContaining(String title);

}
