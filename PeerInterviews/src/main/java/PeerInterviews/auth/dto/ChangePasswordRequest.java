package PeerInterviews.auth.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;

}
