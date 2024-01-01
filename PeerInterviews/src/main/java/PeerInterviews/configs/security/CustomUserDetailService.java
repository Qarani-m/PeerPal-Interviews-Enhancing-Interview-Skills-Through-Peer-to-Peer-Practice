package PeerInterviews.configs.security;

import PeerInterviews.auth.entity.UserProfile;
import PeerInterviews.auth.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AuthRepo authRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = authRepo.findByUsername(username);
        return  new User(userProfile.getUsername(),userProfile.getPassword(), Collections.singleton(userProfile.getRole()));

    }

}