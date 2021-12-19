package com.project.devblog.controller.exceptionhandler;

import com.project.devblog.controller.exceptionhandler.dto.ErrorBody;
import com.project.devblog.model.enums.ErrorsEnum;
import com.project.devblog.service.exception.ArticleNotFoundException;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArticleNotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleException(@NonNull ArticleNotFoundException exception) {
        return new ResponseEntity<>(new ErrorBody(ErrorsEnum.NOT_FOUND, exception.getMessage()), ErrorsEnum.NOT_FOUND.getStatus());
    }
}
