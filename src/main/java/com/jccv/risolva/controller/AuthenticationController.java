package com.jccv.risolva.controller;

import com.jccv.risolva.dto.auth.UserDto;
import com.jccv.risolva.model.auth.Token;
import com.jccv.risolva.model.auth.User;
import com.jccv.risolva.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.jccv.risolva.utils.AuthorizationsLevel.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("registerUser")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User request) {
        try {
            System.out.println("Request");
            System.out.println(request.toString());
            return ResponseEntity.ok(authenticationService.authenticate(request));

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestBody Token token) {
        System.out.println("token Jwt Controller: " + token);
        return ResponseEntity.ok(authenticationService.validateToken(token));
    }
}
