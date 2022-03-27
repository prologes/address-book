package kr.eric.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AddressBookDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class AddressBookRequest {
        private String name;

        private int age;

        private String tel;
    }
}
