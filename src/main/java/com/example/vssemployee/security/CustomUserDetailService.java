//package com.example.vssemployee.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * @author manhdt14
// * created in 9/1/2021 11:18 AM
// */
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username);
//        if(user ==null) {
//            throw new UsernameNotFoundException("User Not Found");
//        }
//        return new CustomUserDetails(user);
//    }
//}
