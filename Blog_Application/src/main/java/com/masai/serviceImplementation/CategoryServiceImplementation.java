/**
 * 
 */
package com.masai.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.Category;
import com.masai.modelRequestDto.CategoryRequestDto;
import com.masai.modelResponseDto.CategoryResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.repository.CategoryRepo;
import com.masai.service.CategoryService;

/**
 * @author tejas
 *
 */
@Service
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {

		Category category = this.toCategory(categoryRequestDto);

		Category saved_category = categoryRepo.save(category);

		return this.toCategoryDto(saved_category);
	}

	@Override
	public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Integer categoryId)
			throws ResourceNotFoundException {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		category.setCategoryTitle(categoryRequestDto.getCategoryTitle());
		category.setCategoryDescription(categoryRequestDto.getCategoryDescription());

		Category updatedCategory = categoryRepo.save(category);

		return this.toCategoryDto(updatedCategory);
	}

	@Override
	public ApiResponse deleteCategory(Integer categoryId) {

		categoryRepo.deleteById(categoryId);

		return new ApiResponse(LocalDateTime.now(), "Category Deleted Successfully !", true);

	}

	@Override
	public CategoryResponseDto getCategory(Integer categoryId) throws ResourceNotFoundException {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.toCategoryDto(category);
	}

	@Override
	public List<CategoryResponseDto> getAllCategories() {

		List<Category> listofcategories = categoryRepo.findAll();

		List<CategoryResponseDto> listofcategoriesdto = listofcategories.stream().map(c -> this.toCategoryDto(c))
				.collect(Collectors.toList());

		return listofcategoriesdto;
	}

	public Category toCategory(CategoryRequestDto categoryRequestDto) {

		return modelMapper.map(categoryRequestDto, Category.class);

	}

	public CategoryResponseDto toCategoryDto(Category category) {

		return modelMapper.map(category, CategoryResponseDto.class);

	}

}
