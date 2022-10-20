package com.sa.gateway.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.sa.gateway.models.*;
import com.sa.gateway.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class JwtAuthenticationController {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RestTemplate template;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @CrossOrigin(value = "http://localhost:3000")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        AuthenticationStatus status = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (!status.getIsAuthenticated()) {
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "uri");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token,status.getUser()));
    }

    private AuthenticationStatus authenticate(String username, String password) {
        AuthenticationStatus status;
        AuthenticationResponse response=template.getForObject("http://localhost:8080/api/account/authenticate/"+username+"/"+password,AuthenticationResponse.class);
        //boolean condition= response.equals("true");
        if (response==null) {
            status = new AuthenticationStatus(false, "Invalid Username/Password",null);
        }
        else {
            status = new AuthenticationStatus(true, "Authentication Successful",response);
        }

        return status;
    }
}
