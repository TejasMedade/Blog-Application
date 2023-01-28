/**
 * 
 */
package com.masai.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masai.exceptions.FileTypeNotValidException;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.PostRequestDto;
import com.masai.modelResponseDto.PostResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.AppConstants;
import com.masai.payloads.PageResponse;
import com.masai.service.PostService;

/**
 * @author tejas
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostResponseDto> createPostHandler(@RequestParam String postRequestDto,
			@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId,
			@RequestParam MultipartFile image)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException {

		// converting String into JSON
		PostRequestDto post = objectMapper.readValue(postRequestDto, PostRequestDto.class);

		PostResponseDto createPost = postService.createPost(post, userId, categoryId, image);

		return new ResponseEntity<PostResponseDto>(createPost, HttpStatus.CREATED);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostResponseDto> updatePostContentHandler(@Valid @RequestBody PostRequestDto postRequestDto,
			@PathVariable("postId") Integer postId) throws ResourceNotFoundException {

		PostResponseDto updatePost = postService.updatePostContent(postRequestDto, postId);

		return new ResponseEntity<PostResponseDto>(updatePost, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePostHandler(@PathVariable("postId") Integer postId) {

		ApiResponse deletePost = postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(deletePost, HttpStatus.OK);

	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostResponseDto> getPostsByIdHandler(@PathVariable("postId") Integer postId)
			throws ResourceNotFoundException {

		PostResponseDto postsById = postService.getPostsById(postId);

		return new ResponseEntity<PostResponseDto>(postsById, HttpStatus.ACCEPTED);
	}

	@GetMapping("/")
	public ResponseEntity<List<PostResponseDto>> getAllPostsHandler() {

		List<PostResponseDto> allPosts = postService.getAllPosts();

		return new ResponseEntity<List<PostResponseDto>>(allPosts, HttpStatus.OK);
	}

	@GetMapping("/allposts")
	public ResponseEntity<PageResponse> getAllPostsByPageHandler(
			@RequestParam(defaultValue = AppConstants.PAGENUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGESIZE, required = false) Integer pageSize) {

		PageResponse allPosts = postService.getAllPostsPagination(pageNumber, pageSize);

		return new ResponseEntity<PageResponse>(allPosts, HttpStatus.OK);

	}

	@GetMapping("/allposts/sort")
	public ResponseEntity<PageResponse> getAllPostsByPageSortByHandler(
			@RequestParam(defaultValue = AppConstants.PAGENUMBER, required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGESIZE, required = false) Integer pageSize,
			@RequestParam(defaultValue = AppConstants.SORTBY, required = false) String sortBy,
			@RequestParam(defaultValue = AppConstants.SORTDIRECTION, required = false) String sortDirection) {

		PageResponse allPosts = postService.getAllPostsByPageSortBy(pageNumber, pageSize, sortBy, sortDirection);

		return new ResponseEntity<PageResponse>(allPosts, HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostResponseDto>> getPostsByUserHandler(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {

		List<PostResponseDto> postsByUser = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostResponseDto>>(postsByUser, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostResponseDto>> getPostsByCategoryHandler(
			@PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {

		List<PostResponseDto> postsByCategory = postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostResponseDto>>(postsByCategory, HttpStatus.OK);
	}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostResponseDto>> getPostBySearch(@PathVariable("keyword") String keyword) {

		List<PostResponseDto> postBySearch = postService.getPostBySearch(keyword);

		return new ResponseEntity<List<PostResponseDto>>(postBySearch, HttpStatus.OK);
	}

	@PostMapping("/{postId}/update/image")
	public ResponseEntity<PostResponseDto> updatePostImageHandler(@PathVariable("postId") Integer postId,
			@RequestParam MultipartFile image)
			throws IOException, ResourceNotFoundException, FileTypeNotValidException {

		PostResponseDto updatePostImage = this.postService.updatePostImage(postId, image);

		return new ResponseEntity<PostResponseDto>(updatePostImage, HttpStatus.ACCEPTED);

	}

	// method to serve images
	@GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void serveImageHandler(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		this.postService.serveImage(imageName, response);

	}

	// method to delete images
	@DeleteMapping("/image/{imageName}")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable("imageName") String imageName) throws IOException {

		ApiResponse apiResponse = this.postService.deleteProductImage(imageName);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.GONE);
	}

}
