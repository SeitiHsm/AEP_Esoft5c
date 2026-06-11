package com.AEP2.demo.DTO;

import com.AEP2.demo.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
