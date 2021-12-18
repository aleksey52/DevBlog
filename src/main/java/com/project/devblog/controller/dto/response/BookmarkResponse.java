package com.project.devblog.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class BookmarkResponse {
    @NonNull
    private final Integer userId;
    @NonNull
    private final Integer articleId;
    @NonNull
    private final String bookmarkType;
}
