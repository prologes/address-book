package kr.eric.api.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("해당하는 정보가 없습니다.");
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
