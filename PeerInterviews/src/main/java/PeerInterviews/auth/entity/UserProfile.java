package PeerInterviews.auth.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;



@Document(collection = "user_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserProfile {
    public enum Role {
        USER, ADMIN, PROF
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String fullName;
    private String email;
    private String username;
    private String phoneNumber;
    private String linkedInProfile;
    private String currentJobTitle;
    private String companyName;
    private String industry;
    private Integer yearsOfExperience;
    private String highestEducationLevel;
    private String university;
    private String major;
    private List<String> skills;
    private String certifications;
    private String preferredInterviewRoles;
    private String preferredInterviewTypes;
    private String timezone;
    private String preferredInterviewTime;
    private String profilePictureUrl;
    private String bio;
    private String password;
    private boolean isVerified=false;
    private Role role= Role.USER;
}
