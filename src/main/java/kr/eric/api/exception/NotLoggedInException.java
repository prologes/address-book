package kr.eric.api.exception;

public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException() {
        super("로그인 후 이용 가능합니다.");
    }

    public NotLoggedInException(String message) {
        super(message);
    }
}
