/**
 * 
 */
package com.masai.modelResponseDto;

import java.time.LocalDate;

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
public class CommentResponseDto {

	private Integer commentId;

	private Integer commentByUserId;

	private String commentByUserName;

	private String content;

	private LocalDate date;

}
