package com.example.securitytokken.authentication;


import com.example.securitytokken.user.User;
import com.example.securitytokken.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("not found username: " + username));
        System.out.println("userdetails service: " + user);
        return new UserDetailsImpl(user);
    }



}
