package ar.com.ayi.ejercicio2final.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomErrorResponse {

    private String code;
    private String message;
}
