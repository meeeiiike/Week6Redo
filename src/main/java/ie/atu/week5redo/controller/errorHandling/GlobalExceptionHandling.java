package ie.atu.week5redo.controller.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae){
        List<ExceptionDetails> errorList = new ArrayList<>();
        for(FieldError fieldError : mae.getBindingResult().getFieldErrors()){
            errorList.add(new ExceptionDetails(fieldError.getField(), fieldError.getDefaultMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(DuplicateExceptionHandling.class)
    public ResponseEntity<ExceptionDetails> dupedIdEx(DuplicateExceptionHandling deh){
        ExceptionDetails exceptionDetails = new ExceptionDetails("Passenger ID ", deh.getMessage(), LocalDateTime.now(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);
    }

    @ExceptionHandler(NotFoundHandling.class)
    public ResponseEntity<ExceptionDetails> notFoundEx(NotFoundHandling nfh){
        ExceptionDetails exceptionDetails = new ExceptionDetails("Passenger ID ", nfh.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);
    }

}