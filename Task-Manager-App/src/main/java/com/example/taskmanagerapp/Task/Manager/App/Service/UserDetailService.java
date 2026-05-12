package com.example.taskmanagerapp.Task.Manager.App.Service;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserDetails;
import com.example.taskmanagerapp.Task.Manager.App.Repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDetailService  {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public Optional<UserDetails> saveEntry(UserDetails userDetails){
        userDetailsRepository.save(userDetails);
        return Optional.empty();
    }



    public List<UserDetails> getAll(){
        List<UserDetails> all = userDetailsRepository.findAll();

        return all;
    }


}
