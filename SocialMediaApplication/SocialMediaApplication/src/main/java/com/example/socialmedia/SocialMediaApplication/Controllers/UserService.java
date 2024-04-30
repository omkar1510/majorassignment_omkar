package com.example.socialmedia.SocialMediaApplication.Controllers;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isPasswordCorrect(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}
