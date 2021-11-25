package com.nagarro.backendapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.backendapi.dto.RegisterDto;
import com.nagarro.backendapi.models.JwtRequest;
import com.nagarro.backendapi.models.JwtResponse;
import com.nagarro.backendapi.models.User;
import com.nagarro.backendapi.repo.UserRepository;
import com.nagarro.backendapi.service.MyUserDetailsService;
import com.nagarro.backendapi.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private UserRepository userRepository;

//	@GetMapping(path = "/getResults")
//	public ResponseEntity<?> getResults() {
//		return ResponseEntity.ok("This page gives results");
//	}

	@PostMapping(path = "/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {

		User user = new User();
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		user.setRole("user");
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());

		System.out.println(registerDto);
		userRepository.save(user);

		return ResponseEntity.ok(null);

	}

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		System.out.println(jwtRequest);

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("bad credentials");

		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("bad credentials");
		}

		// email and password match
		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println(token);

		return ResponseEntity.ok(new JwtResponse(token));
	}
}
