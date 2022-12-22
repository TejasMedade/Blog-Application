/**
 * 
 */
package com.masai.service;

import java.util.List;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.CommentRequestDto;
import com.masai.modelResponseDto.CommentResponseDto;
import com.masai.payloads.ApiResponse;

/**
 * @author tejas
 *
 */
public interface CommentService {

	CommentResponseDto createComment(CommentRequestDto commentRequestDto, Integer postId, Integer userId)
			throws ResourceNotFoundException;

	List<CommentResponseDto> findByPost(Integer postId) throws ResourceNotFoundException;

	ApiResponse deleteComment(Integer commentId) throws ResourceNotFoundException;

	List<CommentResponseDto> findByUser(Integer userId) throws ResourceNotFoundException;

}
