package com.masai.service;

import java.util.List;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.CategoryRequestDto;
import com.masai.modelResponseDto.CategoryResponseDto;
import com.masai.payloads.ApiResponse;

public interface CategoryService {

	CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

	CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Integer categoryId)
			throws ResourceNotFoundException;

	ApiResponse deleteCategory(Integer categoryId);

	CategoryResponseDto getCategory(Integer categoryId) throws ResourceNotFoundException;

	List<CategoryResponseDto> getAllCategories();

}
