package com.mobileapp.chemotherapyextravasation.controller;

import com.mobileapp.chemotherapyextravasation.dto.JWTAuthResponse;
import com.mobileapp.chemotherapyextravasation.dto.LoginDto;
import com.mobileapp.chemotherapyextravasation.dto.RegisterDto;
import com.mobileapp.chemotherapyextravasation.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Build login rest API
     * @param loginDto
     * @return JWTAuthResponse
     */
    //http://localhost:8080/api/auth/signin url can be either /login or /signin
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    /**
     * Build register rest API
     * @param registerDto
     * @return
     */
    // http://localhost:8080/api/auth/register
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
