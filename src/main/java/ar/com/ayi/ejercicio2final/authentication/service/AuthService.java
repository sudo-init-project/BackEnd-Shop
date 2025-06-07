package ar.com.ayi.ejercicio2final.authentication.service;

import ar.com.ayi.ejercicio2final.authentication.jwt.service.JwtService;
import ar.com.ayi.ejercicio2final.authentication.model.AuthResponse;
import ar.com.ayi.ejercicio2final.authentication.model.LoginRequest;
import ar.com.ayi.ejercicio2final.authentication.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        UserDetails userDetails = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (userDetails.getPassword().equals(request.getPassword())) {

            String token = jwtService.getToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } else {

            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
