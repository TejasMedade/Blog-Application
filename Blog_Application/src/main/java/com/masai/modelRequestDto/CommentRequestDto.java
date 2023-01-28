/**
 * 
 */
package com.masai.modelRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class CommentRequestDto {

	@NotNull(message = "{Comment.field.invalid}")
	@NotBlank(message = "{Comment.field.invalid}")
	@NotEmpty(message = "{Comment.field.invalid}")
	private String content;

}
