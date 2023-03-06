package com.peaksoft.service;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.UserResponse;
import com.peaksoft.entity.Role;
import com.peaksoft.entity.User;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User mapToEntity(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setCreatedDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        return user;
    }

    public UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstname(user.getUsername());
        userResponse.setLastname(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedDate(user.getCreatedDate());
        userResponse.setRoleName(user.getRole().name());
        return userResponse;
    }

    public UserResponse create(UserRequest request) {
        User user = mapToEntity(request);
        user.setCreatedDate(LocalDate.now());
        userRepository.save(user);
        return mapToResponse(user);
    }

    public List<UserResponse> getAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userResponses.add(mapToResponse(user));
        }
        return userResponses;
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id).get();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setCreatedDate(LocalDate.now());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).get();
        return mapToResponse(user);
    }

    public void delete(Long id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
    }
}
