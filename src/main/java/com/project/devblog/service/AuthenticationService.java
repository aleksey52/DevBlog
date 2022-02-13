package com.project.devblog.service;

import com.project.devblog.controller.dto.response.AuthenticationResponse;
import com.project.devblog.exception.VerificationException;
import com.project.devblog.model.UserEntity;
import com.project.devblog.security.JwtTokenProvider;
import static com.project.devblog.security.JwtTokenProvider.TOKEN_PREFIX;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> login(@NonNull String login, @NonNull String password) {
        final UserEntity user = userService.findByLogin(login);

        if (!user.isEnabled()) {
            if (user.getVerificationCode() != null) {
                throw new VerificationException("This account is not verified");
            }
            throw new LockedException("This account is blocked");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        final String token = jwtTokenProvider.createToken(login, user.getRole());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token)
                .body(new AuthenticationResponse(user.getId(), login));
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, auth);
    }

    public ResponseEntity<AuthenticationResponse> register(@NonNull String login, @NonNull String password) {
        if (!userService.isExists(login)) {
            UserEntity user = userService.register(login, password);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new AuthenticationResponse(user.getId(), login));
        } else {
            throw new BadCredentialsException("Login already exists");
        }
    }
}