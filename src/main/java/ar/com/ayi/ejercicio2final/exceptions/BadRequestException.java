package ar.com.ayi.ejercicio2final.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends IllegalArgumentException{

    private final String code;
    public BadRequestException(String code, String message){
        super(message);
        this.code = code;
    }
}