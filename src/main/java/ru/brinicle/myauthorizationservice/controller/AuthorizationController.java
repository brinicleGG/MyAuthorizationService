package ru.brinicle.myauthorizationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.brinicle.myauthorizationservice.dto.DtoAuthenticationRequest;

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DtoAuthenticationRequest dtoAuthenticationRequest) {
        return ResponseEntity.ok("LOGIN");
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @PostMapping("/logout")
    public void logout() {

    }
}
