package net.nopainnocode.firstboard.support;

import net.nopainnocode.firstboard.support.error.UsernameDuplicatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by no_pain_no_code on 2015. 11. 3..
 */
@ControllerAdvice
@ResponseBody
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameDuplicatedException.class)
    public ExceptionResponse usernameDuplicatedException(UsernameDuplicatedException exception){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.errorMessage = "error!!!!!";

        return exceptionResponse;
    }
}
