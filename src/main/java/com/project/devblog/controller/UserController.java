package com.project.devblog.controller;

import com.project.devblog.controller.annotation.ApiV1;
import com.project.devblog.controller.dto.request.UserRequest;
import com.project.devblog.controller.dto.response.UserResponse;
import com.project.devblog.model.UserEntity;
import com.project.devblog.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@ApiV1
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse find(@NonNull @PathVariable String userId) {
        return toResponse(userService.find(userId));
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> findAll(@NonNull Pageable pageable) {
        return userService.findAll(pageable)
                .map(this::toResponse);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@NonNull @PathVariable String userId) {
        userService.delete(userId);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@NonNull @PathVariable String userId,
                               @RequestBody @NonNull UserRequest userRequest) {
        return toResponse(userService.update(userId, userRequest));
    }

    @NonNull
    private UserResponse toResponse(@NonNull UserEntity user) {
        return new UserResponse(user.getId(), user.getLogin(), user.getPersonalInfo());
    }
}
