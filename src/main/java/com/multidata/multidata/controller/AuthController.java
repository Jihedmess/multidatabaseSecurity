package com.multidata.multidata.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.multidata.multidata.models.DataBase;
import com.multidata.multidata.models.ERole;
import com.multidata.multidata.models.Role;
import com.multidata.multidata.models.User;
import com.multidata.multidata.repository.RoleRepository;
import com.multidata.multidata.repository.UserRepository;
import com.multidata.multidata.request.LoginRequest;
import com.multidata.multidata.request.SignupRequest;
import com.multidata.multidata.response.JwtResponse;
import com.multidata.multidata.response.MessageResponse;
import com.multidata.multidata.security.jwt.JwtUtils;
import com.multidata.multidata.security.service.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@GetMapping(path = "/findAllUser")
    @ResponseBody
    public ResponseEntity findAllMP() {

        StringBuffer retBuf = new StringBuffer();

        List<User> listUser = (List<User>) userRepository.findAll();
         return ResponseEntity.ok().body(listUser);
       
    }
	
	@GetMapping(path = "/findById/{id}")
    @ResponseBody
    public ResponseEntity findById(@PathVariable long id) {

        StringBuffer retBuf = new StringBuffer();

        Optional<User> centreIntertAccount = userRepository.findById(id);

      
        return ResponseEntity.ok().body(centreIntertAccount);
    }
	
	@DeleteMapping(path = "/deleteUser/{id}")
    @ResponseBody
    public ResponseEntity deleteById(@PathVariable long id) {

        StringBuffer retBuf = new StringBuffer();

        userRepository.deleteById(id);

        retBuf.append("user has been deleted successfully.");

        return ResponseEntity.ok().body(retBuf.toString()) ;
    }
	
	@PutMapping(path = "/updateUser")
    @ResponseBody
    public ResponseEntity updateMp(@RequestBody User user ) {

        StringBuffer retBuf = new StringBuffer();

        Optional<User> newuser = userRepository.findById(user.getId());

        if (newuser != null) {
           
        	newuser.get().setUsername(user.getUsername());
        	newuser.get().setEmail(user.getEmail());
        	newuser.get().setPassword(encoder.encode(user.getPassword()));
        	
        	           	
        	userRepository.save(newuser.get());
            
        }

        retBuf.append("user data update successfully.");

        return ResponseEntity.ok().body(retBuf.toString()) ;
    }
 
}
