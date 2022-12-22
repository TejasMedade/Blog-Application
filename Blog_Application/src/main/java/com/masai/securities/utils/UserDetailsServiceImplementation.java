/**
 * 
 */
package com.masai.securities.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.model.User;
import com.masai.repository.UserRepo;

/**
 * @author tejas
 *
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	//Loading User From Database
	//Before this, Extend User by UserDetails	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with Username : " + username));

		return user;

	}

}
