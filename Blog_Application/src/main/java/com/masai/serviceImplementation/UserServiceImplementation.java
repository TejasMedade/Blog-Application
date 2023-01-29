package com.masai.serviceImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.Role;
import com.masai.model.User;
import com.masai.modelRequestDto.UserRequestDto;
import com.masai.modelRequestDto.UserUpdateRequestDto;
import com.masai.modelResponseDto.UserResponseDto;
import com.masai.payloads.ApiResponse;
import com.masai.payloads.AppConstants;
import com.masai.repository.RoleRepo;
import com.masai.repository.UserRepo;
import com.masai.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDto registerUser(UserRequestDto userdto) throws ResourceNotFoundException {

		// Create new user's account
		User user = this.modelMapper.map(userdto, User.class);

		Set<Role> roles = user.getRoles();

		Role role = this.roleRepo.findById(AppConstants.ROLE_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Role Id", AppConstants.ROLE_USER));

		roles.add(role);

		user.setRoles(roles);

		// Encoding Password
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));

		User saved_user = userRepo.save(user);

		return this.toUserDto(saved_user);

	}

	@Override
	public UserResponseDto registerAdmin(UserRequestDto userdto) throws ResourceNotFoundException {

		// Create new user's account
		User user = this.modelMapper.map(userdto, User.class);

		Set<Role> roles = user.getRoles();

		Role userRole = this.roleRepo.findById(AppConstants.ROLE_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Role Id", AppConstants.ROLE_USER));

		Role adminRole = this.roleRepo.findById(AppConstants.ROLE_ADMIN)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Role Id", AppConstants.ROLE_ADMIN));

		roles.add(userRole);
		roles.add(adminRole);

		user.setRoles(roles);

		// Encoding Password
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));

		User saved_user = userRepo.save(user);

		return this.toUserDto(saved_user);

	}

	@Override
	public UserResponseDto updateUser(UserUpdateRequestDto userdto, Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		user.setName(userdto.getName());
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		user.setAbout(userdto.getAbout());

		User updatedUser = userRepo.save(user);

		return this.toUserDto(updatedUser);

	}

	@Override
	public UserResponseDto getUserById(Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		return this.toUserDto(user);
	}

	@Override
	public List<UserResponseDto> searchUserByName(String keyword) throws ResourceNotFoundException {

		List<User> findByNameContaining = userRepo.findByNameContaining(keyword);

		return findByNameContaining.stream().map(u -> this.toUserDto(u)).collect(Collectors.toList());

	}

	@Override
	public List<UserResponseDto> getAllUsers() {

		List<User> listOfUsers = userRepo.findAll();

		List<UserResponseDto> listOfUserDtos = listOfUsers.stream().map(u -> this.toUserDto(u))
				.collect(Collectors.toList());

		return listOfUserDtos;
	}

	@Override
	public ApiResponse deleteUserById(Integer userId) {

		userRepo.deleteById(userId);

		return new ApiResponse(LocalDateTime.now(), "User Deleted Successfully !", true);

	}

	public User toUser(UserRequestDto userDto) {

		User user = modelMapper.map(userDto, User.class);

		return user;

	}

	public UserResponseDto toUserDto(User user) {

		UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);

		return userDto;

	}

}
