package com.project.devblog.controller;

import com.project.devblog.controller.annotation.ApiV1;
import com.project.devblog.dto.request.TagRequest;
import com.project.devblog.dto.response.TagResponse;
import com.project.devblog.model.TagEntity;
import com.project.devblog.service.TagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Tags")
@ApiV1
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse create(@NonNull @Valid @RequestBody TagRequest request) {
        return toResponse(tagService.create(request.getName()));
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public Page<TagResponse> findAll(@RequestParam(name = "nameContains", required = false) String tagNameContains,
                                     @ParameterObject Pageable pageable) {
        return tagService.findAll(tagNameContains, pageable)
                .map(this::toResponse);
    }

    @GetMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponse find(@NonNull @PathVariable Integer tagId) {
        return toResponse(tagService.findById(tagId));
    }

    @DeleteMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@NonNull @PathVariable Integer tagId) {
        tagService.delete(tagId);
    }

    @PutMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public TagResponse update(@NonNull @PathVariable Integer tagId,
                              @NonNull @Valid @RequestBody TagRequest request) {
        return toResponse(tagService.update(tagId, request.getName()));
    }

    @NonNull
    public TagResponse toResponse(@NonNull TagEntity tagEntity) {
        return new TagResponse(tagEntity.getId(), tagEntity.getName());
    }
}

