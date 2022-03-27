package kr.eric.api.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("가입 되지 않은 회원 입니다.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
