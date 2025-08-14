package lms.auth_service.service;

import io.jsonwebtoken.JwtException;
import lms.auth_service.DTO.LoginRequestDTO;
import lms.auth_service.jwt.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private  final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(user->passwordEncoder.matches(loginRequestDTO.getPassword(),user.getPassword()))
                .map(user -> jwtUtil.generateToken(user.getPassword(),user.getRole()));
    }

    public boolean validateToken(String token) {
        try{
            jwtUtil.validateToken(token);
            return true;
        }
        catch (JwtException e){
            return false;
        }
    }

}
