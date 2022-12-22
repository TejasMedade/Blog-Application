/**
 * 
 */
package com.masai.modelResponseDto;

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
public class CategoryResponseDto {

	private Integer categoryId;

	private String categoryTitle;

	private String categoryDescription;

}
