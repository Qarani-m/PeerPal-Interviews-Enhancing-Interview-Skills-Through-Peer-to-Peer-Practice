package PeerInterviews.auth.service;

import PeerInterviews.auth.dto.SignupRequest;
import PeerInterviews.auth.entity.UserProfile;
import PeerInterviews.auth.exception.AuthenticationException;
import PeerInterviews.auth.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    SignUp
    @Override
    public ResponseEntity<Object> signup(SignupRequest req) {
        try {
            Optional<String> validationResult = validateSignupRequest(req);
            if (validationResult.isPresent()) {
                String errorMessage = validationResult.get();
                System.err.println("Validation failed: " + errorMessage);
                return ResponseEntity.badRequest().body("Validation Failed: " + errorMessage);
            }
            if (authRepo.existsByEmail(req.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already registered.");
            }
            if (authRepo.existsByUsername(req.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken.");
            }
            UserProfile userProfile = buildUserProfile(req);
            authRepo.save(userProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }

    private UserProfile buildUserProfile(SignupRequest req) {
        return UserProfile.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .username(req.getUsername())
                .phoneNumber(req.getPhoneNumber())
                .linkedInProfile(req.getLinkedInProfile())
                .currentJobTitle(req.getCurrentJobTitle())
                .companyName(req.getCompanyName())
                .industry(req.getIndustry())
                .yearsOfExperience(req.getYearsOfExperience())
                .highestEducationLevel(req.getHighestEducationLevel())
                .university(req.getUniversity())
                .major(req.getMajor())
                .skills(req.getSkills())
                .certifications(req.getCertifications())
                .preferredInterviewRoles(req.getPreferredInterviewRoles())
                .preferredInterviewTypes(req.getPreferredInterviewTypes())
                .timezone(req.getTimezone())
                .preferredInterviewTime(req.getPreferredInterviewTime())
                .profilePictureUrl(req.getProfilePictureUrl())
                .bio(req.getBio())
                .password(passwordEncoder.encode(req.getPassword())) // Use password encoder
                .build();
    }
    private Optional<String> validateSignupRequest(SignupRequest req) {
        if (StringUtils.isEmpty(req.getEmail()) ||
                StringUtils.isEmpty(req.getUsername()) ||
                StringUtils.isEmpty(req.getPassword())) {
            return Optional.of("Email, username, and password are required fields.");
        }
        if (!isValidEmail(req.getEmail())) {
            return Optional.of("Invalid email format.");
        }
        if (!isValidUsername(req.getUsername())) {
            return Optional.of("Invalid username format.");
        }
        return Optional.empty();
    }
    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9]{3,20}$";
        return username != null && username.matches(regex);
    }



//    Login
public ResponseEntity<Object> login(String username, String password) {
    try {
        UserProfile userByEmail = authRepo.findByEmail(username);
        if (userByEmail == null) {
            userByEmail = authRepo.findByUsername(username);
        }
        if (userByEmail != null && passwordEncoder.matches(password, userByEmail.getPassword())) {
            Object userDetails = getUserProfile(userByEmail);
            authorizeUser();
            return ResponseEntity.ok(userDetails);
        } else {
            throw new AuthenticationException("Invalid credentials");
        }
    } catch (AuthenticationException e) {
        String errorMessage = "Authentication failed, " + e.getMessage();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    } catch (Exception e) {
        String errorMessage = "Internal Server Error, Please try again later.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}

public void authorizeUser(){

}


    private Object getUserProfile(UserProfile user) {
        return  UserProfile.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .linkedInProfile(user.getLinkedInProfile())
                .currentJobTitle(user.getCurrentJobTitle())
                .companyName(user.getCompanyName())
                .industry(user.getIndustry())
                .yearsOfExperience(user.getYearsOfExperience())
                .highestEducationLevel(user.getHighestEducationLevel())
                .university(user.getUniversity())
                .major(user.getMajor())
                .skills(user.getSkills())
                .certifications(user.getCertifications())
                .preferredInterviewRoles(user.getPreferredInterviewRoles())
                .preferredInterviewTypes(user.getPreferredInterviewTypes())
                .timezone(user.getTimezone())
                .preferredInterviewTime(user.getPreferredInterviewTime())
                .profilePictureUrl(user.getProfilePictureUrl())
                .bio(user.getBio())
                .password(user.getPassword())
                .role(UserProfile.Role.USER)
                .build();

    }


}
