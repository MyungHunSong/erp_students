package erp_students.ui.exception;

@SuppressWarnings("serial")
public class NotSelectedException extends RuntimeException {

	
	public NotSelectedException() {
		super("목록이 필요하다");
		
	}
	
	public NotSelectedException(Throwable cause) {
		super("목록을 넣어 주세요" + cause);
		
	}
}
