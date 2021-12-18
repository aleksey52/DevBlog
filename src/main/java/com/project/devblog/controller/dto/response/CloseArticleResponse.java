package com.project.devblog.controller.dto.response;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CloseArticleResponse {
    @NonNull
    private final Integer id;
    @NonNull
    private final String title;
    @NonNull
    private final String status;
    @NonNull
    private final String description;
    @Nullable
    private final LocalDateTime publicationDate;
    @NonNull
    private final LocalDateTime modificationDate;
    @NonNull
    private final Integer authorId;
    @NonNull
    private final String authorNickname;
    @Nullable
    private final String photo;
    @Nullable
    private final List<TagResponse> tags;
}
