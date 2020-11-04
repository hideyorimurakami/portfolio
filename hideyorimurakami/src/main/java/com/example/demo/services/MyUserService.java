package com.example.demo.services;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.BjUser;
import com.example.demo.models.BjUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyUserService implements UserDetailsService {

//    @Override
//    public UserDetails loadUserByUsername(String username)
//        throws UsernameNotFoundException {
//        if (username.equals("admin")) {
//             return new User(username,
//                 "$2a$10$G5crbmvW44AKypkFIKtVeeaV7tNbPbaFT2ccBozQRzcGaQ5Yc2t.i",
//                 Collections.emptySet());
//        } else {
//            throw new UsernameNotFoundException("User is not found.");
//        }
//    }

    private final BjUserRepository rep;
    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        BjUser usr = rep.findByUsername(username);
        if (usr != null) {
            return new User(username, usr.getPassword(),
                Collections.emptySet());
        } else {
            throw new UsernameNotFoundException("User does not exist.");
        }
    }
}