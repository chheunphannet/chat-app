package com.chatapi.chat_app.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException{

    private HttpStatus code;

    private String message;
}
