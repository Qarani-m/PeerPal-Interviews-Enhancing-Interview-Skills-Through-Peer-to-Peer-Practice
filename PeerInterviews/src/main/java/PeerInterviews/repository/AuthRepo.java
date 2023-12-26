package PeerInterviews.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import PeerInterviews.entity.UserProfile;

public interface AuthRepo extends MongoRepository<UserProfile, Long> {

}
