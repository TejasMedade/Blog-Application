/**
 * 
 */
package com.masai.JWT.security.response;

import com.masai.modelResponseDto.UserResponseDto;

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
public class JwtAuthResponse {

	// Token will be send through cookie
	// If you dont want it from JSON Body, Delete this field
	private String Token;

	private UserResponseDto userResponseDto;

}
