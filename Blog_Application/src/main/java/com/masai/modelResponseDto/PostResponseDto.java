/**
 * 
 */
package com.masai.modelResponseDto;

import java.time.LocalDate;
import java.util.List;
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
public class PostResponseDto {

	private Integer postId;

	private String title;

	private String contentLine1;

	private String contentLine2;

	private String image;

	private LocalDate date;

	private CategoryResponseDto category;

	private UserResponseDto user;

	private List<CommentResponseDto> comments;

}
