/**
 * 
 */
package com.masai.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.masai.exceptions.FileTypeNotValidException;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.PostRequestDto;
import com.masai.modelResponseDto.PostResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.PageResponse;

/**
 * @author tejas
 *
 */
public interface PostService {

	PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId, MultipartFile image)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	PostResponseDto updatePostContent(PostRequestDto postRequestDto, Integer postId) throws ResourceNotFoundException;

	ApiResponse deletePost(Integer postId);

	PostResponseDto getPostsById(Integer postId) throws ResourceNotFoundException;

	List<PostResponseDto> getAllPosts();

	List<PostResponseDto> getPostsByUser(Integer UserId) throws ResourceNotFoundException;

	List<PostResponseDto> getPostsByCategory(Integer CategoryId) throws ResourceNotFoundException;

	List<PostResponseDto> getPostBySearch(String keyword);

	PageResponse getAllPostsPagination(Integer page, Integer size);

	PageResponse getAllPostsByPageSortBy(Integer page, Integer size, String sortBy, String sortDirection);

	void serveImage(String imageName, HttpServletResponse response) throws IOException;

	PostResponseDto updatePostImage(Integer postId, MultipartFile image)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	void serveImageByPostId(Integer postId, HttpServletResponse response) throws IOException, ResourceNotFoundException;
}
