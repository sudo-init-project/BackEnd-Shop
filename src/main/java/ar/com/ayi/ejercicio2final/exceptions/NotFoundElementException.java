package ar.com.ayi.ejercicio2final.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundElementException extends NoSuchElementException {

    private final String code;

    public NotFoundElementException(String code, String s) {
        super(s);
        this.code = code;
    }
}
