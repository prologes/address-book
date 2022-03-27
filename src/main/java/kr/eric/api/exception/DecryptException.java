package kr.eric.api.exception;

public class DecryptException extends RuntimeException {
    public DecryptException() {
        super("민감 정보 복호화 중 에러가 발생했습니다.");
    }

    public DecryptException(String message) {
        super(message);
    }
}
