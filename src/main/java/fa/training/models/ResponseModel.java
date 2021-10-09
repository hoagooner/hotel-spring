package fa.training.models;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	
	 HttpStatus status;
	 Object data;
	 String message;

	public ResponseModel(HttpStatus status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public ResponseModel(HttpStatus ok, Object data) {
		super();
		this.status = ok;
		this.data = data;
	}

	public ResponseModel(HttpStatus status) {
		super();
		this.status = status;
	}

	public ResponseModel(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	

}
