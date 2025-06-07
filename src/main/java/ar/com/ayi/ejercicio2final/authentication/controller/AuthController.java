package ar.com.ayi.ejercicio2final.authentication.controller;

import ar.com.ayi.ejercicio2final.authentication.model.AuthResponse;
import ar.com.ayi.ejercicio2final.authentication.model.LoginRequest;
import ar.com.ayi.ejercicio2final.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){


        return ResponseEntity.ok(authService.login(request));
    }

}
