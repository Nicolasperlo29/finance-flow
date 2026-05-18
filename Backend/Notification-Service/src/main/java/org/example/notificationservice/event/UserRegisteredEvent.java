package org.example.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserRegisteredEvent(
        String name,
        String email
) {
}