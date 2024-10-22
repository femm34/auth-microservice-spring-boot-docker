//package com.fecd.auth.services.impl;
//
//import com.fecd.auth.commons.RolesFunctions;
//import com.fecd.auth.commons.constants.RoleName;
//import com.fecd.auth.commons.dto.CreateUserDto;
//import com.fecd.auth.commons.dto.TokenResponseDto;
//import com.fecd.auth.commons.exceptions.AuthException;
//import com.fecd.auth.models.dao.RoleDao;
//import com.fecd.auth.models.dao.UserDao;
//import com.fecd.auth.models.entities.Roles;
//import com.fecd.auth.models.entities.User;
//import com.fecd.auth.services.JwtService;
//import com.fecd.auth.utils.MyPasswordEncoder;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.EnumSet;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class AuthServiceImplTest {
//    @Mock
//    private UserDao userDao;
//
//    @Mock
//    private RoleDao roleDao;
//
//    @Mock
//    private JwtService jwtService;
//
//    @Mock
//    private MyPasswordEncoder myPasswordEncoder;
//
//    @InjectMocks
//    private AuthServiceImpl authService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRegisterSuccess() {
//        Set<RoleName> roleNames = EnumSet.of(RoleName.ROLE_ADMIN, RoleName.ROLE_USER, RoleName.ROLE_MODERATOR);
//        Set<Roles> adminRoles = RolesFunctions.createRolesSet(roleNames);
//
//        User user = User.builder()
//                .name("Fernando Emanuel")
//                .username("fecd")
//                .password("123456")
//                .email("fmartinez@casystems.com.mx")
//                .roles(adminRoles)
//                .id(1L)
//                .build();
//
//        user.setId(10L);
//        user.setUsername("testuser");
//
//        when(userDao.save(any(User.class))).thenReturn(user);
//        when(jwtService.generateToken(user.getId())).thenReturn("mockToken");
//
//        TokenResponseDto tokenResponse = authService.register(CreateUserDto.builder()
//                        .email(user.getEmail())
//                        .name(user.getName())
//                        .password(user.getPassword())
//                        .rolesSet(user.getRoles())
//                        .username(user.getUsername())
//                .build());
//
//        assertNotNull(tokenResponse);
//        assertEquals("mockToken", tokenResponse.getAccessToken());
//        verify(userDao).save(any(User.class));
//    }
//
//    @Test
//    void testRegisterRoleNotFound() {
//        CreateUserDto createUserDto = new CreateUserDto();
//        createUserDto.setRolesSet(Set.of(Roles.ROLE_ADMIN));
//
//        when(roleDao.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.empty());
//
//        AuthException exception = assertThrows(AuthException.class, () -> {
//            authService.register(createUserDto);
//        });
//        assertEquals("Role not found: ROLE_ADMIN", exception.getMessage());
//    }
//
//
//}
