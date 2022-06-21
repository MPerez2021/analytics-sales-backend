package com.project.ecommerceBi.security.services;

import com.project.ecommerceBi.security.entities.User;
import com.project.ecommerceBi.security.entities.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + userName)
                );
        return UserMain.build(user);
    }
}
