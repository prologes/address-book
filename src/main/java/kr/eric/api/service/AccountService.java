package kr.eric.api.service;

import kr.eric.api.config.JwtTokenProvider;
import kr.eric.api.dto.AccountDto;
import kr.eric.api.entity.Account;
import kr.eric.api.enums.AccountRole;
import kr.eric.api.exception.UserAlreadyExistException;
import kr.eric.api.exception.UserNotFoundException;
import kr.eric.api.repository.AccountRepository;
import kr.eric.api.util.AES256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static kr.eric.api.util.StringUtil.regNoValidationCheck;
import static kr.eric.api.util.StringUtil.telValidationCheck;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원 가입을 한다.
     *
     * @param request
     */
    public void signUp(AccountDto.SignUpRequest request) {

        // 주민등록번호 format 체크
        regNoValidationCheck(request.getRegNo());

        // 전화번호 format 체크
        telValidationCheck(request.getTel());

        // 중복 id 체크
        Optional<Account> optionalAccountByUserId = accountRepository.findFirstByUserId(request.getUserId());
        if (optionalAccountByUserId.isPresent()) {
            throw new UserAlreadyExistException();
        }

        // 중복 주민등록번호 체크
        Optional<Account> optionalAccountByRegNo = accountRepository.findFirstByRegNo(request.getRegNo());
        if (optionalAccountByRegNo.isPresent()) {
            throw new UserAlreadyExistException();
        }

        Account account = Account.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .regNo(AES256Util.encrypt(request.getRegNo()))
                .roles(new ArrayList(Collections.singleton(AccountRole.USER)))
                .tel(request.getTel())
                .build();

        accountRepository.save(account);
    }

    /**
     * 로그인 후, Jwt Token을 반환한다.
     *
     * @param request
     * @return Jwt Token
     */
    public String login(AccountDto.LoginRequest request) {
        Account account = accountRepository.findFirstByUserId(request.getUserId()).orElseThrow(() -> new UserNotFoundException("ID에 해당하는 회원이 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }

        return jwtTokenProvider.createToken(account.getUserId(), account.getRoles());
    }
}
