package com.example.taskmanagerapp.Task.Manager.App.Service;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserPrincipal;
import com.example.taskmanagerapp.Task.Manager.App.Repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = (UserDetails) userDetailsRepository.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }

}

// this is class is different from traditional UserDetailService which is actually carrying the business logic
//this is for creating our own authentication service by handling the interface UserDetailsService, where we have to
// implements one method inside the interface which is loadByUserName.
//which eventually helps to fetch the data back from the database
