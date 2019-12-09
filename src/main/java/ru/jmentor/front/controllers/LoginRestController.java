package ru.jmentor.front.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.jmentor.front.dto.AuthenticationRequestDto;
import ru.jmentor.front.dto.UserDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    private String loginUrl = "http://localhost/rest/login";

    @PostMapping
    public ResponseEntity<?> authConnect(
            @RequestBody AuthenticationRequestDto requestDto,
            HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AuthenticationRequestDto> httpEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(loginUrl, HttpMethod.POST, httpEntity, UserDto.class);

        UserDto userDto = responseEntity.getBody();

        HttpSession session = request.getSession();
        session.setAttribute("user", userDto);

        Map<Object, Object> response = new HashMap<>();
        response.put("username", userDto.getUsername());
        response.put("token", userDto.getToken());
        response.put("userRole", userDto.getUserRole());
        response.put("firstName", userDto.getFirstName());
        response.put("lastName", userDto.getLastName());

        return ResponseEntity.ok(response);
    }
}
