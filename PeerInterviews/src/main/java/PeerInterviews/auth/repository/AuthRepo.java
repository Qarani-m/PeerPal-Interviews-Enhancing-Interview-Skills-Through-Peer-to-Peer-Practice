package PeerInterviews.auth.repository;

import PeerInterviews.auth.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepo extends MongoRepository<UserProfile, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String email);

    UserProfile findByUsername(String userName);
    UserProfile findByEmail(String userName);
}
