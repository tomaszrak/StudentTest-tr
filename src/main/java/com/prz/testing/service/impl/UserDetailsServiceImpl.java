package com.prz.testing.service.impl;

import com.prz.testing.domain.Role;
import com.prz.testing.enumerate.RoleName;
import com.prz.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROLO on 25.11.2015.
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String indexNumber) throws UsernameNotFoundException {

        UserDetails userDetails = null;
        try {
            com.prz.testing.domain.User userData = userRepository.getByIndexNumber(indexNumber);
            userDetails = new User(userData.getIndexNumber().toString(), userData.getPassword(), true, true, true, true, getAuthirities(userData.getRole().getName()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthirities(RoleName name) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (name.equals(RoleName.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(RoleName.ADMIN.getRoleName()));
        } else if (name.equals(RoleName.STUDENT)) {
            authorities.add(new SimpleGrantedAuthority(RoleName.STUDENT.getRoleName()));
        } else if (name.equals(RoleName.TEACHER)) {
            authorities.add(new SimpleGrantedAuthority(RoleName.TEACHER.getRoleName()));
        }

        return authorities;

    }
}
