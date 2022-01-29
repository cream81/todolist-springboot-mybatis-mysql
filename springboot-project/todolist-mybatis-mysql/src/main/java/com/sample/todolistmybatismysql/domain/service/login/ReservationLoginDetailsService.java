package com.sample.todolistmybatismysql.domain.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.todolistmybatismysql.domain.model.entity.User;
import com.sample.todolistmybatismysql.domain.repository.login.UsersRepository;

@Service
public class ReservationLoginDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        return new ReservationLoginDetails(user);
    }
}