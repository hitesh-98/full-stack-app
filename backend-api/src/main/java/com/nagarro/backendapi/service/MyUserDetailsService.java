package com.nagarro.backendapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.backendapi.models.MyUserDetails;
import com.nagarro.backendapi.models.User;
import com.nagarro.backendapi.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = this.userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not Found");
		} else {
			return new MyUserDetails(user);
		}

//		if (userName.equals("Hitesh")) {
//			return new User("Hitesh", "Hitesh123", new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not Found");
//		}

	}

}
