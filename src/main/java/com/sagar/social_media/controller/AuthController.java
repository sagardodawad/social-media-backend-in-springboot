package com.sagar.social_media.controller;

import com.sagar.social_media.config.JwtProvider;
import com.sagar.social_media.models.User;
import com.sagar.social_media.repository.UserRepository;
import com.sagar.social_media.request.LoginRequest;
import com.sagar.social_media.response.AuthResponse;
import com.sagar.social_media.service.CustomUserDetailService;
import com.sagar.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetails;

    @PostMapping("/signup")

    public AuthResponse addNewUser(@RequestBody User user) throws Exception {
        User isExist=userRepository.findByEmail(user.getEmail());
        if (isExist!=null) throw new Exception("this email are exists");

        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());
        User savedUser=userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token =JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"register is success");
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token =JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"login is success");

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails= customUserDetails.loadUserByUsername(email);
        if (userDetails==null){
            throw new BadCredentialsException("invalid username");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword()))
            throw new BadCredentialsException("incorrect password");

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
