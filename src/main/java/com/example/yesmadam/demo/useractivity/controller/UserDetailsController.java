package com.example.yesmadam.demo.useractivity.controller;

import com.example.yesmadam.demo.useractivity.dto.UserDetailsResponseDTO;
import com.example.yesmadam.demo.useractivity.exception.InvalidParameterException;
import com.example.yesmadam.demo.useractivity.model.UserAction;
import com.example.yesmadam.demo.useractivity.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/actions")
    public ResponseEntity<UserDetailsResponseDTO> getUserActions(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String userAction,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "desc") String sortingOrder,
            @RequestParam(defaultValue = "1") int pageNo) {

        // Validate userAction string (to prevent IllegalArgumentException)
        Optional<UserAction> actionEnum = parseUserAction(userAction);
        if (userAction != null && actionEnum.isEmpty()) {
            throw new InvalidParameterException("Invalid userAction provided. Allowed values: " + Arrays.toString(UserAction.values()));
        }

        UserDetailsResponseDTO response = userDetailsService.getUserActions(userId, actionEnum.orElse(null), limit, sortingOrder, pageNo);
        return ResponseEntity.ok(response);
    }

    private Optional<UserAction> parseUserAction(String action) {
        if (action == null) return Optional.empty();
        try {
            return Optional.of(UserAction.valueOf(action.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
