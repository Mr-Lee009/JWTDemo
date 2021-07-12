package com.mta.jwt.demo.service;

import com.mta.jwt.demo.dao.RoleDAO;
import com.mta.jwt.demo.dao.UserDAO;
import com.mta.jwt.demo.model.RoleEntity;
import com.mta.jwt.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("DA VAO HAM");
        try {
            UserEntity user = userDAO.findUserAccount(userName);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + userName);
            }
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            List<RoleEntity> lstRules = roleDAO.findRule(user.getId());
            for (RoleEntity x : lstRules) {
                grantedAuthorities.add(new SimpleGrantedAuthority(x.getName()));
                System.out.println(x.toString());
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
