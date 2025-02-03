package com.mysite.backend.controller;
import com.mysite.backend.exception.UserNotFoundException;
import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ResponseBody 포함(화면 대신 바로 데이터 보냄)
@RestController   //뷰 없이 바로 데이터
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users") //RequestBody 유저 객체를(JSON) 바로 보내준다.
    User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        //DB 에서 user 를 찾음(없으면 예외처리)
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
            //찾은 유저에 새유저 내용으로 업데이트
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user); //업데이트 된 유저를 저장
    }

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id) {
        //아이디가 없을 경우 => 예외처리 
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        //아이디가 있을때
        userRepository.deleteById(id);
        return "유저 아이디: " + id + "는 삭제 되었습니다.";
    }

}
