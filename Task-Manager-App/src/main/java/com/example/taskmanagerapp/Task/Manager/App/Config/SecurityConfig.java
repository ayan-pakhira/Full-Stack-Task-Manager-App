package com.example.taskmanagerapp.Task.Manager.App.Config;

import com.example.taskmanagerapp.Task.Manager.App.Service.CustomUserDetailsService;
import com.example.taskmanagerapp.Task.Manager.App.Service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                //to make all the generated requests authenticated.
                .formLogin(Customizer.withDefaults()) //this is for login form which is provided by spring security
                .httpBasic(Customizer.withDefaults()) //this is for sending the data from postman as in terms of login form and all.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //to make the http security stateless.





                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService((CustomUserDetailsService) customUserDetailsService);
        return provider;
    }


    //this is a default in the spring security but for our need and industry level we should use the
    //customize and build by us.
//    @Bean
//    public UserDetailsService userDetailsService(){
//        //here we can create object of the User and then pass it to return statement.
//        //set the username, password, roles with the help of any "passwordEncoder()"
//        // in the below way, though there are lots of way by which we can customize it accordingly.
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("Ayan")
//                .password("A@123")
//                .roles("User")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("Geet")
//                .password("G@123")
//                .roles("Admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
    // this is the one way of using the UserDetailsService interface, to communicate with that
    //we need the object inside the curly braces, otherwise we had to create a class and there
    //implements the interface, cause as we know we cannot create the object of interface
}
