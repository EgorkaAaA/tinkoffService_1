package tinkoffservice.egor.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
public class ExceptionDto {
    String message;
}
