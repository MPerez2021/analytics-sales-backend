package com.project.ecommerceBi.security.controllers;

import com.project.ecommerceBi.dtos.Message;
import com.project.ecommerceBi.security.dtos.JwtDto;
import com.project.ecommerceBi.security.dtos.LoginUser;
import com.project.ecommerceBi.security.dtos.NewUser;
import com.project.ecommerceBi.security.entities.Role;
import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.enums.RoleName;
import com.project.ecommerceBi.security.jwt.JwtProvider;
import com.project.ecommerceBi.security.services.RoleService;
import com.project.ecommerceBi.security.services.UserService;
import com.project.ecommerceBi.security.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    private RoleService roleService;

    private JwtProvider jwtProvider;

    @Value("${jwt.accessTokenCookieName}")
    private String cookieName;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                          UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(HttpServletResponse httpServletResponse, @Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult) {
        if (bidBindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise sus credenciales 1"), HttpStatus.BAD_REQUEST);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            CookieUtil.create(httpServletResponse, cookieName, jwt, false, -1, "localhost");
            return new ResponseEntity<>(new Message("Sesión iniciada"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Revise sus credenciales 2 " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User();
        Set<Role> roles = new HashSet<>();
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        roles.add(roleService.getByRoleName(RoleName.ROLE_CLIENT).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("usuario creado"), HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<Message> logOut(HttpServletResponse response) {
        CookieUtil.clear(response, cookieName);
        return new ResponseEntity<>(new Message("Sesión cerrada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/test")
    public ResponseEntity<Message> logOut() {
        return new ResponseEntity<>(new Message("Hola"), HttpStatus.OK);
    }

}
