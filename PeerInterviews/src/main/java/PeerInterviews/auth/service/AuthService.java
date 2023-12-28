package PeerInterviews.auth.service;

import PeerInterviews.auth.dto.SignupRequest;

public interface AuthService {
    Object signup(SignupRequest req);

    //    Login
    Object login(String username, String password);
}
