package com.paes.movie_catalog.service;

import com.paes.movie_catalog.dto.AuthRequestDTO;
import com.paes.movie_catalog.dto.AuthResponseDTO;
import com.paes.movie_catalog.dto.RegisterRequestDTO;
import com.paes.movie_catalog.enums.Role;
import com.paes.movie_catalog.model.User;
import com.paes.movie_catalog.repository.UserRepository;
import com.paes.movie_catalog.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new RuntimeException("Username já está em uso");
        }
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já está em uso");
        }
        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(), dto.password()));
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, user.getUsername(), user.getRole().name());
    }
}