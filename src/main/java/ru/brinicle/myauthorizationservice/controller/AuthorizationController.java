package ru.brinicle.myauthorizationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.brinicle.myauthorizationservice.dto.DtoAuthenticationRequest;
import ru.brinicle.myauthorizationservice.dto.DtoAuthenticationResponse;
import ru.brinicle.myauthorizationservice.entity.User;
import ru.brinicle.myauthorizationservice.repository.UserRepository;
import ru.brinicle.myauthorizationservice.security.jwt.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthorizationController(AuthenticationManager authenticationManager,
                                    UserRepository userRepository,
                                    JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //TODO: 1) Docker 4) config 5) app.yaml за контейнер

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login to the system")
    @ApiResponse(responseCode = "200", description = "Successful login")
    public ResponseEntity<DtoAuthenticationResponse> login(@RequestBody DtoAuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));

        String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());

        DtoAuthenticationResponse response = new DtoAuthenticationResponse();
        response.setEmail(request.getEmail());
        response.setToken(token);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Logout from the system")
    @ApiResponse(responseCode = "200", description = "Successful logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        var securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
