package kr.eric.api.util;

import kr.eric.api.exception.InvalidParameterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 전화번호가 02로 시작하는 지 여부를 체크한다.
     * @param tel
     */
    public static void seoulTemNumCheck(String tel) {
        if (!tel.substring(0, 2).equals("02")) {
            throw new InvalidParameterException("서울지역 전화번호에 해당하는 연락처만 삭제 가능합니다.");
        }
    }

    /**
     * 입력받은 주소록 이름이 한글인 지 여부를 체크한다.
     *
     * @param name
     * @return Boolean
     */
    public static void nameValidationCheck(String name) {
        for (int i=0; i<name.length(); i++) {
            if (Character.getType(name.charAt(i)) != 5) {
                throw new InvalidParameterException("한글 이름만 가능합니다.");
            }
        }
    }

    /**
     * 입력받은 주소록 나이가 20세 이하인 지 여부를 체크한다.
     *
     * @param age
     * @return Boolean
     */
    public static void ageValidationCheck(int age) {
        if (age > 20) {
            throw new InvalidParameterException("20세 이하만 가능합니다.");
        }
    }

    /**
     * 입력받은 전화번호의 format을 체크한다.
     * @param tel
     */
    public static void telValidationCheck(String tel) {
        Pattern telPattern = Pattern.compile("\\d{2,3}-\\d{3,4}-\\d{3,4}");
        Matcher telMatcher = telPattern.matcher(tel);
        if (!telMatcher.matches()) {
            throw new InvalidParameterException("전화번호를 올바르게 입력해 주세요. (예 : 010-1111-2345)");
        }
    }

    /**
     * 입력받은 주민등록번호 format을 체크한다.
     * @param regNo
     */
    public static void regNoValidationCheck(String regNo) {
        Pattern regNoPattern = Pattern.compile("\\d{6}-\\d{7}");
        Matcher regNoMatcher = regNoPattern.matcher(regNo);
        if (!regNoMatcher.matches()) {
            throw new InvalidParameterException("주민등록번호를 올바르게 입력해 주세요. (예 : 123456-1234567)");
        }
    }
}
