/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.CommentRequestDto;
import com.masai.modelResponseDto.CommentResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.service.CommentService;

/**
 * @author tejas
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto,
			@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {

		CommentResponseDto createComment = this.commentService.createComment(commentRequestDto, postId, userId);

		return new ResponseEntity<CommentResponseDto>(createComment, HttpStatus.ACCEPTED);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentResponseDto>> findByPost(@PathVariable("postId") Integer postId)
			throws ResourceNotFoundException {

		List<CommentResponseDto> findByPost = this.commentService.findByPost(postId);

		return new ResponseEntity<List<CommentResponseDto>>(findByPost, HttpStatus.OK);

	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable ("commentId") Integer commentId)
			throws ResourceNotFoundException {

		ApiResponse deleteComment = this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(deleteComment, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<CommentResponseDto>> findByUser(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {

		List<CommentResponseDto> findByUser = this.commentService.findByUser(userId);

		return new ResponseEntity<List<CommentResponseDto>>(findByUser, HttpStatus.OK);
	}

}
