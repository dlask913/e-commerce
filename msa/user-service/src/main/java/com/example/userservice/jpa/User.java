package com.example.userservice.jpa;

import com.example.userservice.dto.UserDto;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String userId;
    @Column(nullable = false,unique = true)
    private String pwd;

    public static User createUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPwd(user.getPwd());
        return user;
    }
}
