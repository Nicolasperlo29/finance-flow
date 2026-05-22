package org.example.walletservice.client;

import org.example.walletservice.dto.Response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private String url = "http://localhost:8081/auth";

    public UserResponse getUser(Long userId) {

        String endpoint = url + "/user?userId=" + userId;

        ResponseEntity<UserResponse> response =
                restTemplate.getForEntity(
                        endpoint,
                        UserResponse.class
                );

        return response.getBody();
    }
}
