package com.project.devblog.controller;

import com.project.devblog.controller.annotation.ApiV1;
import com.project.devblog.controller.dto.request.RatingRequest;
import com.project.devblog.controller.dto.response.RatingResponse;
import com.project.devblog.model.UserArticleEntity;
import com.project.devblog.service.RatingService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ApiV1
@RestController
@AllArgsConstructor
public class RatingController {

    @NonNull
    private final RatingService ratingService;

    @PostMapping("/users/{userId}/articles/{articleId}/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public RatingResponse create(@NonNull @PathVariable Integer userId, @NonNull @PathVariable Integer articleId,
                                 @NonNull @Valid RatingRequest request) {
        return toResponse(ratingService.create(userId, articleId, request.getRating()));
    }

    @GetMapping("/users/{userId}/articles/{articleId}/ratings")
    @ResponseStatus(HttpStatus.OK)
    public RatingResponse get(@NonNull @PathVariable Integer userId, @NonNull @PathVariable Integer articleId) {
        return toResponse(ratingService.get(userId, articleId));
    }

    @PutMapping("/users/{userId}/articles/{articleId}/ratings")
    @ResponseStatus(HttpStatus.OK)
    public RatingResponse update(@NonNull @PathVariable Integer userId, @NonNull @PathVariable Integer articleId,
                                 @NonNull @Valid RatingRequest request) {
        return toResponse(ratingService.update(userId, articleId, request.getRating()));
    }

    @NonNull
    private RatingResponse toResponse(@NonNull UserArticleEntity userArticleEntity) {
        return new RatingResponse(userArticleEntity.getUser().getId(), userArticleEntity.getArticle().getId(), userArticleEntity.getRating());
    }
}
