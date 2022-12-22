/**
 * 
 */
package com.masai.payloads;

import java.util.List;

import com.masai.modelResponseDto.PostResponseDto;

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
public class PageResponse {

	private List<PostResponseDto> content;

	private Integer pageNumber;

	private Integer pageSize;

	private Integer totalPages;

	private Long totalPosts;

	private boolean lastPage;
}
