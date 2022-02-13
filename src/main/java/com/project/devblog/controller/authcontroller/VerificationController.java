package com.project.devblog.controller.authcontroller;

import com.project.devblog.controller.annotation.ApiV1;
import com.project.devblog.service.VerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Tag(name = "Verification")
@ApiV1
@RestController
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/users/{userId}/verify")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView verifyUser(@NonNull @PathVariable String userId,
                                   @NonNull @RequestParam("code") String verificationCode) {
        verificationService.verify(userId, verificationCode);
        return new ModelAndView("verification_page");
    }
}