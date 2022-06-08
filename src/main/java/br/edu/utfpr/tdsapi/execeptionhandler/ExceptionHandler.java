package br.edu.utfpr.tdsapi.execeptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMessage = messageSource.getMessage("atributo.invalido", null, LocaleContextHolder.getLocale());
		
		return handleExceptionInternal(ex, new Error(userMessage, ex.getCause().toString()), headers, status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, status, request);
	}
	
	/**
	 * exception when params are not found
	 * 
	 * Ex: when you try to delete some data with ID that does not exist in your DB
	 * 
	 * @param EmptyResultDataAccessException ex
	 * @param WebRequest request
	 * @return handleExceptionInternal
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAcessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String userMessage = messageSource.getMessage("recurso.nao.encontrado", null, LocaleContextHolder.getLocale());
		
		return handleExceptionInternal(ex, new Error(userMessage, ex.toString()),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	/**
	 * exception when you try create a objet in your DB, but the FOREIGN KEY does not exist
	 * 
	 * @param DataIntegrityViolationException ex
	 * @param WebRequest request
	 * @return handleExceptionInternal
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		String userMessage = messageSource.getMessage("operacao.invalida", null, LocaleContextHolder.getLocale());
		
		return handleExceptionInternal(ex, new Error(userMessage, ExceptionUtils.getRootCauseMessage(ex)),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	/**
	 * Method to create a list of errors 
	 * 
	 * @param BindingResult bindResult
	 * @return Error errors
	 */
	private List<Error> createErrorList(BindingResult bindResult) {
		List<Error> errors = new ArrayList<>();
		
		bindResult.getFieldErrors().forEach(fieldlError -> {
			String userMessage = messageSource.getMessage(fieldlError, LocaleContextHolder.getLocale());
			String devMessage = fieldlError.toString();
			errors.add(new Error(userMessage, devMessage));
		});
		
		return errors;
	}
	
	
	public static class Error {
		private String userMessage;
		private String devMessage;
		
		public Error(String userMessage, String devMessage) {
			this.userMessage = userMessage;
			this.devMessage = devMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDevMessage() {
			return devMessage;
		}
	}
	
}