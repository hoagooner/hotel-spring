package fa.training.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnwantedException(Exception e) {
        return ResponseEntity.status(500).body("Unknow error");
    }
	
	@ExceptionHandler(ConflictedSqlException.class)
    public ResponseEntity<String> handelConflict(ConflictedSqlException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	@ResponseStatus(value = HttpStatus.CONFLICT) // 409
	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		if (ex.getCause() instanceof ConstraintViolationException) {
			errors.put("error", "Conflicted database");
		} else {
			errors.put("error", ex.getRootCause().getMessage());
		}
		return errors;
	}

}
