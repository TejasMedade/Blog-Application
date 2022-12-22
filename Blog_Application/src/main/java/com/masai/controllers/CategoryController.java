/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.CategoryRequestDto;
import com.masai.modelResponseDto.CategoryResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.service.CategoryService;

/**
 * @author tejas
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	

	@PostMapping("/")
	ResponseEntity<CategoryResponseDto> createCategoryHandler(
			@Valid @RequestBody CategoryRequestDto categoryRequestDto) {

		CategoryResponseDto category = categoryService.createCategory(categoryRequestDto);

		return new ResponseEntity<>(category, HttpStatus.CREATED);

	}
	

	@PutMapping("/{categoryId}")
	ResponseEntity<CategoryResponseDto> updateCategoryHandler(@Valid @RequestBody CategoryRequestDto categoryRequestDto,
			@PathVariable("categoryId") Integer categoryId) throws ResourceNotFoundException {

		CategoryResponseDto updateCategory = categoryService.updateCategory(categoryRequestDto, categoryId);

		return new ResponseEntity<>(updateCategory, HttpStatus.ACCEPTED);

	}
	
	
	@DeleteMapping("/{categoryId}")
	ResponseEntity<ApiResponse> deleteCategoryHandler(@PathVariable("categoryId") Integer categoryId) {

		ApiResponse deleteCategory = categoryService.deleteCategory(categoryId);

		return new ResponseEntity<ApiResponse>(deleteCategory, HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	ResponseEntity<CategoryResponseDto> getCategoryHandler(@PathVariable("categoryId") Integer categoryId)
			throws ResourceNotFoundException {

		CategoryResponseDto category = categoryService.getCategory(categoryId);

		return new ResponseEntity<CategoryResponseDto>(category, HttpStatus.OK);
	}
	
	@GetMapping("/")
	ResponseEntity<List<CategoryResponseDto>> getAllCategoriesHandler() {

		List<CategoryResponseDto> allCategories = categoryService.getAllCategories();

		return new ResponseEntity<List<CategoryResponseDto>>(allCategories, HttpStatus.OK);
	}

}
