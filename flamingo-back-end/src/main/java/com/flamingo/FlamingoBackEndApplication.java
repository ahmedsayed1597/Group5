package com.flamingo;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.flamingo.persistence.dao.RoleRepo;
import com.flamingo.persistence.entities.Role;

@SpringBootApplication
public class FlamingoBackEndApplication implements CommandLineRunner {

	public static final Long ADMIN_ID = 101L;
	public static final Long USER_ID = 102L;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlamingoBackEndApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// store the the id of admin and user in database
	@Override
	public void run(String... args) throws Exception {
		try {
			Role adminRole = new Role();
			adminRole.setRoleId(ADMIN_ID);
			adminRole.setRoleName("ADMIN");

			Role userRole = new Role();
			userRole.setRoleId(USER_ID);
			userRole.setRoleName("USER");

			List<Role> roles = List.of(adminRole, userRole);
			/* Hibernate: select r1_0.role_id,r1_0.role_name from roles r1_0 where
			 r1_0.role_id=? */
			/*  Hibernate: select r1_0.role_id,r1_0.role_name from roles r1_0 where
			 r1_0.role_id=? */
			roleRepo.saveAll(roles);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
