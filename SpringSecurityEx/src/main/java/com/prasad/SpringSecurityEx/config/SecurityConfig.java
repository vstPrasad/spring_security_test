package com.prasad.SpringSecurityEx.config;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //This tells that don't go to default config go to my custom config
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(customizer ->customizer.disable());   //we're disabling the csrf
//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());  //for any request authentication is applied
//        //http.formLogin(Customizer.withDefaults());    //for getting default login form
//        http.httpBasic(Customizer.withDefaults());    //for getting access to postman, restApi access
//        http.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  //for making http stateless
//
//        return http.build();
//    }

    //we are simplify above code in build up pattern for code readability
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf(customizer ->customizer.disable())   //we're disabling the csrf
             .authorizeHttpRequests(request -> request.anyRequest().authenticated())  //for any request authentication is applied
              .formLogin(Customizer.withDefaults())    //for getting default login form
              .httpBasic(Customizer.withDefaults())    //for getting access to postman, restApi access
              .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  //for making http stateless
              .build();
    }

    //Hardcoded username and password:
//    @Bean
//    public UserDetailsService userDetailsService(){   //interface
//
//        UserDetails user1= User         //UserDetails is interface and User is class implements UserDetails interface
//                .withDefaultPasswordEncoder()
//                .username("Omkar")
//                .password("omkar123")
//                .roles("Admin")
//                .build();
//
//        UserDetails user2= User         //UserDetails is interface and User is class implements UserDetails interface
//                .withDefaultPasswordEncoder()
//                .username("Sangram")
//                .password("sangram123")
//                .roles("User")
//                .build();
//
//
//         return new InMemoryUserDetailsManager(user1,user2);    //inbuilt class which implements UserDetailsService interface
//    }

     @Bean
    public AuthenticationProvider authenticationProvider(){
         DaoAuthenticationProvider provider=new DaoAuthenticationProvider();   //DaoAuthenticationProvider implements AuthenticationProvider
         provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
         provider.setUserDetailsService(userDetailsService);
         return  provider;
     }
}
