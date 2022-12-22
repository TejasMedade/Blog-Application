/**
 * 
 */
package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Comment;
import com.masai.model.Post;
import com.masai.model.User;

/**
 * @author tejas
 *
 */
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	List<Comment> findByUser(User user);

	List<Comment> findByPost(Post post);
}
