package com.practice.store.seed;

import com.practice.store.model.Role;
import com.practice.store.model.RoleEnum;
import com.practice.store.model.UserEntity;
import com.practice.store.repository.RoleRepository;
import com.practice.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;


//@Component
public class DataLoader {//implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName(RoleEnum.ADMIN);
        roleRepository.save(adminRole);

        Role customerRole = new Role();
        adminRole.setName(RoleEnum.CUSTOMER);
        roleRepository.save(customerRole);

        Role managerRole = new Role();
        adminRole.setName(RoleEnum.ADMIN);
        roleRepository.save(managerRole);

        UserEntity userEntity = new UserEntity();
        userEntity.setRoles(Set.of(adminRole));
        userEntity.setUsername("admin");
        userEntity.setPassword(encoder.encode("admin"));
        userEntity.setFirstName("FirstName");
        userEntity.setLastName("LastName");
        userEntity.setEmail("email@email.com");
    }
}
