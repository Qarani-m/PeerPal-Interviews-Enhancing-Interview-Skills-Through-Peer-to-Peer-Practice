package PeerInterviews.auth.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SignupRequest {
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
}


