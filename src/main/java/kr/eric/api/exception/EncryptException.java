package kr.eric.api.exception;

public class EncryptException extends RuntimeException {
    public EncryptException() {
        super("민감 정보 암호화 중 에러가 발생했습니다.");
    }

    public EncryptException(String message) {
        super(message);
    }
}
