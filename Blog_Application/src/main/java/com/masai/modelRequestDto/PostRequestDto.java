/**
 * 
 */
package com.masai.modelRequestDto;

import javax.persistence.Column;

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
public class PostRequestDto {

	private String title;

	@Column(length = Integer.MAX_VALUE)
	private String contentLine1;

	@Column(length = Integer.MAX_VALUE)
	private String contentLine2;

}
