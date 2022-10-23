package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final Environment env;
    private final UserService userService;

    @PostMapping("/users/new")
    public String userForm(UserDto userDto){
        User user = User.createUser(userDto);
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/users/new")
    public String userForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "user/userForm";
    }

//    @PostMapping("/users")
//    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        UserDto userDto = mapper.map(user, UserDto.class);
//        userService.createUser(userDto);
//
//        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
//
////        return new ResponseEntity(HttpStatus.CREATED); // 상태코드값 전달
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<ResponseUser>> getUsers() {
//        Iterable<UserEntity> userList = userService.getUserByAll();
//
//        List<ResponseUser> result = new ArrayList<>();
//        userList.forEach(v -> {
//            result.add(new ModelMapper().map(v, ResponseUser.class));
//        });
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @GetMapping("/users/{userId}")
//    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
//        UserDto userDto = userService.getUserByUserId(userId);
//        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);
//        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
//    }


}
