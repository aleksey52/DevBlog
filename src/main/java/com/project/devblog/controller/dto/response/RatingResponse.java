package com.project.devblog.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class RatingResponse {
    @NonNull
    private final Integer authorId;
    @NonNull
    private final Integer articleId;
    @NonNull
    private final Integer rating;
}
