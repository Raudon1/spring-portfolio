package com.cac.portfolio.service;

import com.cac.portfolio.domain.Role;
import com.cac.portfolio.domain.User;
import com.cac.portfolio.repo.RoleRepo;
import com.cac.portfolio.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private RoleRepo roleRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void test_loadUserByUsername() {
        //Given
        String expected = "TestU";
        String expectedP = "testPass";
        User user = new User();
        user.setUsername(expected);
        user.setPassword(expectedP);
        user.setRoles(new ArrayList<>());
        when(userRepo.findByUsername(expected)).thenReturn(user);
        //When
        UserDetails obtainedUser = userServiceImpl.loadUserByUsername(expected);
        //Then
        Assertions.assertThat(obtainedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void test_saveUser() {
        //Given
        String expected = "testU";
        String expectedP = "testPass";
        User user = new User();
        user.setName(expected);
        user.setPassword(expectedP);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        when(userRepo.save(user)).thenReturn(user);
        //When
        User obtainedUser = userServiceImpl.saveUser(user);
        //Then
        Assertions.assertThat(obtainedUser.getName()).isEqualTo(expected);

    }

    @Test
    void test_saveRole() {
        //Given
        String expected = "testR";
        Role role = new Role();
        role.setName(expected);
        when(roleRepo.save(role)).thenReturn(role);
        //When
        Role obtainedRole = userServiceImpl.saveRole(role);
        //Then
        Assertions.assertThat(obtainedRole.getName()).isEqualTo(expected);
    }

    @Test
    void test_addRoleToUser() {
        //Given
        String expectedU = "testU";
        String expectedR = "testR";
        User user = Mockito.mock(User.class);
        Role role = new Role();
        Collection<Role> roleList = new ArrayList<>();
        when(user.getRoles()).thenReturn(roleList);
        when(roleRepo.findByName(expectedR)).thenReturn(role);
        when(userRepo.findByUsername(expectedU)).thenReturn(user);
        //When
        Boolean test = userServiceImpl.addRoleToUser(expectedU,expectedR);
        //Then
        Assertions.assertThat(test).isTrue();
    }

    @Test
    void test_getUser() {
        //Given
        String expected = "testU";
        User user = new User();
        user.setName(expected);
        when(userRepo.findByUsername(expected)).thenReturn(user);
        //When
        User obtainedUser = userServiceImpl.getUser(expected);
        //Then
        Assertions.assertThat(obtainedUser.getName()).isEqualTo(expected);
    }

    @Test
    void test_getUsers() {
        //Given
        List<User> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);
        //When
        List<User> userObtainedList = userServiceImpl.getUsers();
        //Then
        Assertions.assertThat(userObtainedList).isNotNull();
    }
}