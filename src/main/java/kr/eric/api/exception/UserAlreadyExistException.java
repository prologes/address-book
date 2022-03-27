package kr.eric.api.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("이미 가입된 회원입니다.");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
