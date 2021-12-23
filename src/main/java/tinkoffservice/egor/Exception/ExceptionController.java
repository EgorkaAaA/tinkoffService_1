package tinkoffservice.egor.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(value = StockNotFoundException.class)
    public ResponseEntity<ExceptionDto> stockNotFoundExceptionHandler(StockNotFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
