package ar.com.ayi.ejercicio2final.exceptions;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<CustomErrorResponse> emitirBadRequestException(BadRequestException e){
        logger.error("Error de BadRequestException --> " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler({NotFoundElementException.class})
    public ResponseEntity<CustomErrorResponse> emitirNoSuchElementException(NotFoundElementException e){
        logger.error("Aviso de NoSuchElementException --> " + e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(e.getCode(), e.getMessage()));
    }

}
