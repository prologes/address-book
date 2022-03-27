package kr.eric.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AccountDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class SignUpRequest {
        private String userId;

        private String password;

        private String name;

        private String regNo;

        private String tel;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class LoginRequest {
        private String userId;

        private String password;
    }
}
