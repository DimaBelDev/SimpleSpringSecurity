package com.security.securityjwt.service;

import com.security.securityjwt.model.AuthenticationResponse;
import com.security.securityjwt.model.User;
import com.security.securityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user = repository.save(user);

        String token = jwtService.generateToken(user);

       return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(User request) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getUsername(),
                       request.getPassword())
       );
       User user = repository.findByUsername(request.getUsername()).orElseThrow();
       String token = jwtService.generateToken(user);

       return new AuthenticationResponse(token);
    }

}