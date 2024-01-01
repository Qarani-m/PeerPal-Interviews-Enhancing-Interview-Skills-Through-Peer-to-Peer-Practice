package PeerInterviews.auth.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Document(collection = "user_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserProfile {
    public enum Role implements GrantedAuthority {
        USER, ADMIN, PROF;

        @Override
        public String getAuthority() {
            return name();
        }


    }
//    public enum Role implements Collection<GrantedAuthority> {
//        USER, ADMIN, PROF;
//
//        @Override
//        public int size() {
//            return 0;
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return false;
//        }
//
//        @Override
//        public boolean contains(Object o) {
//            return false;
//        }
//
//        @Override
//        public Iterator<GrantedAuthority> iterator() {
//            return null;
//        }
//
//        @Override
//        public Object[] toArray() {
//            return new Object[0];
//        }
//
//        @Override
//        public <T> T[] toArray(T[] a) {
//            return null;
//        }
//
//        @Override
//        public boolean add(GrantedAuthority grantedAuthority) {
//            return false;
//        }
//
//        @Override
//        public boolean remove(Object o) {
//            return false;
//        }
//
//        @Override
//        public boolean containsAll(Collection<?> c) {
//            return false;
//        }
//
//        @Override
//        public boolean addAll(Collection<? extends GrantedAuthority> c) {
//            return false;
//        }
//
//        @Override
//        public boolean removeAll(Collection<?> c) {
//            return false;
//        }
//
//        @Override
//        public boolean retainAll(Collection<?> c) {
//            return false;
//        }
//
//        @Override
//        public void clear() {
//
//        }
//    }

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
