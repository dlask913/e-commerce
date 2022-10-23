package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.User;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    Environment env;
    OrderServiceClient orderServiceClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           Environment env,
                           RestTemplate restTemplate,
                           OrderServiceClient orderServiceClient) {
        this.userRepository = userRepository;
        this.env=env;
//        this.restTemplate=restTemplate;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
//        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(user);

        UserDto returnUserDto = mapper.map(user, UserDto.class);

        return returnUserDto;
    }

//    @Override
//    public UserDto getUserByUserId(String userId) {
//        User user = userRepository.findByUserId(userId);
//
//        if (user == null)
//            throw new UsernameNotFoundException("User not found");
//
//        UserDto userDto = new ModelMapper().map(user, UserDto.class);
//
////        List<ResponseOrder> orders = new ArrayList<>();
//        /* Using as rest template */
////        String orderUrl = String.format(env.getProperty("order_service.url"),userId);
////        ResponseEntity<List<ResponseOrder>> orderListResponse =
////                restTemplate.exchange(orderUrl, HttpMethod.GET, null,
////                                        new ParameterizedTypeReference<List<ResponseOrder>>() {
////                });
////        List<ResponseOrder> ordersList = orderListResponse.getBody();
//
//        /* Using a Feign client */
//        /* Feign exception handling */
////        List<ResponseOrder> ordersList = null;
////        try {
////            ordersList = orderServiceClient.getOrders(userId);
////        } catch (FeignException ex) {
////            log.error(ex.getMessage());
////        }
//
//        /* ErrorDecoder */
//        log.info("Before called orders microservice");
//        List<ResponseOrder> ordersList =orderServiceClient.getOrders(userId);
//        userDto.setOrders(ordersList);
//        log.info("After called orders microservice");
//
//        return userDto;
//    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException(email);

        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        return userDto;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
