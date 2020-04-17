package com.example.demo.service;

import com.example.demo.model.AccountCredentials;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository ;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean checkUsernameAndPassword(AccountCredentials accountCredentials){
     return userRepository.findByUsernameAndPassword(accountCredentials.getUsername(),accountCredentials.getPassword()).isPresent();
    }
    public User signUp(AccountCredentials accountCredentials){
        User u = new User();
        u.setPassword(accountCredentials.getPassword());
        u.setUsername(accountCredentials.getUsername());
        //check user exist
        System.out.println("user get password "+accountCredentials.getPassword() );
        //insert
        return userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetail userDetail = new CustomUserDetail(userRepository.findByUsername(username)); ;
        return userDetail;
    }
}
