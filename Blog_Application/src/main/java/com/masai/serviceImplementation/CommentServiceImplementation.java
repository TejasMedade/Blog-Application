/**
 * 
 */
package com.masai.serviceImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.Comment;
import com.masai.model.Post;
import com.masai.model.User;
import com.masai.modelRequestDto.CommentRequestDto;
import com.masai.modelResponseDto.CommentResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.repository.CommentRepo;
import com.masai.repository.PostRepo;
import com.masai.repository.UserRepo;
import com.masai.service.CommentService;

/**
 * @author tejas
 *
 */

@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Integer postId, Integer userId)
			throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		Comment comment = this.toComment(commentRequestDto);
		comment.setPost(post);
		comment.setUser(user);
		comment.setCommentByUserId(user.getUserId());
		comment.setCommentByUserName(user.getName());
		comment.setDate(LocalDate.now());

		Comment savedComment = commentRepo.save(comment);

		return this.toCommentResponseDto(savedComment);
	}

	@Override
	public ApiResponse deleteComment(Integer commentId) throws ResourceNotFoundException {

		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

		commentRepo.delete(comment);

		return new ApiResponse(LocalDateTime.now(), "Comment Deleted Successfully !", true);
	}

	@Override
	public List<CommentResponseDto> findByUser(Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		List<Comment> findByUser = commentRepo.findByUser(user);

		List<CommentResponseDto> collect = findByUser.stream().map(this::toCommentResponseDto)
				.collect(Collectors.toList());

		return collect;
	}

	@Override
	public List<CommentResponseDto> findByPost(Integer postId) throws ResourceNotFoundException {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		List<Comment> findByPost = commentRepo.findByPost(post);

		List<CommentResponseDto> collect = findByPost.stream().map(this::toCommentResponseDto)
				.collect(Collectors.toList());

		return collect;
	}

	CommentResponseDto toCommentResponseDto(Comment comment) {

		return this.modelMapper.map(comment, CommentResponseDto.class);
	}

	Comment toComment(CommentRequestDto commentRequestDto) {

		return this.modelMapper.map(commentRequestDto, Comment.class);

	}

}
