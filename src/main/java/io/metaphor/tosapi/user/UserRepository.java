package io.metaphor.tosapi.user;

import io.metaphor.tosapi.error.EntityNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(final String username);
    User findByEmail(final String email);
}

