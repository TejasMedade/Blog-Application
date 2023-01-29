/**
 * 
 */
package com.masai.serviceImplementation;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masai.exceptions.FileTypeNotValidException;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.Category;
import com.masai.model.Post;
import com.masai.model.User;
import com.masai.modelRequestDto.PostRequestDto;
import com.masai.modelResponseDto.PostResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.ImageResponse;
import com.masai.payloads.PageResponse;
import com.masai.repository.CategoryRepo;
import com.masai.repository.PostRepo;
import com.masai.repository.UserRepo;
import com.masai.service.FileService;
import com.masai.service.PostService;

/**
 * @author tejas
 *
 */
@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@Override
	public PostResponseDto createPost(PostRequestDto postRequestDto, Integer userId, Integer categoryId,
			MultipartFile image) throws ResourceNotFoundException, IOException, FileTypeNotValidException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		if (image == null) {

			Post post = this.toPost(postRequestDto);
			post.setDate(LocalDate.now());
			post.setUser(user);
			post.setCategory(category);
			post.setImage("default.jpg");

			Post savedPost = this.postRepo.save(post);

			return this.toPostDto(savedPost);

		} else {

			ImageResponse uploadImage = fileService.updatePostImage(path, image);

			Post post = this.toPost(postRequestDto);
			post.setDate(LocalDate.now());
			post.setUser(user);
			post.setCategory(category);
			post.setImage(uploadImage.getFileName());

			Post savedPost = this.postRepo.save(post);

			return this.toPostDto(savedPost);
		}

	}

	@Override
	public PostResponseDto updatePostContent(PostRequestDto postRequestDto, Integer postId)
			throws ResourceNotFoundException {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		post.setTitle(postRequestDto.getTitle());
		post.setContentLine1(postRequestDto.getContentLine1());
		post.setContentLine2(postRequestDto.getContentLine2());
		post.setDate(LocalDate.now());

		Post save = postRepo.save(post);

		return this.toPostDto(save);
	}

	@Override
	public ApiResponse deletePost(Integer postId) {

		postRepo.deleteById(postId);

		return new ApiResponse(LocalDateTime.now(), "Post Deleted Successfully !", true);
	}

	@Override
	public PostResponseDto getPostsById(Integer postId) throws ResourceNotFoundException {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		return this.toPostDto(post);

	}

	@Override
	public List<PostResponseDto> getAllPosts() {

		List<Post> findAll = postRepo.findAll();

		List<PostResponseDto> listOfPosts = findAll.stream().map(p -> this.toPostDto(p)).collect(Collectors.toList());

		return listOfPosts;
	}

	@Override
	public PageResponse getAllPostsPagination(Integer page, Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Post> pagePost = postRepo.findAll(pageable);

		List<Post> content = pagePost.getContent();

		List<PostResponseDto> listOfPosts = content.stream().map(p -> this.toPostDto(p)).collect(Collectors.toList());

		PageResponse pageResponse = new PageResponse();

		pageResponse.setContent(listOfPosts);
		pageResponse.setTotalPosts(pagePost.getTotalElements());
		pageResponse.setPageSize(pagePost.getSize());
		pageResponse.setPageNumber(pagePost.getNumber());
		pageResponse.setTotalPages(pagePost.getTotalPages());
		pageResponse.setLastPage(pagePost.isLast());

		return pageResponse;
	}

	@Override
	public PageResponse getAllPostsByPageSortBy(Integer page, Integer size, String sortBy, String sortDirection) {

		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Post> pagePost = postRepo.findAll(pageable);

		List<Post> content = pagePost.getContent();

		List<PostResponseDto> listOfPosts = content.stream().map(p -> this.toPostDto(p)).collect(Collectors.toList());

		PageResponse pageResponse = new PageResponse();

		pageResponse.setContent(listOfPosts);
		pageResponse.setTotalPosts(pagePost.getTotalElements());
		pageResponse.setPageSize(pagePost.getSize());
		pageResponse.setPageNumber(pagePost.getNumber());
		pageResponse.setTotalPages(pagePost.getTotalPages());
		pageResponse.setLastPage(pagePost.isLast());

		return pageResponse;
	}

	@Override
	public List<PostResponseDto> getPostsByUser(Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		List<Post> findByUser = postRepo.findByUser(user);

		List<PostResponseDto> listOfPosts = findByUser.stream().map(p -> this.toPostDto(p))
				.collect(Collectors.toList());

		return listOfPosts;

	}

	@Override
	public List<PostResponseDto> getPostsByCategory(Integer categoryId) throws ResourceNotFoundException {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		List<Post> findByCategory = postRepo.findByCategory(category);

		List<PostResponseDto> listOfPosts = findByCategory.stream().map(p -> this.toPostDto(p))
				.collect(Collectors.toList());

		return listOfPosts;
	}

	@Override
	public List<PostResponseDto> getPostBySearch(String keyword) {

		List<Post> findByTitleContaining = this.postRepo.findByTitleContaining(keyword);

		List<PostResponseDto> listOfPosts = findByTitleContaining.stream().map(p -> this.toPostDto(p))
				.collect(Collectors.toList());

		return listOfPosts;

	}

	@Override
	public PostResponseDto updatePostImage(Integer postId, MultipartFile image)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		ImageResponse uploadImage = fileService.updatePostImage(path, image);

		post.setImage(uploadImage.getFileName());

		Post updatePost = postRepo.save(post);

		return this.toPostDto(updatePost);
	}

	@Override
	public void serveImage(String imageName, HttpServletResponse response) throws IOException {

		InputStream resource = this.fileService.servePostImage(path, imageName);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

	@Override
	public void serveImageByPostId(Integer postId, HttpServletResponse response)
			throws IOException, ResourceNotFoundException {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		InputStream resource = this.fileService.servePostImage(path, post.getImage());

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}
	
	@Override
	public ApiResponse deleteProductImage(String fileName) throws IOException {

		boolean delete = this.fileService.delete(fileName);

		if (delete) {
			return new ApiResponse(LocalDateTime.now(), "Image File Deleted Successfully !", true);
		} else {
			return new ApiResponse(LocalDateTime.now(), "Requested Image File Does Not Exist !", false);
		}

	}
	

	Post toPost(PostRequestDto postRequestDto) {

		return this.modelMapper.map(postRequestDto, Post.class);
	}

	PostResponseDto toPostDto(Post post) {

		return this.modelMapper.map(post, PostResponseDto.class);
	}

}
