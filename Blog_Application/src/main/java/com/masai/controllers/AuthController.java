/**
 * 
 */
package com.masai.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.JWT.security.request.JwtAuthRequest;
import com.masai.JWT.security.response.JwtAuthResponse;
import com.masai.JWT.security.utils.JWTUtils;
import com.masai.exceptions.ApiException;
import com.masai.exceptions.DuplicateResourceException;
import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.User;
import com.masai.modelRequestDto.UserRequestDto;
import com.masai.modelResponseDto.UserResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.repository.UserRepo;
import com.masai.service.UserService;

/**
 * @author tejas
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/blog/auth")
public class AuthController {

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/signin")
	public ResponseEntity<?> loginHandler(@RequestBody JwtAuthRequest loginRequest) throws ApiException {

		Authentication authentication = ((AuthenticationManager) authenticationManager).authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setToken(jwtCookie.getValue());

		response.setUserResponseDto(this.modelMapper.map((User) userDetails, UserResponseDto.class));

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(response);

	}
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutHandler() {

		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new ApiResponse(LocalDateTime.now(), "You've been signed out!", true));

	}
	
	@PostMapping("/signup/user")
	public ResponseEntity<?> registerUserHandler(@Valid @RequestBody UserRequestDto userRequestDto)
			throws ResourceNotFoundException {

		if (userRepo.existsByEmail(userRequestDto.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new DuplicateResourceException("User", "Email Id", userRequestDto.getEmail()));
		}

		UserResponseDto registeredUser = this.userService.registerUser(userRequestDto);

		return new ResponseEntity<UserResponseDto>(registeredUser, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/signup/admin")
	public ResponseEntity<?> registerAdminHandler(@Valid @RequestBody UserRequestDto userRequestDto)
			throws ResourceNotFoundException {

		if (userRepo.existsByEmail(userRequestDto.getEmail())) {

			return new ResponseEntity<>(new DuplicateResourceException("User", "Email Id", userRequestDto.getEmail()),
					HttpStatus.BAD_REQUEST);

		}

		UserResponseDto registeredUser = this.userService.registerAdmin(userRequestDto);

		return new ResponseEntity<UserResponseDto>(registeredUser, HttpStatus.CREATED);
	}
	
	
	

}
