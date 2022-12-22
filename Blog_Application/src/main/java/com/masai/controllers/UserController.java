/**
 * 
 */
package com.masai.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.modelRequestDto.UserUpdateRequestDto;
import com.masai.modelResponseDto.UserResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.service.UserService;

/**
 * @author tejas
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDto> updateUserHandler(@Valid @RequestBody UserUpdateRequestDto userdto,
			@PathVariable("userId") Integer userId) throws ResourceNotFoundException {

		UserResponseDto updatedUser = userService.updateUser(userdto, userId);

		return new ResponseEntity<UserResponseDto>(updatedUser, HttpStatus.ACCEPTED);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDto> getUserByIdHandler(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {

		UserResponseDto user = userService.getUserById(userId);

		return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserResponseDto>> getAllUsersHandler() {

		List<UserResponseDto> allUsers = userService.getAllUsers();

		return new ResponseEntity<List<UserResponseDto>>(allUsers, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer userId)
			throws ResourceNotFoundException {

		ApiResponse deleteUserById = userService.deleteUserById(userId);

		return new ResponseEntity<ApiResponse>(deleteUserById, HttpStatus.OK);
	}

}
