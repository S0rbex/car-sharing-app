package vitalitus.carsharingapp.dto.user;

import vitalitus.carsharingapp.model.User;

public record UserResponseDto(
        Long id, String email, String firstName, String lastName, User.Role role) {
}
