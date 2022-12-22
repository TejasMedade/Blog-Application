/**
 * 
 */
package com.masai.modelRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {

	@NotEmpty(message = "{Category.title.invalid}")
	@NotNull(message = "{Category.title.invalid}")
	@NotBlank(message = "{Category.title.invalid}")
	@Size(min = 3, message = "Category Title Should be Minimum of 3 Characters")
	private String categoryTitle;

	@NotEmpty(message = "{Category.description.invalid}")
	@NotNull(message = "{Category.description.invalid}")
	@NotBlank(message = "{Category.description.invalid}")
	private String categoryDescription;

}
