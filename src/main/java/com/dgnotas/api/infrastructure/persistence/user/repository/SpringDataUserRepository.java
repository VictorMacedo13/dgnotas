package com.dgnotas.api.infrastructure.persistence.user.repository;

import com.dgnotas.api.infrastructure.persistence.user.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByEmail(String email);

}

