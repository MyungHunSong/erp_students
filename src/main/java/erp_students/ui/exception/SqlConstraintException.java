package erp_students.ui.exception;

public class SqlConstraintException extends RuntimeException {

	public SqlConstraintException() {
		super("참조하는 레코드가 존재");
	}

	public SqlConstraintException(String message, Throwable cause) {
		super(message);
		
	}

	public SqlConstraintException(Throwable cause) {
		super("참조하는 레코드가 존재", cause);
		
	}

	
}
