package todoList.backend.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import todoList.backend.common.exception.BadRequestException;
import todoList.backend.common.exception.NotFoundException;
import todoList.backend.data.dto.ResponseDto;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseDto> handleNotFoundException(NotFoundException e) {
        ResponseDto responseDto = new ResponseDto(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ResponseDto> handleBadRequestException(BadRequestException e) {
        ResponseDto responseDto = new ResponseDto(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
