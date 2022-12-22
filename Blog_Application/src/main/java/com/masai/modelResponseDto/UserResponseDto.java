/**
 * 
 */
package com.masai.modelResponseDto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tejas
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private String userId;

	private String name;

	private String email;

	private String about;

	private Set<RoleResponseDto> roles;

}
