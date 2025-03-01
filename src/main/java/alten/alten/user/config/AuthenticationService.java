package alten.alten.user.config;


import alten.alten.user.dto.JwtAuthenticationResponse;
import alten.alten.user.dto.RefreshTokenRequest;
import alten.alten.user.dto.SignUpRequest;
import alten.alten.user.dto.SigninRequest;
import alten.alten.user.model.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}