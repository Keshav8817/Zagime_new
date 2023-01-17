package org.cyfwms.common.login.service;

import org.cyfwms.common.login.entity.Users;
import org.cyfwms.common.login.util.CustomUserDetails;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new Users("admin", "password", new ArrayList<>());
        Users user = usersService.findByUsername(username);

        //This is to get Lazy Fetch working
        Hibernate.initialize(user.getRoles());
        if(user == null){
            throw new UsernameNotFoundException("Users Not Found.");
        }
        return new CustomUserDetails(user);
    }
}
