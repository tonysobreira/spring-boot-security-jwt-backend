package com.bezkoder.springjwt;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;

@SpringBootApplication
public class SpringBootSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role r1 = new Role(ERole.ROLE_USER);
		Role r2 = new Role(ERole.ROLE_MODERATOR);
		Role r3 = new Role(ERole.ROLE_ADMIN);
		
		roleRepository.saveAll(Arrays.asList(r1, r2, r3));
		
		User u1 = new User("admin", "admin@bezkoder.com", encoder.encode("123"));
		u1.getRoles().addAll(Arrays.asList(r3));
		
		User u2 = new User("mod", "mod@bezkoder.com", encoder.encode("123"));
		u2.getRoles().addAll(Arrays.asList(r1, r2));
		
		User u3 = new User("user", "user@bezkoder.com", encoder.encode("123"));
		u3.getRoles().addAll(Arrays.asList(r1));
		
		User u4 = new User("super", "super@bezkoder.com", encoder.encode("123"));
		u4.getRoles().addAll(Arrays.asList(r1, r2, r3));
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
	}

}
