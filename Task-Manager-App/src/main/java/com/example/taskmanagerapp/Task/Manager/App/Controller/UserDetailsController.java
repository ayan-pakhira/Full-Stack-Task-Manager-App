package com.example.taskmanagerapp.Task.Manager.App.Controller;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserDetails;
import com.example.taskmanagerapp.Task.Manager.App.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<UserDetails> createEntry(@RequestBody UserDetails userDetails){

        Optional<UserDetails> saved = userDetailsService.saveEntry(userDetails);

        if(saved.isPresent()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public ResponseEntity<List<UserDetails> >getAllTheUsers(){
        List<UserDetails> allvalue = userDetailsService.getAll();

        if(allvalue != null){
            return new ResponseEntity<>(allvalue, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
