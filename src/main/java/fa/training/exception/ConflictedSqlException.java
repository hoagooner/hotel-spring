package fa.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictedSqlException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConflictedSqlException() {
        super();
    }
    public ConflictedSqlException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConflictedSqlException(String message) {
        super(message);
    }
    public ConflictedSqlException(Throwable cause) {
        super(cause);
    }
}
