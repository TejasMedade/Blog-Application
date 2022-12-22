/**
 * 
 */
package com.masai.service;

import java.util.List;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.UserRequestDto;
import com.masai.modelRequestDto.UserUpdateRequestDto;
import com.masai.modelResponseDto.UserResponseDto;
import com.masai.payloads.ApiResponse;

/**
 * @author tejas
 *
 */

public interface UserService {

	UserResponseDto registerUser(UserRequestDto userdto) throws ResourceNotFoundException;

	UserResponseDto updateUser(UserUpdateRequestDto userdto, Integer userId) throws ResourceNotFoundException;

	UserResponseDto getUserById(Integer userId) throws ResourceNotFoundException;

	List<UserResponseDto> getAllUsers();

	ApiResponse deleteUserById(Integer userId) throws ResourceNotFoundException;

	UserResponseDto registerAdmin(UserRequestDto userdto) throws ResourceNotFoundException;

}
