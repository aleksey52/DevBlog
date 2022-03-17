package com.project.devblog.controller;

import com.project.devblog.exception.VerificationException;
import com.project.devblog.model.PersonalInfo;
import com.project.devblog.model.UserEntity;
import com.project.devblog.model.enums.Role;
import com.project.devblog.security.JwtTokenProvider;
import com.project.devblog.service.UserService;
import com.project.devblog.service.VerificationService;
import com.project.devblog.testcontainers.PostgresTestContainer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.project.devblog.security.JwtTokenProvider.TOKEN_PREFIX;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VerificationControllerTest extends PostgresTestContainer {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    VerificationService verificationService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @MockBean
    UserService userService;

    @Test
    void confirmVerificationCodeTest() throws Exception {
        final PersonalInfo personalInfo = new PersonalInfo("firstname", "lastname",
                "nickname", "photo", "user info", "891245646544");
        final UserEntity user = new UserEntity(UUID.randomUUID().toString(), "user@mail.ru", "encryptedPassword",
                Role.USER, true, null, personalInfo,
                List.of(), List.of(), List.of(), List.of(), Set.of(), Set.of());

        final String token = TOKEN_PREFIX + jwtTokenProvider.createToken(user.getLogin(), Role.USER);
        final String verificationCode = UUID.randomUUID().toString();

        when(userService.findByLogin(user.getLogin())).thenReturn(user);
        doNothing().when(verificationService).verify(user.getId(), verificationCode);

        mockMvc
                .perform(get("/v1/users/{userId}/verify", user.getId())
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .queryParam("code", verificationCode))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void confirmNotValidVerificationCodeTest() throws Exception {
        final PersonalInfo personalInfo = new PersonalInfo("firstname", "lastname",
                "nickname", "photo", "user info", "891245646544");
        final UserEntity user = new UserEntity(UUID.randomUUID().toString(), "user@mail.ru", "encryptedPassword",
                Role.USER, true, null, personalInfo,
                List.of(), List.of(), List.of(), List.of(), Set.of(), Set.of());

        final String token = TOKEN_PREFIX + jwtTokenProvider.createToken(user.getLogin(), Role.USER);
        final String verificationCode = UUID.randomUUID().toString();

        when(userService.findByLogin(user.getLogin())).thenReturn(user);
        doThrow(VerificationException.class).when(verificationService).verify(user.getId(), verificationCode);

        mockMvc
                .perform(get("/v1/users/{userId}/verify", user.getId())
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .queryParam("code", verificationCode))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void confirmAlreadyVerifiedCodeTest() throws Exception {
        final PersonalInfo personalInfo = new PersonalInfo("firstname", "lastname",
                "nickname", "photo", "user info", "891245646544");
        final UserEntity user = new UserEntity(UUID.randomUUID().toString(), "user@mail.ru", "encryptedPassword",
                Role.USER, true, null, personalInfo,
                List.of(), List.of(), List.of(), List.of(), Set.of(), Set.of());

        final String token = TOKEN_PREFIX + jwtTokenProvider.createToken(user.getLogin(), Role.USER);
        final String verificationCode = UUID.randomUUID().toString();

        when(userService.findByLogin(user.getLogin())).thenReturn(user);
        doThrow(VerificationException.class).when(verificationService).verify(user.getId(), verificationCode);

        mockMvc
                .perform(get("/v1/users/{userId}/verify", user.getId())
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .queryParam("code", verificationCode))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
