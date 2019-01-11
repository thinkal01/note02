package com.note.common.freemarker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ResponseStatusException : 自定义异常原因")
public class ResponseStatusException extends RuntimeException{

}
