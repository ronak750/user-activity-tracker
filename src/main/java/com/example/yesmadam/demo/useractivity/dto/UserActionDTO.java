package com.example.yesmadam.demo.useractivity.dto;

import com.example.yesmadam.demo.useractivity.model.UserAction;
import java.time.LocalDateTime;

public record UserActionDTO(String userDevice, LocalDateTime logInTime, UserAction userAction) {
}
