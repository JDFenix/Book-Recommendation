package com.example.system_inventory_product.controller.auth;

import com.example.system_inventory_product.dto.auth.AuthenticationRequest;
import com.example.system_inventory_product.dto.auth.AuthenticationResponse;
import com.example.system_inventory_product.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;
    @PreAuthorize("permitAll")

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody @Valid AuthenticationRequest authenticationRequest){

       AuthenticationResponse jwtDto = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @PreAuthorize("permitAll")
    @GetMapping("public-access")
    public  String  publicAccessEndpoint(){
        return "este endpoint es publico";
    }


}
