package com.example.yesmadam.demo.useractivity.dto;

import java.util.List;

public record UserDetailsResponseDTO(String name, String email, String number, List<UserActionDTO> details) {
}