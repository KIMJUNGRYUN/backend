package com.mysite.backend.controller;
import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody 포함(화면 대신 바로 데이터 보냄)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user") //RequestBody 유저 객체를 바로 보내준다.
    User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
