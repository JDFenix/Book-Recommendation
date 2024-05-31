package com.example.system_inventory_product.service.auth;

import com.example.system_inventory_product.dto.auth.AuthenticationRequest;
import com.example.system_inventory_product.dto.auth.AuthenticationResponse;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {


    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        User user = userService.findUserByUsername(authRequest);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(User user) {
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getUsername());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("permissions",user.getAuthorities());
        return  extraClaims;
    }
}
