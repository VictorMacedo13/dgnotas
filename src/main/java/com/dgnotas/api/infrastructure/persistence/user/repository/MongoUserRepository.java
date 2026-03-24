package com.dgnotas.api.infrastructure.persistence.user.repository;

import com.dgnotas.api.domain.model.User;
import com.dgnotas.api.domain.repository.UserRepository;
import com.dgnotas.api.infrastructure.persistence.user.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    public MongoUserRepository(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(
                repository.save(UserMapper.toDocument(user))
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(UserMapper::toDomain);
    }
}
