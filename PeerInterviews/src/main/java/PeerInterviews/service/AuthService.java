package PeerInterviews.service;

import PeerInterviews.dto.SignupRequest;

public interface AuthService {
    Object signup(SignupRequest req);
}
