package com.kh.learnovation.domain.user.handler;

import com.kh.learnovation.domain.user.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
 	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseDto<String> handleArgumentException(Exception e) {
		log.error("서버 에러 발생", e);
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
