package com.example.helthTracking.security.authentication;

import com.example.helthTracking.security.JwtUtil;
import com.example.helthTracking.user.AppUser;
import com.example.helthTracking.user.AppUserRepo;
import com.example.helthTracking.user.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

   private AuthenticationManager authenticationManager;
   private JwtUtil jwtUtil;
   private PasswordEncoder passwordEncoder;
   private AppUserRepo userRepo;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return ResponseEntity.badRequest().body("passwords don't match");

        String pwd = passwordEncoder.encode(request.getPassword());
        AppUser user = new AppUser(request.getFirstName(), request.getLastName(), request.getUsername(), pwd, AppUserRole.PATIENT);
        userRepo.save(user);
        return ResponseEntity.ok().body("saved successfully");
    }

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody Credentials credentials){

       try{
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           credentials.getUsername(), credentials.getPassword()
                   )
           );

           AppUser user = (AppUser) authentication.getPrincipal();

           return ResponseEntity.ok().body(new TokenResponse(user, jwtUtil.generateToken(user)));
       }
       catch (BadCredentialsException exception){

           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
   }
}