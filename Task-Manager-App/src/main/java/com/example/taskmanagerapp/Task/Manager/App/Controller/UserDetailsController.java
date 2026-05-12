package com.example.taskmanagerapp.Task.Manager.App.Controller;

import com.example.taskmanagerapp.Task.Manager.App.Entity.UserDetails;
import com.example.taskmanagerapp.Task.Manager.App.Service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping
    public ResponseEntity<UserDetails> createEntry(@RequestBody UserDetails userDetails){

        Optional<UserDetails> saved = userDetailService.saveEntry(userDetails);

        if(saved.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }


    @GetMapping
    public ResponseEntity<List<UserDetails> >getAllTheUsers(){
        List<UserDetails> allvalue = userDetailService.getAll();

        if(allvalue != null){
            return new ResponseEntity<>(allvalue, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
