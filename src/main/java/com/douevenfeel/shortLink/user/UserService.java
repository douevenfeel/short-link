package com.douevenfeel.shortLink.user;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final Map<UUID, User> userRepository;

    public UserService(Map<UUID, User> userRepository) {
        this.userRepository = userRepository;
    }

    public User create() {
        User user = new User();
        userRepository.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userRepository.get(id));
    }
}
