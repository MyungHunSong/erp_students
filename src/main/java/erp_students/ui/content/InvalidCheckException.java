package erp_students.ui.content;

@SuppressWarnings("serial")
public class InvalidCheckException extends RuntimeException { // 전파가 계속 위로 올라감

	public InvalidCheckException() {
		super("공백이 존재합니다.");
	}
	
	

	public InvalidCheckException(Throwable cause) {
		super("공백이 존재합니다.", cause);
		
	}
	
	
}
