/**
 * 
 */
package com.masai.JWT.security.request;

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
public class JwtAuthRequest {

	private String username;

	private String password;

}
