package com.rockstar.product.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.rockstar.product.service.NotFoundException;
import com.rockstar.product.service.NotUniqueException;

@ControllerAdvice
public class ErrorHandler {

	@Inject private MessageSource messageSource;
	
	public ErrorHandler() {
	}
	
	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResource handleNotFound(NotFoundException notfoundex) {
		ErrorResource error = new ErrorResource();
		error.setError("not_found");
		error.setDescription(this.getText(notfoundex.getMessage()));
		return error;
	}
	
	@ExceptionHandler({NotUniqueException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ErrorResource handleNotUnique(NotUniqueException notuniqueex) {
		ErrorResource error = new ErrorResource();
		error.setError("duplicate");
		error.setDescription(this.getText(notuniqueex.getMessage()));
		return error;
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResource handleNotValid(MethodArgumentNotValidException notvalidex) {
		String fieldErrorCode = null;
		List<String> errorMessages = null;
		BindingResult validationResult = notvalidex.getBindingResult();
		List<FieldError> fieldErrors = validationResult.getFieldErrors();
		ErrorResource error = new ErrorResource();
		
		error.setError("validation_error");
		error.setDescription(this.getText("ValidationError"));
		
		if (fieldErrors != null && !fieldErrors.isEmpty()) {
			errorMessages = new ArrayList<String>();
			
			for (FieldError currentFieldError : fieldErrors) {
				fieldErrorCode = String.format("%s.%s.%s", currentFieldError.getCode(), currentFieldError.getObjectName(), currentFieldError.getField());
				errorMessages.add(this.getText(fieldErrorCode, currentFieldError.getArguments()));
			}
			error.setMessages(errorMessages);
		}
		return error;
	}
	
	@ExceptionHandler({RuntimeException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResource handleInternalServer(Exception exception) {
		ErrorResource error = new ErrorResource();
		error.setError("internal_error");
		error.setDescription(this.getText("InternalError", exception.getMessage()));
		exception.printStackTrace();
		return error;
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public @ResponseBody ErrorResource handleMessageNotReadableException(HttpMessageNotReadableException messagenotreadableex) {
		ErrorResource error = null;
		String unrecognizedPropertyName = null;
		Throwable mostSpecificCause = messagenotreadableex.getMostSpecificCause();
		UnrecognizedPropertyException unrecognizedPropertyException = null;
		
		if (mostSpecificCause != null) {
			if (mostSpecificCause instanceof UnrecognizedPropertyException) {
				unrecognizedPropertyException = (UnrecognizedPropertyException) mostSpecificCause;
				unrecognizedPropertyName = unrecognizedPropertyException.getPropertyName();
				error = new ErrorResource();
				error.setError("unrecognized_property");
				error.setDescription(this.getText("UnrecognizedProperty", unrecognizedPropertyName));
			} else {
				error = new ErrorResource();
				error.setError("unreadable_message");
				error.setDescription(messagenotreadableex.getMessage());
			}
		}
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResource handleMissingRequestParameterException(MissingServletRequestParameterException missingparameterex) {
		ErrorResource error = new ErrorResource();
		error.setError("missing_parameter");
		error.setDescription(this.getText("MissingParameter", missingparameterex.getParameterName()));
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResource handleRequestBindingException(ServletRequestBindingException requestbindingex, HttpServletResponse response) throws IOException {
		ErrorResource error = new ErrorResource();
		error.setError("missing_parameter");
		error.setDescription(this.getText("MissingParameter", requestbindingex.getMessage()));
		return error;
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResource  handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException requestmethodnotsupportedex, HttpServletResponse response) throws IOException {
		ErrorResource error = new ErrorResource();
		error.setError("not_supported");
		error.setDescription(requestmethodnotsupportedex.getMessage());
		return error;
	}
	
	private String getText(String error, Object...arguments) {
		return this.messageSource.getMessage(error, arguments, LocaleContextHolder.getLocale());
	}
}
