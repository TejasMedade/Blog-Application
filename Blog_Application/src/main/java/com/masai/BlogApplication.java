package com.masai;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.masai.exceptions.ResourceNotFoundException;
import com.masai.model.Role;
import com.masai.model.User;
import com.masai.payloads.AppConstants;
import com.masai.repository.RoleRepo;
import com.masai.repository.UserRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	// Model Mapper Bean
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// Message Properties Bean
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource ms) {

		LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
		lvfb.setValidationMessageSource(ms);

		return lvfb;

	}

	@Override
	public void run(String... args) throws Exception {

		try {

			Role admin = new Role();
			admin.setRoleId(AppConstants.ROLE_ADMIN);
			admin.setRoleName("ROLE_ADMIN");

			Role user = new Role();
			user.setRoleId(AppConstants.ROLE_USER);
			user.setRoleName("ROLE_USER");

			List<Role> roles = List.of(admin, user);

			roleRepo.saveAll(roles);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// Default Initial Admin Setup

		Role userRole = this.roleRepo.findById(AppConstants.ROLE_USER)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Role Id", AppConstants.ROLE_USER));

		Role adminRole = this.roleRepo.findById(AppConstants.ROLE_ADMIN)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Role Id", AppConstants.ROLE_ADMIN));

		boolean existsByEmail = userRepo.existsByEmail("tejasmedade@gmail.com");

		if (existsByEmail) {

		} else {
			User author = new User();

			author.setEmail("tejasmedade@gmail.com");
			author.setAbout("Author");
			author.setName("Default Admin");
			author.getRoles().add(adminRole);
			author.getRoles().add(userRole);
			author.setPassword(passwordEncoder.encode("Tejas@1998"));

			userRepo.save(author);

		}

	}

}
