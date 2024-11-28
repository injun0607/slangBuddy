package org.alham.slangbuddy.repository.user;


import org.alham.slangbuddy.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    public UserDocument findUserByUserId(String userId);

}
