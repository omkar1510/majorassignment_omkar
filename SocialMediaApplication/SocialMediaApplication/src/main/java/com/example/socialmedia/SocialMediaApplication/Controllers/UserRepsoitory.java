package com.example.socialmedia.SocialMediaApplication.Controllers;


public class UserRepsoitory extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
