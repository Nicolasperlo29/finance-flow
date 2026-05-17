package org.example.authservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authservice.dto.Role;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent implements Serializable {

}
