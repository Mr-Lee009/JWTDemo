package com.mta.jwt.demo.service;

import com.mta.jwt.demo.dao.RoleDAO;
import com.mta.jwt.demo.dao.UserDAO;
import com.mta.jwt.demo.model.RoleEntity;
import com.mta.jwt.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userDAO.findUserAccount(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            List<RoleEntity> roles = roleDAO.findRule(user.getId());
            for (RoleEntity role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } catch (SQLException ex) {
            System.out.println("++++++++++++++++++++++");
            System.out.println(ex);
            System.out.println("++++++++++++++++++++++");
        }
        return null;
    }

}
