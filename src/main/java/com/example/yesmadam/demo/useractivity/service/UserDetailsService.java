package com.example.yesmadam.demo.useractivity.service;

import com.example.yesmadam.demo.useractivity.dto.UserActionDTO;
import com.example.yesmadam.demo.useractivity.dto.UserDetailsResponseDTO;
import com.example.yesmadam.demo.useractivity.exception.InvalidParameterException;
import com.example.yesmadam.demo.useractivity.exception.UserNotFoundException;
import com.example.yesmadam.demo.useractivity.model.User;
import com.example.yesmadam.demo.useractivity.model.UserAction;
import com.example.yesmadam.demo.useractivity.model.UserDetails;
import com.example.yesmadam.demo.useractivity.repo.UserDetailsRepository;
import com.example.yesmadam.demo.useractivity.repo.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDetailsService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsService(UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetailsResponseDTO getUserActions(Long userId, UserAction userAction, int limit, String sortingOrder, int pageNo) {
        if (limit <= 0 || pageNo <= 0) {
            throw new InvalidParameterException("Limit and page number must be greater than 0");
        }

        Sort sort = sortingOrder.equalsIgnoreCase("asc") ? Sort.by("logInTime").ascending() : Sort.by("logInTime").descending();
        PageRequest pageRequest = PageRequest.of(pageNo - 1, limit, sort);

        List<UserDetails> userDetails;

        if (userId != null && userAction != null) {
            userDetails = userDetailsRepository.findByUserUserIdAndUserAction(userId, userAction, pageRequest);
        } else if (userId != null) {
            userDetails = userDetailsRepository.findByUserUserId(userId, pageRequest);
        } else if (userAction != null) {
            userDetails = userDetailsRepository.findByUserAction(userAction, pageRequest);
        } else {
            userDetails = userDetailsRepository.findAll(pageRequest).getContent();
        }

        if (userDetails.isEmpty()) {
            throw new UserNotFoundException("No user actions found for the given filters.");
        }

        String name = "All Users";
        String email = "N/A";
        String number = "N/A";

        if (userId != null) {
            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
            name = user.getUserName();
            email = user.getEmail();
            number = user.getNumber();
        }

        List<UserActionDTO> details = userDetails.stream()
                .map(detail -> new UserActionDTO(detail.getUserDevice(), detail.getLogInTime(), detail.getUserAction()))
                .collect(Collectors.toList());

        return new UserDetailsResponseDTO(name, email, number, details);
    }
}
