package PeerInterviews.auth.controller;

import PeerInterviews.auth.dto.ChangePasswordRequest;
import PeerInterviews.auth.dto.LoginRequest;
import PeerInterviews.auth.dto.SignupRequest;
import PeerInterviews.auth.dto.UpdateProfileRequest;
import PeerInterviews.auth.entity.UserProfile;
import PeerInterviews.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import PeerInterviews.auth.exception.ConflictException;
import PeerInterviews.auth.exception.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest req) {
        try {
            Object user = authService.login(req.getUsername(), req.getPassword());
            return ResponseEntity.ok(user);
        } catch (AuthenticationException e) {
            String errorMessage = "Authentication failed, " + e.getMessage();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "Internal Server Error, Please try again later.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest req) {
        try {
            Object result = authService.signup(req);
            return ResponseEntity.ok(result);
        } catch (ConflictException e) {
            String errorMessage = "Conflict, " + e.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "Internal Server Error, Please try again later.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }



    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest req) {
        return ResponseEntity.ok("Password changed successfully");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/update-profile")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileRequest req) {
        return ResponseEntity.ok("Profile updated successfully");
    }
}
